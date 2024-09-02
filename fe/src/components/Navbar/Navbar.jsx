import { Avatar, Badge, Box, IconButton } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { pink } from "@mui/material/colors";
import { Person } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
const Navbar = () => {
  const navigate = useNavigate();
  const { auth } = useSelector((store) => store);
  const handleClickAvatar = () => {
    if (auth.user.role == "CUSTOMER") {
      navigate("/my-profile");
    } else {
      navigate("/admin/restaurant");
    }
  };
  // console.log(auth.user.fullName[0].toUpperCase());
  return (
    <Box className="px-5 z-50 sticky top-0 py-[.8rem] bg-[#e91e63] lg:px-20 flex justify-between">
      <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
        <li
          onClick={() => {
            navigate("/");
          }}
          className="logo font-semibold text-gray-300 text-2xl list-none"
        >
          Navbar
        </li>
      </div>

      <div className="flex items-center space-x-2 lg:space-x-10">
        <div className="">
          <IconButton>
            <SearchIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>
        <div className="cursor-pointer">
          {auth.user ? (
            <Avatar
              onClick={handleClickAvatar}
              sx={{ bgcolor: "white", color: "black", fontSize: "1rem" }}
            >
              {auth.user.fullName[0].toUpperCase()}
            </Avatar>
          ) : (
            <IconButton
              onClick={() => {
                navigate("/account/login");
              }}
            >
              <Avatar
                sx={{ bgcolor: "white", color: "black", fontSize: "1rem" }}
              >
                <Person />
              </Avatar>
            </IconButton>
          )}
        </div>
        <div className="">
          <IconButton>
            <Badge color="primary" badgeContent={3}>
              <ShoppingCartIcon sx={{ fontSize: "1.5rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </Box>
  );
};

export default Navbar;
