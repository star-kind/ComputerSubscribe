/*解决react跨域发送请求问题*/
const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://192.168.1.4:8080',
      changeOrigin: true,
      secure: false,
      pathRewrite: {
        '^/api': '',
      },
    })
  )
}
