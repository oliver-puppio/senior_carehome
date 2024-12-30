<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item,index)  in levelList" :key="index" >
        <span v-if="item.meta.title">
          <!-- 如果没有重定向或者是最后一个？是文本 -->
          <span v-if="item.redirect==='noredirect'||index==levelList.length-1" class="no-redirect">{{item.meta.title}}</span>
          <!-- 否则，标签链接，点击跳转到他的路径或重定向后的路径 -->
          <router-link v-else :to="item.redirect||item.path">{{item.meta.title}}</router-link></span>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
export default {
  created() {
    this.getBreadcrumb()
  },
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  methods: {
    getBreadcrumb() {
      let matched = this.$route.matched.filter(item => item.name)
      const first = matched[0]

      if (first) {  
        matched = [{ path: '/home', meta: { title: '首页' }}].concat(matched)
      }
      this.levelList = matched;
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app-breadcrumb.el-breadcrumb {
    display: inline-block;
    font-size: 14px;
    line-height: 50px;
    margin-left: 10px;
    .no-redirect {
      color: #8898ac;
      cursor: text;
    }
  }
</style>
