<template>
  <div class="app-container room-info" shadow="never">
    <el-card class="operate-container" shadow="never" style="margin-top: 0px">
      <div class="title-wrapper" style="height: 20px">
        <div class="titleInfo">
          <i class="el-icon-tickets"></i>
          <span style="margin-left: 10px">护工详情</span>
        </div>
        <el-button
          class="btn-info"
          @click="isEdit ? saveChange() : clickEdit()"
          type="primary"
        >
          {{ isEdit ? "保 存" : "编 辑" }}
        </el-button>

        <el-button
          class="btn-info"
          @click="resetInfoForm()"
          v-show="isEdit"
          style="margin-right: 10px"
        >
          取 消
        </el-button>
        <el-form
          class="worker-info-wrapper"
          :model="workerForm"
          :rules="workerRules"
          ref="workerForm"
          label-position="right"
          label-width="100px"
          style="margin-top: 40px; padding-right: 50px"
          :hide-required-asterisk="!isEdit"
        >
          <el-row :gutter="16">
            <el-col :span="6">
              <el-form-item label="护工姓名" prop="name">
                <el-input
                  v-model="workerForm.name"
                  placeholder="护工姓名"
                  size="medium"
                  :disabled="!isEdit"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="联系方式" prop="tel">
                <el-input
                  v-model="workerForm.tel"
                  placeholder="请输入联系方式"
                  size="medium"
                  :disabled="!isEdit"
                ></el-input> </el-form-item
            ></el-col>
            <el-col :span="8">
              <el-form-item label="工号" label-width="80px">
                <el-input
                  v-model="workerForm.num"
                  placeholder="护工工号"
                  size="medium"
                  disabled
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row :gutter="16">
            <el-col :span="11">
              <el-form-item label="身份证号" prop="idnum">
                <el-input
                  v-model="workerForm.idnum"
                  placeholder="请输入身份证号"
                  size="medium"
                  @change="handleidNumChange"
                  :disabled="!isEdit"
                ></el-input></el-form-item
            ></el-col>
            <el-col :span="5">
              <el-form-item label="性别" label-width="60px">
                <el-input
                  v-model="workerForm.gender"
                  placeholder=""
                  disabled
                  size="medium"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never" style="margin-top: 20px">
      <i class="el-icon-search"></i>
      <span style="margin-left: 10px">负责房间</span>
    </el-card>

    <el-tabs type="card" style="margin-top: 20px" class="worker-detail-tab">
      <el-tab-pane label="院内">
        <div class="table-container">
          <el-table
            ref="inRoomTable"
            :data="inRoomList"
            style="width: 100%"
            v-loading="listLoading"
            border
          >
            <el-table-column label="编号" width="200" align="center">
              <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>

            <el-table-column label="楼栋" width="300" align="center">
              <template slot-scope="scope">{{
                scope.row.buildingname
              }}</template>
            </el-table-column>

            <el-table-column label="房间号" width="200" align="center">
              <template slot-scope="scope">{{ scope.row.num }}</template>
            </el-table-column>

            <el-table-column label="床位状态" align="center">
              <template slot-scope="scope">
                <div class="bed-state-wrapper">
                  <div v-for="bed in scope.row.bedState" :key="bed.id">
                    <div class="bed-state-empty" v-if="bed.state == false">
                      <span> {{ bed.num }}</span>
                    </div>
                    <div class="bed-state-occupied" v-else>
                      <span> {{ bed.num }}</span>
                    </div>
                  </div>
                </div>
              </template>
            </el-table-column>

            <el-table-column label="操作" width="260" align="center">
              <template slot-scope="scope">
                <el-row>
                  <el-button
                    size="small"
                    style="width: 70px"
                    @click="handleRoomDetail(scope.$index, scope.row)"
                    >详 情
                  </el-button>
                </el-row>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="社区">
        <!-- 循环遍历所有社区信息以及社区下的老人 -->
        <div
          class="room-out-wrapper"
          v-for="community in outRoomList"
          :key="community.id"
        >
          <div class="community-info">
            <div class="community-name-loc" style="width: 480px">
              <svg-icon
                icon-class="login-mall"
                style="width: 16px; color: gray"
              ></svg-icon>
              {{ community.name }}
              <div class="community-loc">{{ community.loc }}</div>
            </div>
            <div class="community-leader" style="width: 200px">
              社区主任：{{ community.leader }}
            </div>
            <div class="community-leader-tel" style="width: 200px">
              电话：{{ community.tel }}
            </div>
            <div class="community-size" style="width: 130px; text-align: right">
              接入数：{{ community.room_list.length }}
            </div>
          </div>
          <div class="table-wrapper">
            <el-table
              :data="community.room_list"
              style="width: 100%"
              border
              max-height="300px"
            >
              <el-table-column
                prop="id"
                align="center"
                label="编号"
                width="160px"
              >
              </el-table-column>
              <el-table-column
                prop="buildingname"
                align="center"
                label="楼栋"
                width="200px"
              >
              </el-table-column>
              <el-table-column
                prop="num"
                align="center"
                label="房间号"
                width="180px"
              >
              </el-table-column>
              <el-table-column prop="size" align="center" label="接入人数">
              </el-table-column>
              <!-- <el-table-column
                prop="worker"
                align="center"
                label="负责护工"
                width="200px"
              >
              </el-table-column>
              <el-table-column
                prop="workerTel"
                align="center"
                label="护工电话"
                width="220px"
              >
              </el-table-column> -->
              <el-table-column align="center" label="操作" width="180px">
                <template slot-scope="scope">
                  <el-button
                    style="width: 70px"
                    size="small"
                    @click="handleRoomDetail(scope.$index, scope.row)"
                    >详 情
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <hr style="margin: 30px 0px" />
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import {
  updateWorker,
  queryWorker,
  roomList,
  assignRoom,
} from "../../api/worker";

