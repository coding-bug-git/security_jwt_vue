const name = process.env.VUE_APP_TITLE || 'coding-bug' // 网页标题

const port = process.env.port || process.env.npm_config_port || 80 // 端口

module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: true,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        target: process.env.BACK_END_API,
        changeOrigin: true, // 是否设置同源
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    },
    disableHostCheck: true
  }
}
