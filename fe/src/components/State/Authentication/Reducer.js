import { isPresentInFavourites } from "../../config/logic";
import {
  ADD_TO_FAVOURITE_FAILURE,
  ADD_TO_FAVOURITE_REQUEST,
  ADD_TO_FAVOURITE_SUCCESS,
  GET_USER_REQUEST,
  LOGIN_REQUEST,
  REGISTER_REQUEST,
} from "./ActionType";

const initialState = {
  user: null,
  isLoading: false,
  error: null,
  jwt: null,
  favourite: [],
  success: null,
};
export const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_REQUEST:
    case LOGIN_REQUEST:
      return {
        ...state,
        isLoading: false,
        jwt: action.payload,
        success: "Log in success",
      };
    case GET_USER_REQUEST:
    case ADD_TO_FAVOURITE_REQUEST:
      return { ...state, isLoading: false, error: null, success: null };
    case ADD_TO_FAVOURITE_SUCCESS:
      return {
        ...state,
        isLoading: false,
        error: null,
        favourite: isPresentInFavourites(state.favourite, action.payload)
          ? state.favourite.filter((item) => item.id !== action.payload.id)
          : [action.payload, ...state.favourite],
        success: null,
      };
    case ADD_TO_FAVOURITE_FAILURE:
      return {
        ...state,
        isLoading: false,
        error: action.payload,
        // favourite: isPresentInFavourites(state.favourite, action.payload)
        //   ? state.favourite.filter((item) => item.id !== action.payload.id)
        //   : [action.payload, ...state.favourite],
        success: null,
      };
    default:
      return state;
  }
};
