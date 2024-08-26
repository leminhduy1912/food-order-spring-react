import { Button, Card } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
const AddressCart = ({ item, showButton, handleClickSelectAddress }) => {
  return (
    <Card className="flex gap-5 w-64 p-5">
      <HomeIcon />
      <div className="space-y-3 text-gray-500">
        <h1 className="font-semibold text-lg text-white">Home</h1>
        <p>My An,Da Nang</p>
        {showButton && (
          <Button
            variant="contained"
            fullWidth
            onClick={() => {
              handleClickSelectAddress(item);
            }}
          >
            Select
          </Button>
        )}
      </div>
    </Card>
  );
};

export default AddressCart;
