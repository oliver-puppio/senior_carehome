<template>
  <el-card class="form-container" shadow="never">
    <el-button
      class="btn-edit"
      @click="isEdit ? saveChange() : editAccount()"
      type="primary"
      size="small"
    >
      {{ isEdit ? "保 存" : "编 辑" }}
    </el-button>

    <el-button
      class="btn-edit"
      @click="resetInfoForm()"
      v-show="isEdit"
      size="small"
      style="margin-right: 10px"
    >
      取 消
    </el-button>

    <h3 style="text-align: center; margin: 0px 0px 40px 0">我的账号</h3>
    <el-form
      class="account-info"
      :model="accountForm"
      :rules="accountRules"
      ref="accountForm"
      label-width="100px"
      :hide-required-asterisk="!isEdit"
    >
      <el-form-item label="养老院名称" prop="name">
        <el-input
          v-model="accountForm.name"
          placeholder="请输入养老院名称"
          :disabled="!isEdit"
        ></el-input>
      </el-form-item>

      <el-form-item label="所在区域" required>
        <el-col :span="8" style="margin-right: 20px">
          <el-form-item prop="province">
            <el-select
              v-model="accountForm.province"
              placeholder="请选择省份"
              @change="setProv"
              :disabled="!isEdit"
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
              v-model="accountForm.city"
              placeholder="请选择市"
              @change="handleShi"
              :disabled="!isEdit"
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
            <el-select
              v-model="accountForm.area"
              placeholder="请选择县/区"
              :disabled="!isEdit"
            >
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

      <el-form-item label="详细地址" prop="detail" style="margin-bottom: 10px">
        <el-input
          v-model="accountForm.detail"
          autoComplete="off"
          placeholder="请输入详细地址"
          :disabled="!isEdit"
        ></el-input>
      </el-form-item>

      <el-form-item label="当前地址" style="margin-bottom: 10px">
        <div>{{ fullAddress }}</div>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="18"
          ><el-form-item label="公司名称" prop="firm_name">
            <el-input
              v-model="accountForm.firm_name"
              autoComplete="off"
              placeholder="请输入公司名称"
              :disabled="!isEdit"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <!-- 上传营业执照 -->
          <el-form-item label-width="0" required>
            <el-upload
              class="upload-demo"
              action="#"
              :on-error="uploadError"
              :on-preview="uploadPreview"
              multiple
              :limit="1"
              :file-list="accountForm.licenseFile"
            >
              <!-- <el-button type="primary" plain :disabled="!isEdit"
                >上传营业执照</el-button
              > -->
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="13"
          ><el-form-item label="负责人" prop="leader">
            <el-input
              v-model="accountForm.leader"
              autoComplete="off"
              placeholder="请输入负责人姓名"
              :disabled="!isEdit"
            ></el-input> </el-form-item
        ></el-col>
        <el-col :span="11"
          ><el-form-item label-width="0" prop="tel">
            <el-input
              v-model="accountForm.tel"
              autoComplete="off"
              placeholder="请输入负责人联系方式"
              :disabled="!isEdit"
            ></el-input> </el-form-item
        ></el-col>
      </el-row>

      <el-form-item label="账号密码" prop="">
        <el-input
          v-model="accountForm.password"
          placeholder="您可以重新设置密码"
          show-password
          :disabled="!isEdit"
        >
        </el-input>
      </el-form-item>

      <el-form-item label="验证码" prop="code" v-show="isEdit">
        <el-col :span="10">
          <el-input
            v-model="accountForm.code"
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
  </el-card>
</template>

<script>
import cityJson from "../../citys.json";
import Identify from "../../identify";
import { query, update } from "../../api/account";

