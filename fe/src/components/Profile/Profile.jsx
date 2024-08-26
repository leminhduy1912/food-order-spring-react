import { useState } from "react";
import ProfileNavigation from "./ProfileNavigation";
import { Route, Routes } from "react-router-dom";
import UserProfile from "./UserProfile";
import Address from "./Address";
import Favourite from "./Favourite";
import Event from "./Event";
import Order from "./Order";

const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState(true);
  return (
    <div className="lg:flex justify-between">
      <div className="sticky h-[80vh] lg:w-[20%]">
        <ProfileNavigation open={openSideBar} />
      </div>
      <div className="lg:w-[80%]">
        <Routes>
          <Route path="/" element={<UserProfile />}></Route>
          <Route path="/orders" element={<Order />}></Route>
          <Route path="/address" element={<Address />}></Route>
          <Route path="/favourites" element={<Favourite />}></Route>
          <Route path="/events" element={<Event />}></Route>
        </Routes>
      </div>
    </div>
  );
};

export default Profile;
