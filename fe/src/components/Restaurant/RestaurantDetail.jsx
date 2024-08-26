import {
  Divider,
  FormControl,
  FormControlLabel,
  Grid,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import { useState } from "react";
import MenuCard from "./MenuCard";

const categories = ["Pizza", "Burger", "Chicken", "Rice"];

const foodOptions = [
  { label: "All", value: "all" },
  { label: "Vegetarian only", value: "vegetarian" },
  { label: "Non-Vegetarian", value: "non_vegetarian" },
  { label: "Seasonal", value: "seasonal" },
];
const menu = [1, 1, 1, 1];
const RestaurantDetail = () => {
  const [foodType, setFoodType] = useState("all");

  const handleFilter = (e) => {
    setFoodType(e.target.value);
  };

  return (
    <div className="px-5 lg:px-20">
      <section>
        <h3 className="text-gray-500 py-2 mt-10">Home/India/Fast Food</h3>
        <div>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <img
                src="https://cdn.pixabay.com/photo/2020/09/17/12/41/cafe-5579069_640.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                src="https://cdn.pixabay.com/photo/2019/02/21/19/00/restaurant-4011989_640.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                src="https://cdn.pixabay.com/photo/2017/07/15/13/45/french-restaurant-2506490_640.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
          </Grid>
        </div>
        <div className="pt-3 pb-5">
          <h1 className="text-4xl font-semibold">Indian Fast Food</h1>
          <p className="text-gray-500 mt-1">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis
            adipisci enim quis, voluptate illum distinctio quibusdam similique
            provident minima beatae quia repellendus, tenetur expedita
            voluptatum voluptas aperiam, inventore blanditiis? Blanditiis.
          </p>
          <div className="space-y-3 mt-3">
            <p className="text-gray-500 items-center gap-3">
              <LocationOnIcon />
              <span>Da Nang,Viet Nam</span>
            </p>
            <p className="text-gray-500 items-center gap-3">
              <CalendarTodayIcon />
              <span>Mon-Sun: 9:00 AM - 9:00 PM</span>
            </p>
          </div>
        </div>
      </section>
      <Divider />
      <section className="pt-[2rem] relative flex">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28">
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Type
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  name="food_type"
                  value={foodType}
                  onChange={handleFilter}
                >
                  {foodOptions.map((item) => {
                    return (
                      <FormControlLabel
                        key={item.value}
                        value={item.value}
                        control={<Radio />}
                        label={item.label}
                      />
                    );
                  })}
                </RadioGroup>
              </FormControl>
            </div>
            <Divider />
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Category
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  name="food_type"
                  value={foodType}
                  onChange={handleFilter}
                >
                  {categories.map((item) => {
                    return (
                      <FormControlLabel
                        key={item}
                        value={item}
                        control={<Radio />}
                        label={item}
                      />
                    );
                  })}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>
        <div className="space-y-5 lg:w-[80%] lg:pl-10">
          {menu.map((item, index) => {
            return <MenuCard key={index} />;
          })}
        </div>
      </section>
    </div>
  );
};

export default RestaurantDetail;
