<!-- 顶部栏【ok】 -->
<template>
  <el-menu class="navbar" mode="horizontal">
    <!-- hamburger：展开/收起侧边栏 -->
    <hamburger
      class="hamburger-container"
      :toggleClick="toggleSideBar"
      :isActive="sidebar.opened"
    ></hamburger>
    <breadcrumb></breadcrumb>

    <el-button class="exit-button" type="text" @click="logout"
      >退出账号</el-button
    >
    <!-- <router-link class="exit-button" to="/404"
      >退出账号</router-link
    > -->
  </el-menu>
</template>

<script>
import { mapGetters } from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import Hamburger from "@/components/Hamburger";

export default {
  components: {
    Breadcrumb,
    Hamburger,
  },
  computed: {
    ...mapGetters(["sidebar"]),
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch("ToggleSideBar");
    },

    // 退出账号
    logout() {
      this.$confirm("确定退出系统吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "已退出！",
          });
          localStorage.clear();
          console.log("It should have been directed to login page")
          this.$router.push("/login")
          location.reload(); // 为了重新实例化vue-router对象 避免bug
          // this.$store.dispatch("LogOut").then(() => {
          //   location.reload(); // 为了重新实例化vue-router对象 避免bug
          // });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }

  .exit-button {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
  }
}
</style>

