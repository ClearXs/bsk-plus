import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import reducer from './reducer';
import roleReducer from './roleReducer';
import userReducer from './userReducer';
import thunk from 'redux-thunk';

// 使用增加函数，目的是redux既可以使用chorme插件与thunk
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ? window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({}) : compose

const enhancer = composeEnhancers(applyMiddleware(thunk));

// createStore只接收两个参数，所以为了使用thunk，而是用增加函数
const reducers = combineReducers({
    base: reducer,
    user: userReducer,
    role: roleReducer
})
const store = createStore(reducers, enhancer);

export default store;