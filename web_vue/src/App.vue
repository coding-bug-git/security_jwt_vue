<template>
  <el-config-provider :locale="locale">
    <suspense>
      <router-view/>
    </suspense>
  </el-config-provider>

</template>

<script>
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

export default {
  setup() {
    return {
      locale: zhCn
    }
  }
}
</script>
<style lang="scss">

</style>
