import {
  Box,
  Button,
  Card,
  Divider,
  Grid,
  Modal,
  TextField,
} from "@mui/material";
import CartItem from "./CartItem";
import AddressCart from "./AddressCart";
import * as yup from "yup";
import AddLocationAltIcon from "@mui/icons-material/AddLocationAlt";
import { useState } from "react";
import { Formik, Field, ErrorMessage } from "formik";

const items = [1, 1];
const Cart = () => {
  const [open, setOpen] = useState(false);

  const createOrderUsingSelectedAddress = () => {
    // Implement your order creation logic here
  };

  const handleOpenAddAddressModal = () => setOpen(true);
  const handleClose = () => setOpen(false);

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

  const validationSchema = yup.object({
    streetAddress: yup
      .string()
      .required("Street address is required")
      .label("Street Address"),
    state: yup.string().required("State is required"),
    pincode: yup.string().required("Pincode is required"),
    city: yup.string().required("City is required"),
  });

  return (
    <>
      <main className="lg:flex justify-between">
        <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
          {items.map((item, index) => (
            <CartItem key={index} />
          ))}
          <Divider />
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Detail</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                <p>599</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Delivery Fee</p>
                <p>21</p>
              </div>
              <Divider />
              <div className="flex justify-between text-gray-400">
                <p>Total Pay</p>
                <p>21</p>
              </div>
            </div>
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">
              Choose Delivery Address
            </h1>
            <div className="flex gap-5 flex-wrap justify-center">
              {[1, 1, 1].map((item, index) => (
                <AddressCart
                  handleClickSelectAddress={createOrderUsingSelectedAddress}
                  item={item}
                  key={index}
                  showButton={true}
                />
              ))}
              <Card className="flex gap-5 w-64 p-5">
                <AddLocationAltIcon />
                <div className="space-y-3 text-gray-500">
                  <h1 className="font-semibold text-lg">Add New Address</h1>

                  <Button
                    variant="contained"
                    fullWidth
                    onClick={handleOpenAddAddressModal}
                  >
                    Add Location
                  </Button>
                </div>
              </Card>
            </div>
          </div>
        </section>
      </main>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Formik
            initialValues={{
              streetAddress: "",
              state: "",
              pincode: "",
              city: "",
            }}
            validationSchema={validationSchema}
            onSubmit={(values) => console.log(JSON.stringify(values, null, 4))}
          >
            {({ handleSubmit, errors, touched }) => (
              <form onSubmit={handleSubmit}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <Field
                      as={TextField}
                      name="streetAddress"
                      label="Street Address"
                      fullWidth
                      error={
                        touched.streetAddress && Boolean(errors.streetAddress)
                      }
                      helperText={
                        <ErrorMessage name="streetAddress">
                          {(msg) => <span className="text-red-600">{msg}</span>}
                        </ErrorMessage>
                      }
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <Field
                      as={TextField}
                      name="city"
                      label="City"
                      fullWidth
                      error={touched.city && Boolean(errors.city)}
                      helperText={
                        <ErrorMessage name="city">
                          {(msg) => <span className="text-red-600">{msg}</span>}
                        </ErrorMessage>
                      }
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <Field
                      as={TextField}
                      name="state"
                      label="State"
                      fullWidth
                      error={touched.state && Boolean(errors.state)}
                      helperText={
                        <ErrorMessage name="state">
                          {(msg) => <span className="text-red-600">{msg}</span>}
                        </ErrorMessage>
                      }
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <Field
                      as={TextField}
                      name="pincode"
                      label="Pincode"
                      fullWidth
                      error={touched.pincode && Boolean(errors.pincode)}
                      helperText={
                        <ErrorMessage name="pincode">
                          {(msg) => <span className="text-red-600">{msg}</span>}
                        </ErrorMessage>
                      }
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <Button type="submit" variant="contained" fullWidth>
                      Add Address
                    </Button>
                  </Grid>
                </Grid>
              </form>
            )}
          </Formik>
        </Box>
      </Modal>
    </>
  );
};

export default Cart;
