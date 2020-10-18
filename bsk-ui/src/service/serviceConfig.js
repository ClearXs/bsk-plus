const proxyServerName = '/bsk';
const serviceConfig = {
    interface: {
        login: proxyServerName + '/api/security/login',
        logout: proxyServerName + '/api/security/logout',
        queryUserByAccount: proxyServerName + '/sys/user/queryUserByAccount',
        queryUserByToken: proxyServerName + '/sys/user/queryUserByToken',
    }
};
export default serviceConfig;