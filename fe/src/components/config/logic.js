export const isPresentInFavourites = (favourites, restaurant) => {
  for (let item in favourites) {
    if (restaurant.id == item.id) {
      return true;
    }
  }
  return false;
};
