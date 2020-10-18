import axios from '../service'
import Qs from 'qs'
import serviceConfig from '../serviceConfig';

export function login(info) {
    return axios({
        method: 'post',
        url: serviceConfig.interface.login,
        headers:{
            'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        data: Qs.stringify(info)
    })
}

export function logout() {
    return axios({
        method: 'get',
        url: serviceConfig.interface.logout,
    })
}