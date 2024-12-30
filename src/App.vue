<!-- UI结构 -->
<template>
  <div id="app">
    <!-- APP根组件 -->

    <!-- router-view是路由占位符，这样的话通过路由匹配到的组件都会被渲染到router-view中展示 -->
    <router-view/>
  </div>
</template>

<!-- 行为 -->
<script>
  export default {
    name: 'App'
  }
</script>

<!-- 样式 -->
<style>
</style>
