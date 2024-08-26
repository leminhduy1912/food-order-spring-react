import { createTheme } from "@mui/material";

export const darkTheme = createTheme({
  palette: {
    mode: "dark",
    contrastText: "#fff",
    primary: {
      main: "#e91e63",
    },
    secondary: {
      main: "#5A20CB",
    },
    black: {
      main: "#0D0D0D",
    },
    background: {
      default: "#0D0D0D",
      paper: "#0D0D0D",
    },
    text: {
      main: "#111111",
    },
  },
});
