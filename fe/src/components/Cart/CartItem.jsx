import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import { Chip, IconButton } from "@mui/material";
const CartItem = () => {
  return (
    <div className="px-5">
      <div className="lg:flex items-center lg:space-x-5">
        <div>
          <img
            className="h-[5rem] w-[5rem] object-cover"
            src="https://th.bing.com/th/id/OIP.Z34VvdCT6bEordzT-7HgqwHaF-?w=220&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
            alt=""
          />
        </div>
        <div className="flex items-center justify-between lg:w-[70%]">
          <div className="space-y-1 lg:space-y-3 w-full">
            <p>Biryani</p>
            <div className="flex justify-center items-center">
              <div className="flex items-center space-x-1">
                <IconButton>
                  <RemoveCircleOutlineIcon />
                </IconButton>
                <div className="w-5 h-5 text-xs flex items-center justify-center">
                  {5}
                </div>
                <IconButton>
                  <AddCircleOutlineIcon />
                </IconButton>
              </div>
            </div>
          </div>
          <p>1956</p>
        </div>
      </div>

      <div className="pt-3 space-x-2">
        {[1, 1, 1].map((item, index) => {
          return <Chip key={index} label={"Bread"} />;
        })}
      </div>
    </div>
  );
};

export default CartItem;
