<template>
  <div class="senior-info">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right"
          @click="handleSearchList()"
          type="primary"
          size="small"
        >
          查询结果
        </el-button>
        <el-button
          style="float: right; margin-right: 15px"
          @click="handleResetSearch()"
          size="small"
        >
          重置
        </el-button>
      </div>
      <div style="margin-top: 20px">
        <el-form
          :inline="true"
          :model="listQuery"
          ref="listQuery"
          size="small"
          label-width="140px"
          style="padding-top: 10px"
        >
          <el-form-item label="所在楼栋：">
            <el-select
              placeholder="请选择楼栋名称"
              size="small"
              v-model="listQuery.buildingID"
              style="width: 203px"
            >
              <el-option
                v-for="item in buildingList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option
            ></el-select>
          </el-form-item>
          <el-form-item label="房间号：">
            <el-input
              v-model="listQuery.roomNum"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
          <el-form-item label="负责护工：">
            <el-input
              v-model="listQuery.workerName"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never" style="margin-top: 20px">
      <div class="titleInfo">
        <i class="el-icon-tickets"></i>
        <span>院内房间</span>
      </div>
      <div class="btn-add" style="margin-top: -7px">
        <el-button @click="clickAddBuilding()" type="primary" size="small">
          新增楼栋
        </el-button>
        <el-button @click="clickAddRoom()" type="primary" size="small">
          新增房间
        </el-button>
      </div>
    </el-card>

    <div class="table-container">
      <el-table
        ref="roomInTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
      >
        <el-table-column label="编号" width="120" align="center">
          <template slot-scope="scope">{{ scope.row.roomID }}</template>
        </el-table-column>

        <el-table-column label="楼栋" width="200" align="center">
          <template slot-scope="scope">{{ scope.row.buildingname }}</template>
        </el-table-column>

        <el-table-column label="房间号" width="180" align="center">
          <template slot-scope="scope">{{ scope.row.roomnum }}</template>
        </el-table-column>

        <el-table-column label="床位状态" align="center">
          <template slot-scope="scope">
            <div class="bed-state-wrapper">
              <div v-for="bed in scope.row.states" :key="bed.id">
                <div class="bed-state-empty" v-if="bed.state != 1">
                  <span> {{ bed.num }}</span>
                </div>
                <div class="bed-state-occupied" v-else>
                  <span> {{ bed.num }}</span>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="负责护工" width="180" align="center">
          <template slot-scope="scope">{{ scope.row.workername }}</template>
        </el-table-column>

        <el-table-column label="护工电话" width="180" align="center">
          <template slot-scope="scope">{{ scope.row.tel }}</template>
        </el-table-column>

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-row>
              <el-button
                size="small"
                @click="handleRoomDetail(scope.$index, scope.row)"
                >详 情
              </el-button>
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删 除
              </el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      title="新增楼栋"
      :visible.sync="addBuildingVisible"
      width="30%"
      top="25vh"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form
        :model="addBuildingForm"
        :rules="addBuildingRules"
        ref="addBuildingForm"
        label-width="130px"
      >
        <el-form-item label="请输入楼栋名：" prop="name">
          <el-input
            v-model="addBuildingForm.name"
            autoComplete="off"
            placeholder="楼栋名称"
            style="width: 270px"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-row style="padding: 0 10px">
          <el-button
            type="primary"
            plain
            @click="addAgain('addBuildingForm')"
            style="float: left"
            >再加一个</el-button
          >
          <el-button
            type="primary"
            @click="addConfirm('addBuildingForm')"
            style="float: right; margin-left: 20px"
            >确 定</el-button
          >
          <el-button
            @click="addCancel('addBuildingForm')"
            style="float: right"
            plain
            >取 消</el-button
          >
        </el-row>
      </div>
    </el-dialog>

    <el-dialog
      title="新增房间"
      :visible.sync="addRoomVisible"
      width="30%"
      top="15vh"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form
        :model="addRoomForm"
        :rules="addRoomRules"
        ref="addRoomForm"
        label-width="130px"
      >
        <el-form-item label="楼栋：" prop="buildingID">
          <el-select
            placeholder="请选择楼栋名称"
            size="medium"
            v-model="addRoomForm.buildingID"
            style="width: 203px"
          >
            <el-option
              v-for="item in buildingList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option></el-select
        ></el-form-item>
        <el-form-item label="房间号：" prop="roomNum">
          <el-input
            v-model="addRoomForm.roomNum"
            autoComplete="off"
            placeholder="请输入房间号"
            style="width: 203px"
          ></el-input>
        </el-form-item>
        <el-form-item label="容量：" prop="size">
          <el-input
            v-model="addRoomForm.size"
            autoComplete="off"
            placeholder="请输入房间容量"
            style="width: 203px"
          ></el-input>
        </el-form-item>
        <el-form-item label="护工：" prop="workerID">
          <el-select
            placeholder="请选择负责护工"
            size="medium"
            v-model="addRoomForm.workerID"
            style="width: 203px"
          >
            <el-option
              v-for="item in workerList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option></el-select
        ></el-form-item>
      </el-form>
      <div slot="footer">
        <el-row style="padding: 0 10px">
          <el-button
            type="primary"
            plain
            @click="addAgain('addRoomForm')"
            style="float: left"
            >再加一个</el-button
          >
          <el-button
            type="primary"
            @click="addConfirm('addRoomForm')"
            style="float: right; margin-left: 20px"
            >确 定</el-button
          >
          <el-button
            @click="addCancel('addRoomForm')"
            style="float: right"
            plain
            >取 消</el-button
          >
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { fetchList, addRoom } from "@/api/room";
import { fetchList as fetchBuildingList, addBuilding } from "@/api/building";
import { fetchList as fetchWorkerList } from "@/api/worker";

