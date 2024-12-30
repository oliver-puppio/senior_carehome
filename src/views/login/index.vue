<!-- login登录组件 -->
<!-- 【ok】 -->
<template>
  <div>
    <!-- login-form-layout是登录小卡片 -->
    <el-card class="login-form-layout">
      <!-- 登陆表单数据绑定【:model="loginForm"】；表单数据验证规则【:rules="loginRules"】 -->
      <el-form
        autoComplete="on"
        :model="loginForm"
        :rules="loginRules"
        ref="loginForm"
        label-position="left"
      >
        <!-- logo -->
        <div style="text-align: center">
          <svg-icon
            icon-class="login-mall"
            style="width: 56px; height: 56px; color: #409eff"
          ></svg-icon>
        </div>

        <!-- 系统名称 -->
        <h2 class="login-title color-main">智慧养老 - 夜间睡眠监测系统</h2>

        <!-- 表单组件：用户名；指定【prop="username"】使验证规则生效 -->
        <el-form-item prop="username">
          <el-input
            name="username"
            type="text"
            v-model.number="loginForm.username"
            autoComplete="on"
            placeholder="请输入用户名"
          >
            <span slot="prefix">
              <svg-icon icon-class="user" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>

        <!-- 表单组件：密码；【prop="password"】注意加在item上-->
        <el-form-item prop="password">
          <!-- v-model数据绑定为loginForm.password；:type是v-bind数据绑定，取变量pwdType -->
          <el-input
            name="password"
            :type="pwdType"
            @keyup.enter.native="handleLogin"
            v-model="loginForm.password"
            autoComplete="on"
            placeholder="请输入密码"
          >
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>

        <!-- 表单组件：按钮区域 -->
        <el-form-item style="margin-bottom: 50px; text-align: center">
          <!-- 注册 -->
          <el-button
            style="width: 45%"
            plain
            :loading="loading"
            @click.native.prevent="handleRegister"
          >
            注 册
          </el-button>

          <!-- 登录，type="primary"蓝色按钮（info灰色） -->
          <el-button
            style="width: 45%"
            type="primary"
            :loading="loading"
            @click.native.prevent="handleLogin"
          >
            登 录
          </el-button>

          <!-- <el-button
            style="width: 45%"
            type="info"
            @click.native.prevent="resetLoginForm"
          >
            重置
          </el-button> -->
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 背景中的蓝色底图 -->
    <img :src="login_center_bg" class="login-center-layout" />

    <el-dialog
      title="养老院注册"
      :visible.sync="registerVisible"
      :show-close="true"
      :center="true"
      width="40%"
      top="4vh"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <!-- 注册表单 -->
      <el-form
        :model="registerForm"
        :rules="registerRules"
        ref="registerForm"
        label-width="100px"
      >
        <el-form-item label="养老院名称" prop="name">
          <el-input
            v-model="registerForm.name"
            autoComplete="off"
            placeholder="请输入养老院名称"
          ></el-input>
        </el-form-item>

        <el-form-item label="所在区域" required>
          <el-col :span="7" style="margin-right: 20px">
            <el-form-item prop="province">
              <el-select
                v-model="registerForm.province"
                placeholder="请选择省份"
                @change="setProv"
              >
                <el-option
                  v-for="prov of prov_list"
                  :key="prov.code"
                  :value="prov.name"
                  :label="prov.name"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7" style="margin-right: 20px">
            <el-form-item prop="city">
              <el-select
                v-model="registerForm.city"
                placeholder="请选择市"
                @change="handleShi"
              >
                <el-option
                  v-for="city in city_list"
                  :key="city.code"
                  :value="city.name"
                  :label="city.name"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item prop="area">
              <el-select v-model="registerForm.area" placeholder="请选择县/区">
                <el-option
                  v-for="area in area_list"
                  :key="area.code"
                  :value="area.name"
                  :label="area.name"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>

        <el-form-item
          label="详细地址"
          prop="detail"
          style="margin-bottom: 10px"
        >
          <el-input
            v-model="registerForm.detail"
            autoComplete="off"
            placeholder="请输入详细地址"
          ></el-input>
        </el-form-item>

        <el-form-item label="当前地址" style="margin-bottom: 10px">
          <div>{{ fullAddress }}</div>
        </el-form-item>

        <el-form-item label="公司名称" prop="cname">
          <el-input
            v-model="registerForm.cname"
            autoComplete="off"
            placeholder="请输入公司名称"
          ></el-input>
        </el-form-item>

        <!-- 上传营业执照 -->
        <!-- <el-form-item label-width="0" required>
          <el-upload
            class="upload-demo"
            action="#"
            :on-error="uploadError"
            :on-preview="uploadPreview"
            multiple
            :limit="1"
            :file-list="registerForm.licenseFile"
          >
            <el-button type="primary" plain>上传营业执照</el-button>
          </el-upload>
        </el-form-item> -->

        <el-row :gutter="20">
          <el-col :span="13"
            ><el-form-item label="负责人" prop="leader">
              <el-input
                v-model="registerForm.leader"
                autoComplete="off"
                placeholder="请输入负责人姓名"
              ></el-input> </el-form-item
          ></el-col>
          <el-col :span="11"
            ><el-form-item label-width="0" prop="tel">
              <el-input
                v-model="registerForm.tel"
                autoComplete="off"
                placeholder="请输入负责人联系方式"
              ></el-input> </el-form-item
          ></el-col>
        </el-row>

        <el-form-item label="设置密码" prop="password">
          <el-input
            v-model="registerForm.password"
            placeholder="请输入密码"
            show-password
          >
          </el-input>
        </el-form-item>

        <el-form-item label="验证码" prop="code">
          <el-col :span="10">
            <el-input
              v-model="registerForm.code"
              auto-complete="off"
              placeholder="请输入验证码"
              size=""
            ></el-input>
          </el-col>
          <el-col :span="11" style="margin-left: 15px">
            <div class="login-code" width="100%" @click="refreshCode">
              <!--验证码组件-->
              <identify :identifyCode="identifyCode"></identify>
            </div>
          </el-col>
        </el-form-item>
      </el-form>

      <el-row slot="footer" class="dialog-footer">
        <el-col :span="6">
          <el-button plain @click.native.prevent="resetRegisterForm"
            >重 置</el-button
          ></el-col
        >
        <el-col :span="10" :offset="8">
          <el-button
            type="primary"
            @click.native.prevent="RegisterConfirm('registerForm')"
            style="width: 45%"
            >确 定</el-button
          >
          <el-button
            type="info"
            @click.native.prevent="RegisterCancel"
            style="width: 45%"
            >取 消</el-button
          ></el-col
        >
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { setSupport, getSupport, setCookie, getCookie } from "@/utils/support";
import login_center_bg from "@/assets/images/login_center_bg.png";
import cityJson from "../../citys.json";
import Identify from "../../identify";
import { login, test, register } from "@/api/login";

