import { all, call, fork, put, takeLatest } from "@redux-saga/core/effects";

async function fetchTodosApi({ title, details, category }) {
  const res = await fetch("http://localhost:8000/notes", {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({ title, details, category }),
  });

  return await res.json();
}

function* fetchTodos(action) {
  const todos = yield call(fetchTodosApi, action.payload);
  yield put({
    type: "SUBMIT_SUCCESS",
    payload: todos,
  });
}

function* watchTodos() {
  yield takeLatest("SUBMIT", fetchTodos);
}

function* rootSaga() {
  yield all([fork(watchTodos)]);
}

export default rootSaga;
