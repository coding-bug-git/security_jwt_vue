<template>
  <el-row class="row-bg" justify="center" type="flex">
    <el-col :sm="6" :lg="7">
      <h2>欢迎来到VueAdmin管理系统</h2>
      <el-image style="height: 180px;width: 180px;" :src="require('@/assets/barCode.jpg')"></el-image>
      <p>coding-bug</p>
    </el-col>
    <el-col :span="1">
      <el-divider direction="vertical"></el-divider>
    </el-col>
    <el-col :sm="6" :lg="7">
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="rules"
        label-width="100px"
        class="demo-loginForm"
        :size="formSize"
      >
        <el-form-item label="用户名" prop="username" style="width: 380px">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="width: 380px">
          <el-input v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="captcha" style="width: 380px">
          <el-col :span="12">
            <el-input v-model="loginForm.captcha"></el-input>
          </el-col>
          <el-col :span="12" style="display: flex;align-items: center;">
            <el-image class="captcha" :src="captchaImg"/>
          </el-col>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm(loginFormRef,doLogin)"
          >登录
          </el-button
          >
          <el-button @click="resetForm(loginFormRef)">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>

import { on, reactive, ref } from 'vue'
import FormValidate from '@/utils/formValidate'
import request from '@/utils/request'
// More info see https://github.com/element-plus/element-plus/blob/dev/docs/examples/form/utils.ts
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const store = useStore()

    const formSize = ref('')

    const loginFormRef = ref()
    const loginForm = reactive({
      username: '',
      password: '',
      captcha: '',
      token: '',
      date2: '',
      delivery: false,
      type: [],
      resource: '',
      desc: ''
    })
    const rules = reactive({
      username: [
        {
          required: true,
          message: '请输入用户名',
          trigger: 'blur'
        }

      ],
      password: [
        {
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }
      ],
      captcha: [
        {
          required: true,
          message: '请输入验证码',
          trigger: 'blur'
        }
      ]
    })

    const captchaImg = ref()

    getCaptcha()
    request.get('/test3', {
      params: { a: 1 }
    }).then(res => console.log(res))

    const doLogin = () => {
      request.post('/login', loginForm).then(res => {
        console.log(res)
      })
    }

    function getCaptcha() {
      request.get('/captcha')
        .then(res => {
          console.log(res)
          loginForm.token = res.data.token
          captchaImg.value = res.data.captchaImg
        })
    }

    return {
      formSize,
      loginFormRef,
      loginForm,
      rules,
      captchaImg,
      doLogin,
      ...FormValidate
    }
  }
}

</script>

<style scoped lang="scss">
.captcha {
  margin-left: 8px;
  border-radius: 4px;
}

.el-divider {
  height: 200px;
}

.el-row {
  background-color: #fafafa;
  height: 100vh;
  display: flex;
  align-items: center;
  text-align: center;
}
</style>
