package com.foodOrder.service.impl;

import com.foodOrder.dto.RestaurantDto;
import com.foodOrder.model.Address;
import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.repository.AddressRepository;
import com.foodOrder.repository.RestaurantRepository;
import com.foodOrder.repository.UserRepository;
import com.foodOrder.request.CreateRestaurantRequest;
import com.foodOrder.service.IRestaurantService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) throws Exception {
        Address address = addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInfomation(request.getContactInfomation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHour(request.getOpeningHour());
        restaurant.setRegistrationDate(request.getRegistrationDate());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(CreateRestaurantRequest updateRequest, Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            Restaurant restaurantToUpdate = restaurant.get();
            restaurantToUpdate.setDescription(updateRequest.getDescription());
            restaurantToUpdate.setName(updateRequest.getName());
            restaurantToUpdate.setCuisineType(updateRequest.getCuisineType());
            return restaurantRepository.save(restaurantToUpdate);
        } else {
            throw new Exception("Restaurant not found");
        }

    }

    @Override
    public void deleteRestaurant(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            restaurantRepository.deleteById(id);
        } else {
            throw new Exception("Restaurant not found");
        }
    }

    @Override
    public List<Restaurant> getAllRestaurant() throws Exception {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) throws Exception {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurantRepository.findById(id);
        } else {
            throw new Exception("Restaurant not found");
        }

    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addRestaurantToListFavouriteOfUser(Long restaurantId, User user) throws Exception {
        Optional<Restaurant> restaurant = findRestaurantById(restaurantId);
        if (restaurant.isPresent()) {
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setDescription(restaurant.get().getDescription());
            restaurantDto.setId(restaurantId);
            restaurantDto.setImages(restaurant.get().getImages());
            restaurantDto.setTitle(restaurant.get().getName());
//            if (user.getRestaurantFavouriteList().contains(restaurantDto)) {
//                user.getRestaurantFavouriteList().remove(restaurantDto);
//            } else {
//                user.getRestaurantFavouriteList().add(restaurantDto);
//            }
            // if the restaurant is already in fav,remove,else add it to fav
            boolean isFavourited= false;
            List<RestaurantDto> listFav= user.getRestaurantFavouriteList();
            for (RestaurantDto dto : listFav){
                if (dto.getId().equals(restaurantId)){
                    isFavourited=true;
                    break;
                }
            }
            if (isFavourited){
                listFav.removeIf(listFavDto -> listFavDto.getId().equals(restaurantId));
            } else {
                listFav.add(restaurantDto);
            }
            userRepository.save(user);
            return restaurantDto;
        } else {
            throw new Exception("Restaurant not found");
        }
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            Restaurant restaurant1 = restaurant.get();
            restaurant1.setOpen(!restaurant1.isOpen());
            return restaurantRepository.save(restaurant1);
        } else {
            throw new Exception("Restaurant not found");
        }
    }
}
