import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";

import { darkTheme } from "./themes/DarkTheme";
// import Navbar from "./components/Navbar/Navbar";
// import Home from "./components/Home/Home";
// import RestaurantDetail from "./components/Restaurant/RestaurantDetail";
// import Cart from "./components/Cart/Cart";
// import Profile from "./components/Profile/Profile";
import CustomerRoute from "./Routes/CustomerRoute";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUser } from "./components/State/Authentication/Action";

function App() {
  // const dispatch = useDispatch();
  // const jwt = localStorage.getItem("jwt");
  // const { auth } = useSelector((store) => store);
  // useEffect(() => {
  //   dispatch(getUser(auth.jwt || jwt));
  // }, [auth.jwt]);
  return (
    <>
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <CustomerRoute />
      </ThemeProvider>
    </>
  );
}

export default App;
