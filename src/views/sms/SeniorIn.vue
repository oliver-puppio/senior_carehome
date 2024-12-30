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
          <el-form-item label="老人姓名：">
            <el-input
              v-model="listQuery.seniorName"
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
          <el-form-item label="所在位置：">
            <el-input
              v-model="listQuery.seniorLoc"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <div class="table-container">
      <el-table
        ref="seniorInTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
      >
        <el-table-column label="编号" width="70" align="center">
          <template slot-scope="scope">{{ scope.row.seniorID }}</template>
        </el-table-column>
        <el-table-column label="老人姓名" width="110" align="center">
          <template slot-scope="scope">{{ scope.row.seniorName }}</template>
        </el-table-column>
        <el-table-column label="性别" align="center" width="60">
          <template slot-scope="scope">{{ scope.row.gender }}</template>
        </el-table-column>
        <el-table-column label="位置" align="center">
          <template slot-scope="scope">{{ scope.row.seniorLoc }}</template>
        </el-table-column>
        <el-table-column label="入住时间" width="150" align="center">
          <template slot-scope="scope">{{ scope.row.registerTime }}</template>
        </el-table-column>
        <el-table-column label="联系方式" width="130" align="center">
          <template slot-scope="scope">{{ scope.row.tel }}</template>
        </el-table-column>
        <el-table-column label="负责护工" width="120" align="center">
          <template slot-scope="scope">{{ scope.row.worker }}</template>
        </el-table-column>
        <el-table-column label="护工电话" width="130" align="center">
          <template slot-scope="scope">{{ scope.row.workerTel }}</template>
        </el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template slot-scope="scope">
            <el-row>
              <el-button
                size="small"
                @click="handleSeniorDetail(scope.$index, scope.row)"
                >详情
              </el-button>
              <el-button
                size="small"
                @click="clickChangeBed(scope.$index, scope.row)"
                >换床
              </el-button>
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >离院
              </el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      class="changeBed"
      title="更换床位绑定"
      :visible.sync="changeBedInfo.dialogVisible"
      width="50%"
      top="10vh"
      :show-close="false"
      :center="true"
      :close-on-press-escape="false"
      :close-on-click-modal="false"
    >
      <div class="current-info-wrapper">
        <div class="current-info" style="width: 25%">
          <span>姓名：</span>
          <span>{{ changeBedInfo.seniorName }}</span>
        </div>
        <div class="current-info" style="width: 49%">
          <span>当前床位：</span>
          <span>{{ changeBedInfo.current.seniorLoc }}</span>
        </div>
        <div class="current-info" style="width: 23%">
          <span>负责人：</span>
          <span>{{ changeBedInfo.current.workerName }}</span>
        </div>
      </div>
      <hr />
      <div class="change-to">
        更换至：
        <el-form
          style="margin-top: 15px"
          label-position="right"
          label-width="40px"
          :model="listQuery"
        >
          <el-row>
            <el-col :span="8">
              <el-form-item label="楼栋">
                <el-select
                  placeholder="请选择楼栋"
                  size="medium"
                  v-model="changeBedInfo.roomListQuery.buildingID"
                  clearable
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
                  placeholder="请输入房间号"
                  size="medium"
                  v-model="changeBedInfo.roomListQuery.roomNum"
                >
                </el-input
              ></el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item style="float: right; margin-right: 20px"
                ><el-button size="medium" @click="searchRoomData"
                  >查 询</el-button
                ></el-form-item
              ></el-col
            ></el-row
          >
        </el-form>
      </div>

      <el-table
        style="width: 100%; margin-top: 20px"
        :data="roomData"
        border
        max-height="250px"
        v-loading="roomLoading"
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

      <span slot="footer" class="dialog-footer">
        <el-button
          @click="changeBedCancel"
          style="margin-right: 20px; width: 100px"
          >取 消</el-button
        >
        <el-button type="primary" @click="changeBedConfirm" style="width: 100px"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { fetchList, bindBed } from "@/api/senior";
import { fetchList as fetchRoomList } from "@/api/room";
import { fetchList as fetchBuildingList } from "@/api/building";

const defaultListQuery = {
  seniorName: null,
  seniorLoc: null,
  workerName: null,
};