export default {
  name: "accountInfo",
  components: { Identify },
  data() {
    return {
      cityJson,

      isEdit: false,

      accountInfo: {},
      accountForm: {},

      prov_list: [],
      city_list: [],
      area_list: [],

      identifyCodes: "1234567890abcdefjhijklinopqrsduvwxyz", //随机串内容
      identifyCode: "",

      accountRules: {
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
        firm_name: [
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
      },
    };
  },

  computed: {
    fullAddress: function () {
      this.accountForm.address =
        this.accountForm.province +
        "-" +
        this.accountForm.city +
        "-" +
        this.accountForm.area +
        "-" +
        this.accountForm.detail;
      return this.accountForm.address;
    },
  },

  created() {
    console.log("account info created");
    this.getInfo();
  },
  mounted() {
    // console.log(cityJson);
    this.handleGetCitys();

    // 初始化验证码
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  },

  methods: {
    getInfo() {
      query().then((response) => {
        this.accountInfo = response.data;
        this.accountForm = Object.assign({}, this.accountInfo);
        this.accountForm.code = "";
        // this.accountForm.password = "";
      });
    },

    resetInfoForm() {
      this.isEdit = false;
      this.accountForm = Object.assign({}, this.accountInfo);
      this.accountForm.code = "";
      // this.accountForm.password = "";
      this.$refs.accountForm.clearValidate();
      this.refreshCode();
    },

    setProv() {
      this.handleSheng();
      this.handleShi();
    },
    handleGetCitys() {
      this.prov_list = cityJson;
    },
    handleSheng() {
      this.city_list = [];
      this.area_list = [];
      if (this.prov_list.length > 0) {
        this.prov_list.forEach((item) => {
          if (this.accountForm.province === item.name) {
            console.log(item.name);
            this.city_list = Object.assign([], item.cityList);
            this.accountForm.city = "";
            this.accountForm.area = "";
          }
        });
      }
    },
    handleShi() {
      if (this.city_list.length > 0) {
        this.city_list.forEach((item) => {
          if (this.accountForm.city === item.name) {
            this.area_list = Object.assign([], item.areaList);
            this.accountForm.area = "";
          }
        });
      }
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

    uploadError() {
      this.$message({
        message: "上传失败",
        type: "error",
      });
    },

    uploadPreview(file) {
      console.log(file);
    },

    // 编辑
    editAccount() {
      this.isEdit = true;
      // console.log(this.isEdit);
    },

    // 保存更改
    saveChange() {
      console.log("当前表单内容：", this.accountForm);
      var flag = true;
      // 验证码检查
      if (
        this.accountForm.code.toLowerCase() !== this.identifyCode.toLowerCase()
      ) {
        this.$message({
          message: "验证码错误！",
          type: "warning",
        });
        this.refreshCode();
        flag = false;
      }
      // 其他表单验证
      this.$refs.accountForm.validate((valid) => {
        if (!valid) {
          console.log("error submit!");
          flag = false;
        }
      });

      // 三个都通过的情况下：
      if (flag) {
        console.log(this.accountForm);
        var obj = {
          cname: this.accountForm.firm_name,
          license: "",
          password: this.accountForm.password,
          name: this.accountForm.name,
          loc: this.accountForm.address,
          leader: this.accountForm.leader,
          tel: this.accountForm.tel,
        };
        // 调用注册接口，根据是否返回结果，设定success和account
        var success = true;
        update(obj)
          .then((response) => {})
          .catch(() => {
            success = false;
          });
        if (success) {
          this.isEdit = !this.isEdit;
          this.$message({
            message: "信息修改成功",
            type: "success",
          });
          this.getInfo();
          this.refreshCode();
        } else {
          this.$message({
            message: "修改失败",
            type: "error",
          });
        }
      } else {
        console.log("表单验证不通过！");
      }
    },
  },
};
</script>
<style scoped>
.btn-edit {
  float: right;
  margin-top: -4px;
}
.form-container {
  width: 700px;
}
.el-card__body {
  padding: 0;
}

.form-footer {
  margin-top: 20px;
}
</style>