const defaultListQuery = {
  buildingID: null,
  roomNum: null,
  workerName: null,
};

export default {
  name: "SeniorIn",
  data() {
    return {
      addRoomVisible: false,
      addBuildingVisible: false,

      listQuery: Object.assign({}, defaultListQuery),

      totalList: [],
      list: [],
      listLoading: false,

      current: null,

      addBuildingForm: {
        name: null,
      },
      addBuildingRules: {
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
      },

      addRoomForm: {
        buildingID: null,
        roomNum: null,
        size: null,
        workerID: null,
      },

      buildingList: [],
      workerList: [],

      addRoomRules: {
        buildingID: [
          { required: true, message: "请选择所在楼栋", trigger: "change" },
        ],
        roomNum: [{ required: true, message: "请输入房间号", trigger: "blur" }],
        size: [{ required: true, message: "请输入房间容量", trigger: "blur" }],
        workerID: [
          { required: true, message: "请选择负责护工", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getTotalList();
    this.getBuildingList();
  },

  methods: {
    getBuildingList() {
      fetchBuildingList().then((res) => {
        console.log("fetchBuildingList().response:", res);
        this.buildingList = res.data;
      });
    },

    getWorkerList() {
      fetchWorkerList().then((res) => {
        console.log("fetchWorkerList().response:", res);
        this.workerList = res.data;
      });
    },

    getTotalList() {
      this.listLoading = true;
      fetchList(0).then((res) => {
        console.log("fetchList(): room in, res: ", res);
        this.totalList = res.data;
        this.list = this.totalList;
        this.listLoading = false;
      });
    },

    // 新增楼栋
    clickAddBuilding() {
      this.addBuildingVisible = true;
    },

    closeDialog(form) {
      if (form == "addBuildingForm") {
        this.addBuildingVisible = false;
      } else if (form == "addRoomForm") {
        this.addRoomVisible = false;
      }
    },

    addCancel(form) {
      this.$refs[form].resetFields();
      this.closeDialog(form);
    },

    addConfirm(form) {
      console.log(form);
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (form == "addBuildingForm") {
            addBuilding(this[form].name)
              .then(() => {
                this.$message({
                  message: "新增楼栋成功",
                  type: "success",
                });
                this.addBuildingVisible = false;
                this.getBuildingList();
              })
              .catch((err) => {
                console.log("err: addBuilding", err);
              });
          } else if (form == "addRoomForm") {
            var formObj = {
              buildingID: this[form].buildingID,
              num: this[form].roomNum,
              size: this[form].size,
              workerID: this[form].workerID,
            };
            addRoom(formObj)
              .then((res) => {
                this.$message({
                  message: "新增房间成功",
                  type: "success",
                });
                this.addRoomVisible = false;
              })
              .catch((err) => {
                console.log("err: addRoom", err);
              });
          }
          this.getTotalList();
        }
      });
    },

    addAgain(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (form == "addBuildingForm") {
            addBuilding(this[form].name)
              .then(() => {
                this.$message({
                  message: "新增楼栋成功",
                  type: "success",
                });
              })
              .catch((err) => {
                console.log("err: addBuilding", err);
              });
          } else if (form == "addRoomForm") {
            var formObj = {
              buildingID: this[form].buildingID,
              num: this[form].roomNum,
              size: this[form].size,
              workerID: this[form].workerID,
            };
            addRoom(formObj)
              .then((res) => {
                this.$message({
                  message: "新增房间成功",
                  type: "success",
                });
              })
              .catch((err) => {
                console.log("err: addRoom", err);
              });
          }
          this.getTotalList();
        }
      });
    },

    // 新增房间
    clickAddRoom() {
      this.getWorkerList();
      this.addRoomVisible = true;
    },

    // 搜索
    handleSearchList() {
      this.listLoading = true;
      var query = this.listQuery;
      var len = this.totalList.length;
      this.list = [];
      for (let i = 0; i < len; i++) {
        const item = this.totalList[i];
        if (query.buildingID && item.buildingID != query.buildingID) {
          continue;
        } else if (query.roomNum && !item.roomnum.includes(query.roomNum)) {
          continue;
        } else if (
          query.workerName &&
          !item.workername.includes(query.workerName)
        ) {
          continue;
        } else {
          this.list.push(item);
        }
      }
      this.listLoading = false;
    },

    // 搜索重置
    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.list = this.totalList;
    },

    // 删除
    handleDelete(index, row) {
      this.$confirm("是否要进行删除操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        let ids = [];
        ids.push(row.id);

        // updateDeleteStatus是接口
        this.updateDeleteStatus(1, ids);
      });
    },

    // 查看房间详情
    handleRoomDetail(index, row) {
      this.$router.push({ path: "/rms/roomDetail", query: { id: row.roomID } });
    },
  },
};
</script>

<style scoped>
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

.operate-container .titleInfo {
  display: inline-block;
}

.current-info-wrapper {
  margin-bottom: 15px;
  padding-left: 20px;
}

.change-to {
  margin-top: 20px;
  padding-left: 20px;
}

.current-info {
  display: inline-block;
  padding-right: 10px;
}
</style>