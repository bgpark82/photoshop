import { applyMiddleware, compose, createStore } from "redux";
import rootReducer from "../reducers";
import { createWrapper } from "next-redux-wrapper";
import { composeWithDevTools } from "redux-devtools-extension";

const middleware =
  process.env.NODE_ENV === "production"
    ? compose(applyMiddleware())
    : composeWithDevTools(applyMiddleware());

const store = createStore(rootReducer, middleware);
const wrapper = createWrapper(() => store);

export default wrapper;
