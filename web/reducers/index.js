import { HYDRATE } from "next-redux-wrapper";

const rootState = {
  todos: {},
};

const rootReducer = (state = rootState, action) => {
  switch (action.type) {
    case HYDRATE:
      return {
        ...state,
        ...action.payload,
      };
      break;
    case "SUBMIT":
      console.log(state, action);
      return {
        ...state,
        todos: action.payload,
      };
      break;
    default:
      return state;
  }
};

export default rootReducer;
