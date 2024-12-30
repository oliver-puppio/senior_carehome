<template>
  <el-card class="form-container" shadow="never">
    <h3 style="text-align: center; margin: 0px 0px 40px 0">新增老人信息</h3>
    <el-form
      :model="addSeniorForm"
      :rules="addSeniorRules"
      ref="addSeniorForm"
      label-position="right"
      label-width="100px"
    >
      <el-row :gutter="0">
        <el-col :span="11">
          <el-form-item label="老人姓名" prop="name">
            <el-input
              v-model="addSeniorForm.name"
              placeholder="请输入老人姓名"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="联系方式">
            <el-input
              v-model="addSeniorForm.tel"
              placeholder="请输入联系方式"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="11">
          <el-form-item label="身份证号" prop="idNum">
            <el-input
              v-model="addSeniorForm.idNum"
              placeholder="请输入身份证号"
              size="medium"
              @change="handleidNumChange"
            ></el-input> </el-form-item
        ></el-col>
        <el-col :span="6">
          <el-form-item label="性别">
            <el-input
              v-model="addSeniorForm.gender"
              disabled
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="出生年月">
            <el-input
              v-model="addSeniorForm.birth"
              disabled
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="11">
          <el-form-item label="平均心率" prop="heart">
            <el-input
              v-model="addSeniorForm.heart"
              autoComplete="off"
              placeholder="正常状态下老人平均心率"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="平均呼吸" prop="breath">
            <el-input
              v-model="addSeniorForm.breath"
              autoComplete="off"
              placeholder="正常状态下老人平均呼吸"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- <el-form-item label="老人照片" required>
        <el-upload
          action="#"
          :on-error="uploadError"
          :on-preview="uploadPreview"
          multiple
          :limit="1"
          :file-list="addSeniorForm.image"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <span slot="tip" class="el-upload__tip" style="margin-left: 10px">
            只能上传jpg/png文件，且不超过500kb
          </span>
        </el-upload>
      </el-form-item> -->

      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="紧急联系人1" prop="cont1">
            <el-input
              v-model="addSeniorForm.cont1"
              autoComplete="off"
              placeholder="请输入姓名"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="cTel1" label-width="0">
            <el-input
              v-model="addSeniorForm.cTel1"
              autoComplete="off"
              placeholder="请输入联系方式"
              size="medium"
            ></el-input> </el-form-item
        ></el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="紧急联系人2">
            <el-input
              v-model="addSeniorForm.cont2"
              autoComplete="off"
              placeholder="请输入姓名"
              size="medium"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label-width="0">
            <el-input
              v-model="addSeniorForm.cTel2"
              autoComplete="off"
              placeholder="请输入联系方式"
              size="medium"
            ></el-input> </el-form-item
        ></el-col>
      </el-row>

      <el-form-item label="入住类型" prop="type">
        <el-radio-group v-model="addSeniorForm.type">
          <el-radio label="养老院" @change="clickIn"></el-radio>
          <el-radio label="社区" @change="clickOut"></el-radio>
        </el-radio-group>
      </el-form-item>

      <div class="in-wrapper" v-show="inVisible">
        <el-form
          style="margin-top: 15px"
          label-position="right"
          label-width="100px"
          :model="listQuery"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item label="搜索楼栋">
                <el-select
                  placeholder="楼栋名称"
                  size="medium"
                  v-model="listQuery.buildingID"
                >
                  <el-option
                    v-for="item in buildingList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  >
                  </el-option> </el-select></el-form-item
            ></el-col>
            <el-col :span="8">
              <el-form-item label="房间" label-width="70px">
                <el-input
                  placeholder="房间号"
                  size="medium"
                  v-model="listQuery.roomNum"
                >
                </el-input
              ></el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item style="float: right; margin-right: 0"
                ><el-button size="medium" @click="SearchRoomData"
                  >查 看</el-button
                ></el-form-item
              ></el-col
            ></el-row
          >
        </el-form>
        <el-table
          style="width: 100%; margin-top: 20px"
          :data="roomData"
          v-show="inVisible"
          v-loading="listLoading"
          border
          max-height="250px"
        >
          <el-table-column
            label="楼栋"
            align="center"
            prop="buildingname"
          ></el-table-column>

          <el-table-column
            label="房间"
            align="center"
            prop="roomnum"
          ></el-table-column>

          <el-table-column label="床位状态" align="center" width="200px">
            <template slot-scope="scope">
              <el-radio-group
                size="small"
                v-model="scope.row.chosenbed"
                @change="clickBed(scope.$index, scope.row)"
              >
                <el-radio-button
                  v-for="bed in scope.row.states"
                  :key="bed.id"
                  :label="bed.num"
                  :disabled="bed.state == 1"
                ></el-radio-button> </el-radio-group></template
          ></el-table-column>

          <el-table-column
            label="负责人"
            align="center"
            prop="workername"
          ></el-table-column>
        </el-table>
      </div>

      <div class="out-wrapper" v-show="outVisible">
        <el-form
          style="margin-top: 15px"
          ref="chosenInfoOut"
          label-position="right"
          :model="chosenInfoOut"
          :rules="chosenOutRules"
        >
          <el-row>
            <el-col :span="10">
              <el-form-item label="社区名称" label-width="100px" prop="instID">
                <el-select
                  placeholder="请选择社区"
                  size="medium"
                  v-model="chosenInfoOut.instID"
                >
                  <el-option
                    v-for="item in communityList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="楼栋" label-width="70px" prop="buildingName">
                <el-input
                  placeholder="楼栋名称"
                  size="medium"
                  v-model="chosenInfoOut.buildingName"
                >
                </el-input></el-form-item
            ></el-col>
            <el-col :span="7">
              <el-form-item label="房间" label-width="70px" prop="roomNum">
                <el-input
                  placeholder="房间号"
                  size="medium"
                  v-model="chosenInfoOut.roomNum"
                >
                </el-input></el-form-item
            ></el-col>
          </el-row>

          <el-form-item label="设备号:" label-width="100px" prop="deviceID">
            <el-input
              placeholder="请输入设备号"
              size="medium"
              v-model="chosenInfoOut.deviceID"
            >
            </el-input
          ></el-form-item>
        </el-form>
      </div>

      <el-row class="form-footer">
        <el-col :span="6">
          <el-button plain @click="resetForm">重 置</el-button></el-col
        >
        <el-col :span="10" :offset="8">
          <el-button type="primary" style="width: 45%" @click="addSeniorConfirm"
            >确 定</el-button
          >
          <el-button type="info" style="width: 45%" @click="addSeniorCancel"
            >取 消</el-button
          ></el-col
        >
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
import { addSenior, bindBed } from "@/api/senior";
import { fetchList as fetchBuildingList } from "@/api/building";
import { fetchList as fetchRoomList } from "@/api/room";
import { fetchList as fetchCommunityList } from "@/api/community";

