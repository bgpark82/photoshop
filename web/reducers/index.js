const rootState = {
  todos: {},
};

const rootReducer = (state = rootState, action) => {
  switch (action.type) {
    case "SUBMIT":
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
