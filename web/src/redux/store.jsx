import {compose, createStore} from "redux";
import reducers from './reducers/index';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(reducers, {},  composeEnhancers && composeEnhancers());

export default store;