export default {
  name: "addSenior",

  data() {
    return {
      addSeniorForm: {
        name: "",
        tel: "",
        idNum: "",
        image: "",
        gender: "",
        birth: "",
        cont1: "",
        cTel1: "",
        cont2: "",
        cTel2: "",
        type: "",
        heart: "",
        breath: "",
      },

      addSeniorRules: {
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        idNum: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          {
            max: 18,
            min: 18,
            message: "请输入18位有效身份证号",
            trigger: "blur",
          },
        ],
        cont1: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        cTel1: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        type: [{ required: true, message: "请选择类型", trigger: "change" }],
        heart: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        breath: [{ required: true, message: "该项不能为空", trigger: "blur" }],
      },

      chosenOutRules: {
        instID: [
          { required: true, message: "该项不能为空", trigger: "change" },
        ],
        buildingName: [
          { required: true, message: "该项不能为空", trigger: "blur" },
        ],
        roomNum: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        deviceID: [
          { required: true, message: "该项不能为空", trigger: "blur" },
        ],
      },

      listQuery: {
        buildingID: null,
        roomNum: null,
      },

      inVisible: false,
      outVisible: false,
      listLoading: false,

      totalRoomData: [],
      roomData: [],

      communityList: [],

      buildingList: [],

      chosenInfoIn: {
        bedID: null,
      },

      chosenInfoOut: {
        instID: null,
        buildingName: null,
        roomNum: null,
        deviceID: null,
      },
    };
  },

  created() {
    this.getTotalRoom();
    this.getBuildingList();
    this.getCommunityList();
  },

  mounted() {},

  methods: {
    getBuildingList() {
      fetchBuildingList().then((response) => {
        console.log("fetchBuildingList().response", response);
        this.buildingList = response.data;
      });
    },

    getCommunityList() {
      fetchCommunityList().then((res) => {
        console.log("fetchCommunityList().response", res);
        this.communityList = res.data;
      });
    },

    getTotalRoom() {
      this.listLoading = true;
      fetchRoomList(0).then((response) => {
        console.log("fetchRoomList().response", response);
        this.totalRoomData = response.data;
        this.roomData = this.totalRoomData;
        this.listLoading = false;
      });
    },

    handleidNumChange() {
      var idNum = this.addSeniorForm.idNum;
      var len = idNum.length;
      if (len == 18) {
        if (idNum[len - 2] % 2) {
          this.addSeniorForm.gender = "男";
        } else {
          this.addSeniorForm.gender = "女";
        }
        this.addSeniorForm.birth =
          idNum.slice(6, 10) + "-" + idNum.slice(10, 12);
      } else {
        this.addSeniorForm.gender = null;
        this.addSeniorForm.birth = null;
      }
    },

    clickIn() {
      // console.log("clickIn", "选择养老院", this.$refs.chosenInfoOut);
      if (this.roomData === null || this.roomData.length === 0) {
        this.getTotalRoom();
      }
      this.outVisible = false;
      this.resetInfoOut();
      this.inVisible = true;
    },

    clickOut() {
      // console.log("clickOut", "选择社区");
      this.getCommunityList();

      this.inVisible = false;
      this.resetInfoIn();
      this.outVisible = true;
    },

    resetInfoOut() {
      this.$refs.chosenInfoOut.resetFields();
      this.chosenInfoOut.instID = null;
    },

    resetInfoIn() {
      this.chosenInfoIn.bedID = null;

      for (var i = 0; i < this.roomData.length; i++) {
        this.roomData[i].chosenbed = null;
      }
    },

    SearchRoomData() {},

    clickBed(index, row) {
      for (var i = 0; i < row.states.length; i++) {
        if (row.states[i].num == row.chosenbed) {
          this.chosenInfoIn.bedID = row.states[i].id;
          break;
        }
      }
      for (var i = 0; i < this.roomData.length; i++) {
        if (i != index) {
          this.roomData[i].chosenbed = null;
        }
      }
      console.log("this.chosenInfoIn", this.chosenInfoIn);
    },

    resetForm() {
      this.$refs.addSeniorForm.resetFields();
      this.addSeniorForm.tel = "";
      this.addSeniorForm.cont2 = "";
      this.addSeniorForm.cTel2 = "";
      this.resetInfoIn();
      this.resetInfoOut();

      this.listQuery.buildingID = "";
      this.listQuery.roomNum = "";
      this.inVisible = false;
      this.outVisible = false;
    },

    addSeniorConfirm() {
      var flag = true;
      if (this.addSeniorForm.type == "社区") {
        this.$refs.chosenInfoOut.validate((valid) => {
          if (!valid) {
            console.log("chosenInfoOut error! ", this.chosenInfoOut);
            flag = false;
          }
        });
      } else if (
        this.addSeniorForm.type == "养老院" &&
        this.chosenInfoIn.bedID == ""
      ) {
        this.$message({
          message: "请选择入住信息",
          type: "warning",
          duration: 5000,
        });
        flag = false;
      }
      // // 图片
      // if (
      //   this.addSeniorForm.image == [] ||
      //   this.addSeniorForm.image.length == 0
      // ) {
      //   this.$message({
      //     message: "请上传图片",
      //     type: "warning",
      //     duration: 2000,
      //   });
      //   flag = false;
      // }
      this.$refs["addSeniorForm"].validate((valid) => {
        if (!valid) {
          console.log("error submit!", this.addSeniorForm);
          flag = false;
        }
      });

      if (flag) {
        var formObj = {
          name: this.addSeniorForm.name,
          gender: this.addSeniorForm.gender,
          tel: this.addSeniorForm.tel,
          heart: this.addSeniorForm.heart,
          breath: this.addSeniorForm.breath,
          idnum: this.addSeniorForm.idNum,
          pic: this.addSeniorForm.image,
          cont1: this.addSeniorForm.cont1,
          ctel1: this.addSeniorForm.cTel1,
          cont2: this.addSeniorForm.cont2,
          ctel2: this.addSeniorForm.cTel2,
          bedID: "",
          communityID: "",
          buildingName: "",
          roomNum: "",
          deviceID: "",
          flag: "",
        };


        // console.log("老人基本信息：", this.addSeniorForm);
        if (this.addSeniorForm.type == "养老院") {
          // console.log("入住绑定类信息(院内)：", this.chosenInfoIn);
          formObj.bedID = this.chosenInfoIn.bedID;
          formObj.flag = 0;
        } else if (this.addSeniorForm.type == "社区") {
          // console.log("入住绑定类信息(社区)：", this.chosenInfoOut);
          formObj.communityID = this.chosenInfoOut.instID;
          formObj.buildingName = this.chosenInfoOut.buildingName;
          formObj.roomNum = this.chosenInfoOut.roomNum;
          formObj.deviceID = this.chosenInfoOut.deviceID;
          formObj.flag = 1;
        }
        // console.log("调接口插入新增信息，返回老人列表页面，请求参数");
        addSenior(formObj)
          .then((response) => {
            console.log("addSenior().response:", response);
            this.$message({
              message: "成功办理入住登记",
              type: "success",
            });
          })
          .catch((err) => {
            console.log("err: addSenior", err);
          });

        this.$router.push({ path: "/sms/senior" });
      }
    },

    addSeniorCancel() {
      this.resetForm();
      this.$router.push({ path: "/sms/senior" });
    },

    // uploadError() {
    //   this.$message({
    //     message: "上传失败",
    //     type: "error",
    //   });
    // },

    // uploadPreview(file) {
    //   console.log(file);
    // },
  },
};
</script>
<style scoped>
.form-container {
  width: 800px;
}
.el-card__body {
  padding: 0;
}

.form-footer {
  margin-top: 20px;
}
</style>
