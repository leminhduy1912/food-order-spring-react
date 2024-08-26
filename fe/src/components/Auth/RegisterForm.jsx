import {
  Box,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Modal,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { Field, Formik, Form } from "formik";
import { useLocation, useNavigate } from "react-router-dom";

const initialValues = {
  fullName: "",
  role: "ROLE_CUSTOMER",
  email: "",
  password: "",
};

const RegisterForm = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const handleClose = () => {};

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

  const handleSubmit = () => {};

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
              Register
            </Typography>
            <Formik onSubmit={handleSubmit} initialValues={initialValues}>
              <Form>
                <Field
                  as={TextField}
                  name="fullName"
                  label="Fullname"
                  variant="outlined"
                  fullWidth
                  margin="normal"
                />
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
                  type="password"
                  margin="normal"
                />
                <FormControl fullWidth margin="normal">
                  <InputLabel id="demo-simple-select-label">Role</InputLabel>
                  <Field
                    labelId="demo-simple-select-label"
                    as={Select}
                    id="demo-simple-select"
                    // value={age}
                    label="Age"
                    // onChange={handleChange}
                  >
                    <MenuItem value={"ROLE_CUSTOMER"}>Customer</MenuItem>
                    <MenuItem value={"ROLE_RESTAURANT_OWNER"}>
                      Restaurant Owner
                    </MenuItem>
                  </Field>
                </FormControl>
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
              If have an account already?{" "}
              <Button
                size="small"
                onClick={() => {
                  navigate("/account/login");
                }}
              >
                Log in
              </Button>
            </Typography>
          </div>
        </Box>
      </Modal>
    </>
  );
};

export default RegisterForm;
