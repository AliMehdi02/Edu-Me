import toggleSearchLoadingReducer from './toggleSearchLoadingReducer';
import isSearchPostReducer from './isSearchPostReducer';
import {combineReducers} from 'redux';

const allReducers = combineReducers({
    toggleSearchLoading: toggleSearchLoadingReducer,
    isSearchPost: isSearchPostReducer
});

export default allReducers;
