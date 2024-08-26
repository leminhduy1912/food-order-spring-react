import { Box, Modal } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import RegisterForm from "./RegisterForm";
import LoginForm from "./LoginForm";

const Auth = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const handleClose = () => {};
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    outline: "none",
    boxShadow: 24,
    p: 4,
  };
  console.log("modal");
  return (
    <>
      <Modal
        open={
          location.pathname == "/account/register" ||
          location.pathname == "/account/login"
        }
        onClose={handleClose}
      >
        <Box style={style}>
          {" "}
          {location.pathname == "/account/register" ? (
            <RegisterForm />
          ) : (
            <LoginForm />
          )}
        </Box>
      </Modal>
    </>
  );
};

export default Auth;
