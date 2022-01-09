import axios from 'axios'
import router from '@/router'
import { ElMessage } from 'element-plus'

const request = axios.create({
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }

})

request.interceptors.request.use(config => {
  // config.headers['Content-Type'] = 'application/json;charset=utf-8'
  config.headers.Authorization = localStorage.getItem('token')
  config.headers.token = localStorage.getItem('token')
  return config
}, error => {
  return Promise.reject(error)
})

request.interceptors.response.use((response) => {
  let res = response.data
  if (response.config.responseType === 'blob') {
    return res
  }
  if (typeof res === 'string') {
    res = res ? JSON.parse(res) : res
  }
  if (res.code === 200) {
    return res
  } else {
    ElMessage.error(res.msg ? res.msg : '系统异常')
    return Promise.reject(res.msg)
  }
}, error => {
  console.log('err' + error)
  if (error.response.data) {
    error.message = error.response.data.msg
  }
  if (error.response.status === 401) {
    router.push('login')
  }
  ElMessage.error({ message: error.message, duration: 3000 })
  return Promise.reject(error)
})
request.defaults.baseURL = process.env.VUE_APP_BASE_API
export default request
