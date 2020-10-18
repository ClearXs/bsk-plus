const { createProxyMiddleware } = require("http-proxy-middleware");
const path = require('path');

module.exports = function (app) {
    app.use(createProxyMiddleware("/bsk", {
        target: "http://localhost:8080/bsk", //配置你要请求的服务器地址
        pathRewrite: {'^/bsk': ''},
        changeOrigin: true,
    }))
};