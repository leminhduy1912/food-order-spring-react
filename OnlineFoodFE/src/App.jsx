import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";

import { darkTheme } from "./themes/DarkTheme";
// import Navbar from "./components/Navbar/Navbar";
// import Home from "./components/Home/Home";
// import RestaurantDetail from "./components/Restaurant/RestaurantDetail";
// import Cart from "./components/Cart/Cart";
// import Profile from "./components/Profile/Profile";
import CustomerRoute from "./Routes/CustomerRoute";

function App() {
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
