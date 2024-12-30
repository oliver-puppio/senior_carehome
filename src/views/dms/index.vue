<template>
   
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right"
          type="primary"
          @click="handleSearchList()"
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
          size="small"
          label-width="140px"
          style="padding-top: 10px"
        >
          <el-form-item label="设备编号：">
            <el-input
              v-model="listQuery.id"
              class="input-width"
              placeholder="不限"
            ></el-input>
          </el-form-item>

          <el-form-item label="绑定位置：">
            <el-input
              v-model="listQuery.location"
              class="input-width"
              placeholder="不限"
            ></el-input>
          </el-form-item>

          <el-form-item label="设备状态：">
            <el-select
              v-model="listQuery.state"
              class="input-width"
              placeholder="不限"
              clearable
            >
              <el-option
                v-for="item in stateOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>设备列表</span>
      <el-button
        class="btn-add"
        @click="showAddDialog()"
        type="primary"
        size="small"
        style="margin-top: -7px"
      >
        添加设备
      </el-button>
    </el-card>

    <div class="table-container">
      <el-table
        ref="deviceTable"
        :data="list"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="listLoading"
        border
        max-height="400px"
      >
        <el-table-column
          type="selection"
          width="80"
          align="center"
        ></el-table-column>

        <el-table-column label="设备编号" width="200" align="center">
          <template slot-scope="scope">{{ scope.row.deviceid }}</template>
        </el-table-column>

        <el-table-column label="绑定位置" align="center">
          <template slot-scope="scope">{{ scope.row.loc }}</template>
        </el-table-column>

        <el-table-column label="注册时间" align="center">
          <template slot-scope="scope">{{ scope.row.registerTime }}</template>
        </el-table-column>

        <el-table-column label="操作" width="240" align="center">
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="removeBind(scope.$index, scope.row)"
              v-show="scope.row.state == 2"
              >解除绑定</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="ditchDevice(scope.$index, scope.row)"
              v-show="scope.row.state == 1"
              style="margin-left: 0px"
              >注销设备</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="batch-operate-container">
      <el-button
        class="search-button"
        @click="batchDitch()"
        type="danger"
        plain
        size="small"
      >
        批量解绑
      </el-button>
      <el-button
        style="margin-left: 10px"
        class="search-button"
        @click="batchDitch()"
        type="danger"
        size="small"
      >
        批量注销
      </el-button>
    </div>

    <el-dialog
      title="添加设备"
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
            plain
            @click="addAgain()"
            style="float: left"
            >再加一个</el-button
          >
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
import { deviceList, addDevice, ditchDevice } from "../../api/device";

const defaultListQuery = {
  id: null,
  location: null,
  state: null,
};
export default {
  name: "deviceList",
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      listLoading: false,
      list: [],
      totalList: [],

      multipleSelection: [],

      stateOptions: [
        {
          value: 1,
          label: "未绑定",
        },
        {
          value: 2,
          label: "已绑定",
        },
      ],

      dialogVisible: false,
      addDeviceForm: {
        id: null,
      },
      addDeviceRules: {
        id: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          { type: "number", trigger: "blur", message: "请输入整数的设备号" },
        ],
      },
    };
  },
  created() {
    console.log("dms created");
    this.getTotalList();
  },

  methods: {
    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.list = this.totalList;
    },

    handleSearchList() {
      this.listLoading = true;
      var query = this.listQuery;
      var len = this.totalList.length;
      this.list = [];
      for (let i = 0; i < len; i++) {
        const item = this.totalList[i];
        if (query.id && item.deviceid != query.name) {
          continue;
        } else if (query.location && !item.loc.includes(query.location)) {
          continue;
        } else if (
          (query.state == 1 || query.state == 2) &&
          item.state != query.state
        ) {
          continue;
        } else {
          this.list.push(item);
        }
      }
      this.listLoading = false;
    },

    getTotalList() {
      this.listLoading = true;
      deviceList().then((res) => {
        this.totalList = res.data;
        this.list = this.totalList;
        this.listLoading = false;
      });
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    showAddDialog() {
      this.dialogVisible = true;
    },

    addCancel() {
      this.$refs.addDeviceForm.resetFields();
      this.dialogVisible = false;
    },

    addConfirm() {
      this.$refs.addDeviceForm.validate((valid) => {
        if (valid) {
          console.log("输入的id:", this.addDeviceForm.id);
          // 调用接口，插入成功后：
          addDevice(this.addDeviceForm.id)
            .then((res) => {
              // success = true;
              console.log("success");
              this.$message({
                message: "添加设备成功",
                type: "success",
              });
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

    addAgain() {
      this.$refs.addDeviceForm.validate((valid) => {
        if (valid) {
          console.log("输入的id:", this.addDeviceForm.id);
          // 调用接口，插入成功后：
          addDevice(this.addDeviceForm.id)
            .then((res) => {
              // success = true;
              console.log("success");
              this.$message({
                message: "添加设备成功，您可以再加一个",
                type: "success",
              });
              this.getTotalList();
              this.$refs.addDeviceForm.resetFields();
              this.dialogVisible = true;
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

    removeBind(index, row) {
      console.log("解除绑定, id:", row.deviceid, "原本的state:", row.state);
      ditchDevice(row.deviceid, 0)
        .then((res) => {
          console.log(res);

          this.$message({
            message: "解绑成功",
            type: "success",
          });
          this.getTotalList();
        })
        .catch((err) => {
          console.log(err);
          this.$message({
            message: "解绑失败",
            type: "warning",
          });
        });
    },

    // 删除单个设备，列表长度是1
    ditchDevice(index, row) {
      ditchDevice(row.deviceid, 2)
        .then((res) => {
          this.$message({
            message: "注销成功",
            type: "success",
          });
          this.getTotalList();
        })
        .catch((err) => {
          this.$message({
            message: "注销失败",
            type: "warning",
          });
        });
    },

    // 批量注销
    batchDitch() {
      if (this.multipleSelection == null || this.multipleSelection.length < 1) {
        this.$message({
          message: "请选择要操作的设备",
          type: "warning",
        });
        return;
      }

      // 注销设备
      let ids = [];
      let states = [];
      for (let i = 0; i < this.multipleSelection.length; i++) {
        ids.push(this.multipleSelection[i].id);
        states.push(this.multipleSelection[i].state);
      }
      this.ditchDeviceList(ids, states);
    },

    // 注销设备列表
    ditchDeviceList(ids, states) {
      // 首先判断所选项是否都已绑定(1)，否则不允许注销！
      var flag = true;
      for (var i = 0; i < ids.length; i++) {
        if (states[i] != 1) {
          flag = false;
          break;
        }
      }
      if (flag) {
        this.$confirm("是否要进行注销操作?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          let params = new URLSearchParams();
          params.append("ids", ids);

          // deleteOrder是调用接口
          deleteOrder(params).then((response) => {
            this.$message({
              message: "注销成功！",
              type: "success",
            });
            this.getTotalList();
          });
        });
      } else {
        this.$message({
          message: "注销失败！请先解绑设备",
          type: "error",
        });
      }
    },
  },
};
</script>
<style scoped>
.input-width {
  width: 203px;
}
</style>


