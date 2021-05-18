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
    case "SUBMIT_SUCCESS":
      return {
        ...state,
        todos: {
          ...state.todos,
          msg: "SUCCESS",
        },
      };
    default:
      return state;
  }
};

export default rootReducer;
