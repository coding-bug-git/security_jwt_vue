const Mock = require('mockjs')

const Random = Mock.Random

const AjaxResult = {
  code: 200,
  msg: 'success',
  data: null
}

Mock.mock('/captcha', 'get', () => {
  AjaxResult.data = {
    token: Random.string(32),
    captcha: Random.dataImage('120x40', 'p7n5w')
  }
  return AjaxResult
})

Mock.mock('/login', 'post', () => {
  AjaxResult.code = 400
  AjaxResult.msg = '验证码错误'
  return AjaxResult
})
