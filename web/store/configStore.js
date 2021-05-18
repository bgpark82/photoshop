import { applyMiddleware, compose, createStore } from "redux";
import rootReducer from "../reducers";
import { createWrapper } from "next-redux-wrapper";
import { composeWithDevTools } from "redux-devtools-extension";
import createSagaMiddleware from "@redux-saga/core";
import rootSaga from "../saga";

const sagaMiddleware = createSagaMiddleware();

const enhancer = applyMiddleware(sagaMiddleware);
const middleware =
  process.env.NODE_ENV === "production"
    ? compose(enhancer)
    : composeWithDevTools(enhancer);

const store = createStore(rootReducer, middleware);

sagaMiddleware.run(rootSaga);

const wrapper = createWrapper(() => store);

export default wrapper;
