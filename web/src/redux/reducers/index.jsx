import { combineReducers } from "redux";
import {AuthReducer} from "./AuthReducer";


const reducers = combineReducers({
    authenticateToken: AuthReducer,
});

export default reducers;