export default {
  name: "workerDetail",
  props: {
    edit: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      isEdit: this.edit,
      workerInfo: null,
      workerForm: null,
      workerRules: {
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        tel: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        idnum: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          {
            max: 18,
            min: 18,
            message: "请输入18位有效身份证号",
            trigger: "blur",
          },
        ],
      },

      inRoomList: [],

      outRoomList: [],
    };
  },
  created() {
    console.log("this.$route.query.id", this.$route.query.id);
    this.getWorkerInfo(this.$route.query.id);

    roomList(this.$route.query.id).then((res) => {
      this.inRoomList = res.data[0].room_list;
      this.outRoomList = res.data[1];
    });
  },
  methods: {
    getWorkerInfo(id) {
      queryWorker(id).then((res) => {
        this.workerInfo = res.data;
        this.workerForm = Object.assign({}, this.workerInfo);
      });
    },

    // 编辑
    clickEdit() {
      this.isEdit = !this.isEdit;
    },

    resetInfoForm() {
      this.isEdit = false;
      this.workerForm = Object.assign({}, this.workerInfo);
      this.$refs.workerForm.clearValidate();
    },

    // 保存更改
    saveChange() {
      // this.isEdit = !this.isEdit;
      this.$refs.workerForm.validate((valid) => {
        if (!valid) {
          console.log("error submit!");
        } else {
          const id = this.workerForm.id;
          updateWorker(this.workerForm.id, this.workerForm)
            .then((res) => {
              this.$message({
                message: "保存成功！",
                type: "success",
              });
              this.getWorkerInfo(id);
              this.isEdit = false;
            })
            .catch((err) => {
              console.log(err);
              this.$message({
                message: "保存失败！",
                type: "error",
              });
            });
        }
      });
    },

    // 查看房间详情
    handleRoomDetail(index, row) {
      console.log("handleRoomDetail", row);
      console.log(
        "这里需要跳转房间信息详情页，调用【this.$router.push({path:'/rms/roomDetail',query:{id:row.id}})】path路径，query:参数对象列表"
      );
      this.$router.push({ path: "/rms/roomDetail", query: { id: row.roomID } });
    },

    //身份证号改变时执行的方法
    handleidNumChange() {
      var idnum = this.workerForm.idnum;
      var len = idnum.length;
      if (len == 18) {
        if (idnum[len - 2] % 2) {
          this.workerForm.gender = "男";
        } else {
          this.workerForm.gender = "女";
        }
      } else {
        this.workerForm.gender = null;
      }
    },
  },
};
</script>
<style scope>
.bed-state-wrapper {
  display: flex;
  justify-content: center;
}

.bed-state-empty,
.bed-state-occupied {
  width: 38px;
  height: 30px;
  padding-top: 3px;
  border: 1px #e0e0e0 solid;
  border-radius: 3px;
}

.bed-state-occupied {
  background: #409eff;
  color: #fff;
}

.worker-detail-tab .el-tabs__header {
  margin-bottom: 0px;
}

.el-input.is-disabled .el-input__inner {
  border-color: #e4e7ed;
  color: #5c5c5c;
  cursor: auto;
}

.titleInfo {
  display: inline-block;
}

.btn-info {
  float: right;
}

.community-info {
  margin: 25px 15px 15px 10px;
  display: flex;
  justify-content: space-between;
}
.community-info div {
  display: inline-block;
  margin-right: 20px;
}

.community-loc {
  font-size: 14px;
  color: gray;
}
</style>


