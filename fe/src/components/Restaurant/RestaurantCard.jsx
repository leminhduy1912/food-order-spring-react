import { Card, Chip, IconButton } from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
const RestaurantCard = () => {
  return (
    <Card className="p-2 w-[22rem]">
      <div
        className={`${true ? "cursor-pointer" : "cursor-not-allowed"} relative`}
      >
        <img
          className="w-full h-[10rem] rounded-t-md object-cover"
          src="https://cdn.pixabay.com/photo/2022/11/14/10/37/chinese-lanterns-7591296_960_720.jpg"
          alt=""
        />
        <Chip
          size="small"
          className="absolute top-2 left-2"
          color={true ? "success" : "error"}
          label={true ? "open" : "closed"}
        />
      </div>
      <div className="p-4 textPart w-full lg:flex justify-between">
        <div className="space-y-1">
          <p className="font-semibold text-lg">Indian food restaurant</p>
          <p className="text-sm text-gray-500">
            Craving it all ? dive into out global fla....{" "}
          </p>
        </div>
        <div>
          <IconButton>
            {true ? <FavoriteIcon /> : <FavoriteBorderIcon />}
          </IconButton>
        </div>
      </div>
    </Card>
  );
};

export default RestaurantCard;
