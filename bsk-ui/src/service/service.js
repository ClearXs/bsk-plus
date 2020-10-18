import axios from 'axios';
import { getToken } from '../common/auth'
import { destroy } from '../common/auth'

axios.create({
    timeout: 15000 ,// 请求超时时间
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
})
 
// 开始请求设置，发起拦截处理
axios.interceptors.request.use(config => {
    const token = getToken();
    config.headers['X-Authorization-access_token'] = token;
    return config
}, error => {
    return Promise.reject(error)
})

// response拦截器
axios.interceptors.response.use((response) => {
        const res = response.data;
        //这里根据后台返回来设置
        if (res.state === 200) {
            return response;
        } else if (res.state === 401) {
            return Promise.reject(res.message);
        }
    }, (error) => {
        const res = error.response;
        if (res.status === 401 || res.status === 500) {
            destroy();
            window.location.reload()
        }
        return Promise.reject(error)
    }
)
 
export default axios