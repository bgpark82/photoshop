import { createStore } from "redux";
import rootReducer from "../reducers";
import { createWrapper } from "next-redux-wrapper";

const store = createStore(rootReducer);
const wrapper = createWrapper(() => store);

export default wrapper;
