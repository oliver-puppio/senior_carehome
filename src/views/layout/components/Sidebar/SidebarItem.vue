<template>
  <!-- menu-wrapper是左边菜单列表内的div -->
  <div class="menu-wrapper">
    <template v-for="item in routes">
      <router-link
        v-if="!item.hidden && item.children"
        :to="item.path + '/' + item.children[0].path"
        :key="item.children[0].name"
      >
        <el-menu-item
          :index="item.path + '/' + item.children[0].path"
          :class="{ 'submenu-title-noDropdown': !isNest }"
        >
          <svg-icon
            v-if="item.meta && item.meta.icon"
            :icon-class="item.meta.icon"
          ></svg-icon>
          <span v-if="item.meta && item.meta.title" slot="title">{{
            item.meta.title
          }}</span>
        </el-menu-item>
      </router-link>
    </template>
  </div>
</template>

<script>
export default {
  name: "SidebarItem",
  props: {
    routes: {
      type: Array,
    },
    isNest: {
      type: Boolean,
      default: false,
    },
  },

  created(){
    // console.log("SidebarItem created",this.routes);
  },



  methods: {

  },
};
</script>
