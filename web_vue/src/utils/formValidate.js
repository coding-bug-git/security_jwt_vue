export default {
  submitForm: (formEl, fn) => {
    if (!formEl) return
    formEl.validate((valid) => {
      if (valid) {
        console.log('submit!')
        fn()
      } else {
        console.log('error submit!')
        return false
      }
    })
  },
  resetForm: (formEl) => {
    if (!formEl) return
    formEl.resetFields()
  }
}