export default {
  name: "login",
  data() {
    const validatePass = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error("密码不能小于3位"));
      } else {
        callback();
      }
    };

    return {
      cityJson,
      loginForm: {
        // 默认都为空
        username: "",
        password: "",
      },
      // 验证规则对象
      loginRules: {
        /* 【required: true】必填，可设【message:'……'】错误提示（不符合该行定义的规则就会显示）；
           【trigger: "blur"】表示鼠标失去焦点时触发；
           【validator: validateUsername】自定义验证规则
            */
        username: [
          { required: true, trigger: "blur", message: "请输入正确的用户名" },
          { type: "number", trigger: "blur", message: "用户名只能为数字" },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePass },
        ],
      },
      loading: false,
      pwdType: "password", // 密码框默认类型是password，即不可见
      login_center_bg,
      // supportDialogVisible: false,
      registerVisible: false,

      registerForm: {
        name: "",
        loc: "",
        cname: "",
        licenseFile: "",

        leader: "",
        tel: "",
        code: "",

        province: "",
        city: "",
        area: "",
        detail: "",

        password: "",
      },

      prov_list: [],
      city_list: [],
      area_list: [],

      identifyCodes: "1234567890abcdefjhijklinopqrsduvwxyz", //随机串内容
      identifyCode: "",

      registerRules: {
        name: [
          { required: true, message: "养老院名称不能为空", trigger: "blur" },
        ],
        province: [
          { required: true, message: "省份不能为空", trigger: "change" },
        ],
        city: [{ required: true, message: "市不能为空", trigger: "change" }],
        area: [{ required: true, message: "县/区不能为空", trigger: "change" }],
        detail: [
          { required: true, message: "详细地址不能为空", trigger: "blur" },
        ],
        cname: [
          { required: true, message: "公司名称不能为空", trigger: "blur" },
        ],
        leader: [
          { required: true, message: "负责人姓名不能为空", trigger: "blur" },
        ],
        tel: [
          {
            required: true,
            message: "联系方式不能为空",
            trigger: "blur",
          },
          { min: 8, max: 11, message: "请输入8-11位字符", trigger: "blur" },
        ],
        // 验证码【提交时单独校验】
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
        ],
      },
    };
  },
  components: { Identify },

  mounted() {
    // console.log(cityJson);
    this.handleGetCitys();

    // 初始化验证码
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  },

  computed: {
    fullAddress: function () {
      if (this.registerForm.province === this.registerForm.city) {
        this.registerForm.loc =
          this.registerForm.province +
          "-" +
          this.registerForm.area +
          "-" +
          this.registerForm.detail;
        return this.registerForm.loc;
      } else {
        this.registerForm.loc =
          this.registerForm.province +
          "-" +
          this.registerForm.city +
          "-" +
          this.registerForm.area +
          "-" +
          this.registerForm.detail;
        return this.registerForm.loc;
      }
    },
  },

  created() {
    console.log("login created and localstroage cleared");
    localStorage.clear();
    this.loginForm.username = "";
    this.loginForm.password = "";
  },

  methods: {
    showPwd() {
      if (this.pwdType === "password") {
        this.pwdType = "";
      } else {
        this.pwdType = "password";
      }
    },

    setProv() {
      this.handleSheng();
      this.handleShi();
    },

    // 登录
    handleLogin() {
      console.log("handle Login");
      // 登录前的表单预验证，通过$refs.loginForm获取表单对象
      localStorage.clear();
      this.$refs.loginForm.validate((valid) => {
        console.log("validated");
        if (valid) {
          // let isSupport = getSupport();
          // if(isSupport===undefined||isSupport==null){
          //   this.dialogVisible =true;
          //   return;
          // }
          this.loading = true;
          login(this.loginForm.username, this.loginForm.password)
            .then((response) => {
              this.loading = false;
              console.log("登录成功,返回结果response为:", response);
              localStorage.setItem("token", response.data.token);
              this.$router.push({ path: "/" });
            })
            .catch((e) => {
              console.log(e);
              this.loading = false;
              // this.$message({
              //   type:'warning',
              //   message:'登录失败，请重新登录'
              // })
            });
        } else {
          console.log("参数验证不合法！");
          return false;
        }
      });
    },

    // 注册弹窗
    handleRegister() {
      this.registerVisible = true;
    },

    // 确认注册
    RegisterConfirm(formName) {
      var flag = true;
      // 验证码检查
      if (
        this.registerForm.code.toLowerCase() !== this.identifyCode.toLowerCase()
      ) {
        this.$message({
          message: "验证码错误！",
          type: "warning",
        });
        this.refreshCode();
        flag = false;
      }
      // // 上传图片
      // if (
      //   this.registerForm.licenseFile == [] ||
      //   this.registerForm.licenseFile.length == 0
      // ) {
      //   this.$message({
      //     message: "请上传营业执照",
      //     type: "warning",
      //     duration: 1500,
      //   });
      //   flag = false;
      // }
      // 其他表单验证
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          console.log("error submit!");
          console.log(this.registerForm);
          flag = false;
        }
      });

      // 三个都通过的情况下：
      if (flag) {
        console.log(this.registerForm);
        // 调用注册接口，根据是否注册成功，设定success和account
        // register({
        //   header: { "content-type": "application/json" },
        //   data: this.registerForm,
        // })
        var formObj = {
          cname: this.registerForm.cname,
          license: this.registerForm.licenseFile,
          password: this.registerForm.password,
          name: this.registerForm.name,
          loc: this.registerForm.loc,
          leader: this.registerForm.leader,
          tel: this.registerForm.tel,
        };
        register(formObj)
          .then((response) => {
            console.log("Register response is ", response);
            var account = response.data;
            this.$message({
              message: "注册成功！您的账号是：" + account,
              type: "success",
              duration: 0,
              showClose: true,
            });
            this.registerVisible = false;
            setSupport(true);
          })
          .catch((err) => {
            this.$message({
              message: "注册失败",
              type: "error",
            });
          });
      } else {
        console.log("表单验证不通过！");
      }
    },
    // 取消注册
    RegisterCancel() {
      this.resetRegisterForm(); // 重置表单
      this.registerVisible = false; // 隐藏弹窗
      setSupport(true);
    },

    // 注册表单重置
    resetRegisterForm() {
      this.refreshCode(); // 刷新验证码
      this.city_list = [];
      this.area_list = [];
      this.$refs["registerForm"].resetFields();
    },

    handleGetCitys() {
      this.prov_list = cityJson;
    },
    handleSheng() {
      this.city_list = [];
      this.area_list = [];
      if (this.prov_list.length > 0) {
        this.prov_list.forEach((item) => {
          if (this.registerForm.province === item.name) {
            console.log(item.name);
            this.city_list = Object.assign([], item.cityList);
            // this.registerForm.city = this.city_list[0].name;
            this.registerForm.city = "";
            this.registerForm.area = "";
          }
        });
      }
    },
    handleShi() {
      if (this.city_list.length > 0) {
        this.city_list.forEach((item) => {
          if (this.registerForm.city === item.name) {
            this.area_list = Object.assign([], item.areaList);
            // this.registerForm.area = this.area_list[0].name;
            this.registerForm.area = "";
          }
        });
      }
    },

    uploadError() {
      this.$message({
        message: "上传失败",
        type: "error",
      });
    },

    uploadPreview(file) {
      console.log(file);
    },

    // 验证码
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode +=
          this.identifyCodes[this.randomNum(0, this.identifyCodes.length)];
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
  },
};
</script>

<!-- scoped控制以下样式只在当前组件生效（防止组件之间样式冲突） -->
<style scoped>
.login-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 400px;
  margin: 140px auto;
  border-top: 10px solid #409eff;
}

.login-title {
  text-align: center;
}

.login-center-layout {
  background: #409eff;
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  margin-top: 200px;
}
</style>
