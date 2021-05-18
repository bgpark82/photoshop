const rootState = {};

const rootReducer = (state = rootState, action) => {
  switch (action.type) {
    case "SUBMIT":
      return {
        ...action.payload,
      };
      break;
    default:
      return state;
  }
};

export default rootReducer;
