import { QUERY_USER } from './actionTypes'
import { login, logout } from '../service/api/api'
import { queryUserByToken, queryUserByAccount } from '../service/sys/user'
import { message } from 'antd';
import { saveToken, destroy } from '../common/auth'

export const queryUser = (data) => ({
    type: QUERY_USER,
    data
});

// 导出函数，调用后返回也是一个函数。thunk自动接收store的dispatch参数
export const toLogin = (info, callback) => {
    return () => {
        login(info).then((res) => {
            const data = res.data;
            saveToken(data.data);
            callback && callback();
        }).catch((error) => {
            message.error('登录失败：' + error.message);
        })
    }
}

export const toLogout = (callback) => {
    return () => {
        logout().then((res) => {
            const data = res.data
            if (data.state !== 200) {
                message.error(data.message)
            } else {
                message.success(data.message);
                callback && callback();
                destroy();
                window.location.reload()
            }
        }).catch((error) => {
            message.error('登出失败' + error.message);
        })
    }
}


export const queryUserAccordingToken = (callback) => {
    return (dispatch) => {
        queryUserByToken().then((res) => {
            const data = res.data;
            dispatch(queryUser(data.data));
            callback && callback();

        }).catch((error) => {
            message.error(error.message);
        })
    }
}


export const queryUserAccordingAccount = (account, callback) => {
    return (dispatch) => {
        queryUserByAccount(account).then((res) => {
            const data = res.data;
            if (data) {
                dispatch(queryUser(data.data));
                callback && callback();
            }
        }).catch((error) => {
            message.error(error.message);
        })
    }
}
