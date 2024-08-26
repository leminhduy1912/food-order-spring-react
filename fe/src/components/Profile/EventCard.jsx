import {
  Card,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  Typography,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
const EventCard = () => {
  return (
    <div>
      <Card sx={{ width: 345 }}>
        {" "}
        <CardMedia
          sx={{ height: 345 }}
          image="https://cdn.pixabay.com/photo/2022/05/11/07/10/porridge-7188630_640.jpg"
        ></CardMedia>
        <CardContent>
          <Typography variant="h5">Indian Fast Food</Typography>
          <Typography variant="body2">50% Off on your first order</Typography>
          <div className="py-2 space-y-2">
            <p>{"Da Nang"}</p>
            <p className="text-sm text-green-500">February 14,2024 12:00 AM</p>
            <p className="text-sm text-red-500">February 18,2024 12:00 AM</p>
          </div>
        </CardContent>
        <CardActions>
          <IconButton>
            <DeleteIcon />
          </IconButton>
        </CardActions>
      </Card>
    </div>
  );
};

export default EventCard;
