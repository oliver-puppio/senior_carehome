<template>
  <div class="app-container room-info" shadow="never">
    <el-card class="operate-container" shadow="never" style="margin-top: 0px">
      <div class="title-wrapper" style="height: 20px">
        <div class="titleInfo">
          <i class="el-icon-tickets"></i>
          <span style="margin-left: 10px">房间详情</span>
        </div>

        <el-form
          class="room-info-wrapper"
          :model="roomInfo"
          ref="roomInfo"
          label-position="right"
          label-width="80px"
        >
          <el-row :gutter="16">
            <el-col :span="6">
              <el-form-item label="房间号">
                <el-input
                  v-model="roomInfo.room.num"
                  placeholder="房间号"
                  size="medium"
                  disabled
                ></el-input> </el-form-item
            ></el-col>
            <el-col :span="4">
              <el-form-item label="容量" label-width="60px">
                <el-input
                  v-model="roomInfo.room.size"
                  placeholder="容量"
                  size="medium"
                  disabled
                ></el-input></el-form-item
            ></el-col>
            <el-col :span="12">
              <el-form-item label="所在位置" label-width="90px">
                <el-input
                  v-model="roomInfo.room.buildingname"
                  placeholder=""
                  size="medium"
                  disabled
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row :gutter="16">
            <el-col :span="8">
              <el-form-item label="负责护工">
                <el-input
                  v-model="roomInfo.workername"
                  placeholder="负责护工"
                  disabled
                  size="medium"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="护工电话" label-width="100px">
                <el-input
                  v-model="roomInfo.workertel"
                  placeholder="护工电话"
                  disabled
                  size="medium"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>

    <el-card
      class="room-senior-container"
      shadow="never"
      style="margin-top: 20px"
    >
      <i class="el-icon-search"></i>
      <span style="margin-left: 10px">床位信息</span>
    </el-card>

    <el-table
      ref="roomSeniorTable"
      :data="list"
      style="width: 100%; margin-top: 20px"
      border
    >
      <el-table-column label="床位" align="center" width="100">
        <template slot-scope="scope">{{ scope.row.bedNum }}</template>
      </el-table-column>

      <el-table-column label="设备编号" align="center" width="160">
        <template slot-scope="scope">{{ scope.row.deviceID }}</template>
      </el-table-column>

      <el-table-column label="姓名" align="center" width="140">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>

      <el-table-column label="性别" align="center" width="100">
        <template slot-scope="scope">{{ scope.row.gender }}</template>
      </el-table-column>

      <el-table-column label="年龄" align="center" width="100">
        <template slot-scope="scope">{{
          scope.row.idNum ? 2022 - scope.row.idNum.slice(6, 10) : ""
        }}</template>
      </el-table-column>

      <el-table-column label="联系方式" align="center">
        <template slot-scope="scope">{{ scope.row.tel }}</template>
      </el-table-column>

      <el-table-column label="紧急联系人" align="center" width="140">
        <template slot-scope="scope">{{ scope.row.cont1 }}</template>
      </el-table-column>

      <el-table-column label="紧急电话" align="center">
        <template slot-scope="scope">{{ scope.row.CTel1 }}</template>
      </el-table-column>

      <el-table-column label="操作" width="210" align="center">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="handleDeviceBind(scope.$index, scope.row)"
            >设备绑定
          </el-button>
          <el-button
            size="small"
            type="primary"
            @click="handleSeniorDetail(scope.$index, scope.row)"
            >老人详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="绑定设备"
      :visible.sync="dialogVisible"
      width="30%"
      top="25vh"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <el-form
          :model="addDeviceForm"
          :rules="addDeviceRules"
          ref="addDeviceForm"
          label-width="130px"
        >
          <el-form-item label="请输入设备号：" prop="id">
            <el-input
              v-model.number="addDeviceForm.id"
              autoComplete="off"
              placeholder="设备唯一序列号"
              style="width: 270px"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer">
        <el-row style="padding: 0 10px">
          <el-button
            type="primary"
            @click="addConfirm()"
            style="float: right; margin-left: 20px"
            >确 定</el-button
          >
          <el-button @click="addCancel()" style="float: right" plain
            >取 消</el-button
          >
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
// import { getProduct, updateProduct } from "@/api/product";
import { fetchRoomDetail } from "@/api/room";

const defaultRoomInfo = {
  roomID: "",
  roomNum: "",
  size: 4,
  location: "花好月圆楼",
  workerName: "陈科航",
  workerTel: "14444444444",
};

export default {
  name: "SeniorDetail",
  props: {
    // isEdit: {
    //   type: Boolean,
    //   default: false,
    // },
  },
  data() {
    return {
      dialogVisible: false,
      addDeviceForm: {
        bedID: null,
        id: null,
        oldID: null,
      },
      addDeviceRules: {
        id: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          { type: "number", trigger: "blur", message: "请输入整数的设备号" },
        ],
      },

      roomInfo: Object.assign({}, defaultRoomInfo),

      list: [
        {
          bedID: null,
          deviceID: null,
          seniorID: 26,
          bedNum: null,
          name: null,
          gender: null,
          idNum: "",
          tel: null,
          cont1: null,
          cTel1: null,
        },
      ],
    };
  },
  created() {
    // console.log(imgUrl);
    console.log("this.$route.query.id", this.$route.query.id);
    fetchRoomDetail(this.$route.query.id).then((res) => {
      console.log("fetchRoomDetail, res: ", res);
      this.roomInfo = res.data;
      this.list = res.data.bedList;
    });
  },
  methods: {
    addConfirm() {
      this.$refs.addDeviceForm.validate((valid) => {
        if (valid) {
          console.log("输入的id:", this.addDeviceForm.id);
          // 调用接口，插入成功后：
          ditchDevice(this.addDeviceForm.oldID, 2)
            .then((res) => {
              

              this.getTotalList();
              this.$refs.addDeviceForm.resetFields();
              this.dialogVisible = false;
            })
            .catch((err) => {
              console.log("err");
              this.$message({
                message: "设备号已存在！",
                type: "warning",
              });
              this.dialogVisible = true;
            });
        }
      });
    },
    // 设备绑定按钮
    handleDeviceBind(index, row) {
      this.dialogVisible = true;
      this.addDeviceForm.bedID = row.bedID;
      this.addDeviceForm.oldID = row.deviceID;
    },

    addCancel() {
      this.$refs.addDeviceForm.resetFields();
      this.dialogVisible = false;
    },

    handleSeniorDetail(index, row) {
      this.$router.push({
        path: "/sms/seniorDetail",
        query: { id: row.seniorID },
      });
    },
  },
};
</script>
<style scope>
.room-info-wrapper {
  width: 60%;
}

.el-input.is-disabled .el-input__inner {
  /* background-color: #fcfcfc; */
  border-color: #e4e7ed;
  color: #5c5c5c;
  cursor: auto;
}

.room-info-wrapper {
  margin-top: 20px;
  padding: 10px 10px 10px 0;
}

.titleInfo {
  display: inline-block;
}

.image-wrapper .el-form-item__content {
  padding-top: 4px;
  padding-left: 60px;
}

.seniorImg {
  object-fit: cover;
  width: 120px;
  height: 160px;
  border-radius: 5px;
}

.monitor-container {
  margin: 20px 0;
}

.btn-info {
  float: right;
}

.btn-cancel {
  float: right;
}
</style>


