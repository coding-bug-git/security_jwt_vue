import axios from 'axios'

const request = {}
const requestObj = axios.create({
  timeout: 5000
})

requestObj.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8'
  // config.headers['token'] = user.token
  return config
}, error => {
  return Promise.reject(error)
})

requestObj.interceptors.response.use(
  response => {
    let res = response.data
    if (response.config.responseType === 'blob') {
      return res
    }
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    return res
  }, error => {
    console.log('err' + error)
    return Promise.reject(error)
  })
request.get = function (url, config) {
  return requestObj.get(url, config)
}
request.post = function (url, config) {
  return requestObj.post(url, config)
}
export default request
