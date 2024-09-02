// import { Box, Button, Modal, TextField, Typography } from "@mui/material";
// import { Field, Formik, Form } from "formik";
// import { useDispatch, useSelector } from "react-redux";
// import { useLocation, useNavigate } from "react-router-dom";
// import { getUser, loginUser } from "../State/Authentication/Action";
// import { useEffect } from "react";

// const LoginForm = () => {
//   const dispatch = useDispatch();
//   const navigate = useNavigate();
//   const location = useLocation();
//   const handleClose = () => {};
//   const { auth } = useSelector((store) => store);

//   // const jwt = localStorage.getItem("jwt");
//   const handleSubmit = (values) => {
//     dispatch(loginUser({ userData: values, navigate }));
//     const jwt = localStorage.getItem("jwt");
//     dispatch(getUser(jwt));
//   };
//   const initialValues = {
//     email: "",
//     password: "",
//   };
//   const style = {
//     position: "absolute",
//     top: "50%",
//     left: "50%",
//     padding: "2rem",
//     transform: "translate(-50%, -50%)",
//     width: 400,
//     backgroundColor: "black",
//     border: "2px solid black",
//     boxShadow: 24,
//     p: 4,
//   };
//   console.log("modal");
//   return (
//     <>
//       <Modal
//         open={
//           location.pathname == "/account/register" ||
//           location.pathname == "/account/login"
//         }
//         onClose={handleClose}
//       >
//         <Box style={style}>
//           <div>
//             <Typography variant="h5" className="text-center">
//               Log In
//             </Typography>
//             <Formik onSubmit={handleSubmit} initialValues={initialValues}>
//               <Form>
//                 <Field
//                   as={TextField}
//                   name="email"
//                   label="Email"
//                   variant="outlined"
//                   fullWidth
//                   margin="normal"
//                 />
//                 <Field
//                   as={TextField}
//                   name="password"
//                   label="Password"
//                   variant="outlined"
//                   fullWidth
//                   margin="normal"
//                 />
//                 <Button
//                   className="mt-5"
//                   fullWidth
//                   type="submit"
//                   variant="contained"
//                 >
//                   Submit
//                 </Button>
//               </Form>
//             </Formik>
//             <Typography variant="body2" align="center" sx={{ mt: 3 }}>
//               Don't have account ?{" "}
//               <Button
//                 size="small"
//                 onClick={() => {
//                   navigate("/account/register");
//                 }}
//               >
//                 Register
//               </Button>
//             </Typography>
//           </div>
//         </Box>
//       </Modal>
//     </>
//   );
// };

// export default LoginForm;

import { Box, Button, Modal, TextField, Typography } from "@mui/material";
import { Field, Formik, Form } from "formik";
import { useDispatch, useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
import { getUser, loginUser } from "../State/Authentication/Action";
import { useEffect } from "react";

const LoginForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();
  const { auth } = useSelector((store) => store);

  const handleClose = () => {};

  const handleSubmit = async (values) => {
    await dispatch(loginUser({ userData: values, navigate }));

    const jwt = localStorage.getItem("jwt");

    if (jwt) {
      dispatch(getUser(jwt));
    }
  };

  const initialValues = {
    email: "",
    password: "",
  };

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    padding: "2rem",
    transform: "translate(-50%, -50%)",
    width: 400,
    backgroundColor: "black",
    border: "2px solid black",
    boxShadow: 24,
    p: 4,
  };

  return (
    <>
      <Modal
        open={
          location.pathname === "/account/register" ||
          location.pathname === "/account/login"
        }
        onClose={handleClose}
      >
        <Box style={style}>
          <div>
            <Typography variant="h5" className="text-center">
              Log In
            </Typography>
            <Formik onSubmit={handleSubmit} initialValues={initialValues}>
              <Form>
                <Field
                  as={TextField}
                  name="email"
                  label="Email"
                  variant="outlined"
                  fullWidth
                  margin="normal"
                />
                <Field
                  as={TextField}
                  name="password"
                  label="Password"
                  variant="outlined"
                  fullWidth
                  margin="normal"
                />
                <Button
                  className="mt-5"
                  fullWidth
                  type="submit"
                  variant="contained"
                >
                  Submit
                </Button>
              </Form>
            </Formik>
            <Typography variant="body2" align="center" sx={{ mt: 3 }}>
              Don't have account?{" "}
              <Button
                size="small"
                onClick={() => {
                  navigate("/account/register");
                }}
              >
                Register
              </Button>
            </Typography>
          </div>
        </Box>
      </Modal>
    </>
  );
};

export default LoginForm;