export default {
  seniorName: "SeniorIn",
  data() {
    return {
      buildingList: [],
      totalRoomData: [],
      roomData: [],

      changeBedInfo: {
        dialogVisible: false,
        seniorID: null,
        seniorName: null,

        roomListQuery: {
          buildingID: null,
          roomnum: null,
        },

        current: {
          seniorLoc: null,
          workerName: null,
        },

        newBedID: null,
      },

      listQuery: Object.assign({}, defaultListQuery),
      list: [],
      totalList: [],
      listLoading: false,
      roomLoading: false,

      current: null,
    };
  },
  created() {
    this.getTotalList();
  },
  watch: {
    selectProductCateValue: function (newValue) {
      if (newValue != null && newValue.length == 2) {
        this.listQuery.productCategoryId = newValue[1];
      } else {
        this.listQuery.productCategoryId = null;
      }
    },
  },
  filters: {},
  methods: {
    getTotalList() {
      // 显示加载中
      this.listLoading = true;
      fetchList(0)
        .then((response) => {
          console.log("fetchList().response: ", response);
          this.totalList = response.data.seniors;
          this.list = this.totalList;
          // 对象数组深拷贝
          // this.list = this.totalList.map((o) => Object.assign({}, o));
          this.listLoading = false;
        })
        .catch((err) => {
          console.log("fetchList().err", err);
        });
    },

    // 筛选搜索 查询结果
    handleSearchList() {
      this.listLoading = true;
      var query = this.listQuery;
      var len = this.totalList.length;
      this.list = [];
      for (let i = 0; i < len; i++) {
        const item = this.totalList[i];
        if (query.seniorName && !item.seniorName.includes(query.seniorName)) {
          continue;
        } else if (
          query.seniorLoc &&
          !item.seniorLoc.includes(query.seniorLoc)
        ) {
          continue;
        } else if (
          query.workerName &&
          !item.workerName.includes(query.workerName)
        ) {
          continue;
        } else {
          this.list.push(item);
        }
      }
      this.listLoading = false;
    },

    // 更换床位（弹窗）
    clickChangeBed(index, row) {
      this.changeBedInfo.dialogVisible = true;
      this.changeBedInfo.seniorID = row.seniorID;
      this.changeBedInfo.seniorName = row.seniorName;
      this.changeBedInfo.current.seniorLoc = row.seniorLoc;
      this.changeBedInfo.current.workerName = row.worker;

      fetchBuildingList().then((response) => {
        console.log("fetchBuildingList().response", response);
        this.buildingList = response.data;
      });
      this.roomLoading = true;
      fetchRoomList(0).then((response) => {
        console.log("fetchRoomList().response", response);
        this.totalRoomData = response.data;
        this.roomData = this.totalRoomData;
        this.roomLoading = false;
      });
      console.log("clickChangeBed", this.changeBedInfo);
    },

    // 换床
    searchRoomData() {
      this.listLoading = true;
      var query = this.changeBedInfo.roomListQuery;
      var len = this.totalRoomData.length;
      this.roomData = [];
      for (let i = 0; i < len; i++) {
        const item = this.totalRoomData[i];
        if (query.buildingID && query.buildingID == item.buildingID) {
          if (query.roomnum && query.roomnum == item.roomnum) {
            this.list.push(item);
          }
        }
      }
      this.listLoading = false;
    },

    // 选床
    clickBed(index, row) {
      for (var i = 0; i < row.states.length; i++) {
        if (row.states[i].num == row.chosenbed) {
          this.changeBedInfo.newBedID = row.states[i].id;
          break;
        }
      }
      for (var i = 0; i < this.roomData.length; i++) {
        if (i != index) {
          this.roomData[i].chosenbed = "";
        }
      }
      console.log("this.chosenInfoIn", this.changeBedInfo.newBedID);
    },

    resetChangeBed() {
      this.changeBedInfo.roomListQuery.buildingID = null;
      this.changeBedInfo.roomListQuery.roomnum = null;
      this.changeBedInfo.newBedID = null;
      for (var i = 0; i < this.roomData.length; i++) {
        this.roomData[i].chosenbed = "";
      }
    },

    // 取消换床
    changeBedCancel() {
      this.resetChangeBed();
      this.changeBedInfo.dialogVisible = false;
    },

    // 确认换床
    changeBedConfirm(row) {
      if (
        this.changeBedInfo.newBedID == null //任一个是空即没有记录
      ) {
        this.$message({
          message: "请选择床位",
          type: "warning",
        });
        return;
      }
      this.$confirm("是否要更换床位？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        bindBed(this.changeBedInfo.seniorID, this.changeBedInfo.newBedID)
          .then((response) => {
            this.$message({
              message: "操作成功",
              type: "success",
            });
            this.resetChangeBed();
            this.getTotalList();
            this.changeBedInfo.dialogVisible = false;
          })
          .catch((err) => {
            this.$message({
              message: "操作失败",
              type: "error",
            });
          });
      });
    },

    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.list = this.totalList;
    },

    handleDelete(index, row) {
      this.$confirm("是否要办理离院?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 删除老人，重新获得老人列表
        this.getList();
      });
    },

    // 查看老人详情
    handleSeniorDetail(index, row) {
      this.$router.push({
        path: "/sms/seniorDetail",
        query: { id: row.seniorID },
      });
    },
  },
};
</script>

<style scoped>
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