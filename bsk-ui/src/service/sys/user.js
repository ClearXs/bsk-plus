import axios from "../service"
import serviceConfig from '../serviceConfig';


export function queryUserByAccount(account) {
    return (
        axios.get(serviceConfig.interface.queryUserByAccount + '?account=' + account)
    )
}

export function queryUserByToken() {
    return (
        axios.get(serviceConfig.interface.queryUserByToken)
    )
}