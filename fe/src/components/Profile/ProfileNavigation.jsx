import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import FavoriteIcon from "@mui/icons-material/Favorite";
import HomeIcon from "@mui/icons-material/Home";
import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import NotificationsIcon from "@mui/icons-material/Notifications";
import EventIcon from "@mui/icons-material/Event";
import LogoutIcon from "@mui/icons-material/Logout";
import AddReactionIcon from "@mui/icons-material/AddReaction";
import { Drawer, useMediaQuery } from "@mui/material";
import { useNavigate } from "react-router-dom";

const menu = [
  { title: "Orders", icon: <ShoppingBagIcon /> },
  { title: "Favourites", icon: <FavoriteIcon /> },
  { title: "Address", icon: <AddReactionIcon /> },
  { title: "Payments", icon: <AccountBalanceWalletIcon /> },
  { title: "Notifications", icon: <NotificationsIcon /> },
  { title: "Events", icon: <EventIcon /> },
  { title: "Log out", icon: <LogoutIcon /> },
];

const ProfileNavigation = ({ open, handleClose }) => {
  const isSmallScreen = useMediaQuery("(min-width:900px)");
  const navigate = useNavigate();
  const handleNavigate = (item) => {
    const title = item.title.toLowerCase();
    navigate(`/my-profile/${title}`);
  };
  return (
    <div>
      <Drawer
        variant={isSmallScreen ? "temporary" : "permanent"}
        onClose={handleClose}
        open={isSmallScreen ? open : true}
        sx={{ zIndex: 1 }}
        anchor="left"
      >
        <div className="pt-16 w-[40vw] lg:w-[20vw] h-[100vh] flex flex-col gap-8 bg-clip-border rounded-xl bg-black text-gray-700    p-4 shadow-xl shadow-blue-gray-900/5">
          <div className="mb-2 p-4">
            <h5 className="block antialiased tracking-normal font-sans text-xl font-semibold leading-snug text-gray-900">
              Material Tailwind
            </h5>
          </div>
          <nav className="flex flex-col gap-1 min-w-[240px] p-2 font-sans text-base font-normal text-gray-700">
            {menu.map((item, index) => {
              return (
                <div
                  onClick={() => {
                    handleNavigate(item);
                  }}
                  key={index}
                  role="button"
                  tabIndex="0"
                  className="flex text-xl font-semibold items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
                >
                  <div className="grid place-items-center mr-4">
                    {item.icon}
                  </div>
                  {item.title}
                </div>
              );
            })}
          </nav>
        </div>
      </Drawer>
    </div>
  );
};

export default ProfileNavigation;
