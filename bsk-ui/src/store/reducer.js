import { QUERY_USER } from './actionTypes'

const defaultState = {
    user: {},
    globalLoading: false
}

export default (state = defaultState, action) => {
    if (action.type === QUERY_USER) {
        // 做对象的深度拷贝
        let newState = JSON.parse(JSON.stringify(state));
        newState.user = action.data;
        return newState;
    }

    return state;
}