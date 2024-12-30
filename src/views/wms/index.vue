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
          <el-form-item label="护工姓名：">
            <el-input
              v-model="listQuery.name"
              class="input-width"
              placeholder="不限"
            ></el-input>
          </el-form-item>

          <el-form-item label="工号：">
            <el-input
              v-model="listQuery.num"
              class="input-width"
              placeholder="不限"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>护工列表</span>
      <el-button
        class="btn-add"
        @click="showAddDialog()"
        type="primary"
        size="small"
        style="margin-top: -7px"
      >
        添加护工
      </el-button>
    </el-card>

    <div class="table-container">
      <el-table
        ref="deviceTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
      >
        <el-table-column label="工号" width="200" align="center">
          <template slot-scope="scope">{{ scope.row.num }}</template>
        </el-table-column>

        <el-table-column label="姓名" align="center">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>

        <el-table-column label="性别" align="center">
          <template slot-scope="scope">{{ scope.row.gender }}</template>
        </el-table-column>

        <el-table-column label="年龄" align="center">
          <template slot-scope="scope">{{
            scope.row.idnum ? 2022 - scope.row.idnum.slice(6, 10) : ""
          }}</template>
        </el-table-column>

        <el-table-column label="联系方式" align="center">
          <template slot-scope="scope">{{ scope.row.tel }}</template>
        </el-table-column>

        <el-table-column label="操作" width="240" align="center">
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleWorkerDetail(scope.$index, scope.row)"
              >详 情</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="handleDelete(scope.$index, scope.row)"
              >删 除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      title="添加护工"
      :visible.sync="addDialogVisible"
      width="40%"
      top="25vh"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <el-form
          :model="addWorkerForm"
          :rules="addWorkerRules"
          ref="addWorkerForm"
          label-width="100px"
          style="padding-right: 20px"
        >
          <el-form-item label="工号：" prop="num">
            <el-input
              v-model="addWorkerForm.num"
              placeholder="员工工号"
            ></el-input>
          </el-form-item>
          <el-row :gutter="10">
            <el-col :span="11">
              <el-form-item label="护工姓名：" prop="name">
                <el-input
                  v-model="addWorkerForm.name"
                  placeholder="姓名"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="13">
              <el-form-item label="联系方式：" prop="tel">
                <el-input
                  v-model="addWorkerForm.tel"
                  placeholder="电话号码"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="16">
              <el-form-item label="身份证号：" prop="idNum">
                <el-input
                  v-model="addWorkerForm.idNum"
                  placeholder="身份证号"
                  @change="handleidNumChange"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="性别：" label-width="70px">
                <el-input disabled v-model="addWorkerForm.gender"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
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
            @click="addConfirm"
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
import { fetchList, addWorker, deleteWorker } from "@/api/worker";

const defaultListQuery = {
  num: null,
  name: null,
};
export default {
  name: "orderList",
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      listLoading: false,

      totalList: [
        {
          id: "w01",
          num: "iw16338",
          name: "wyy",
          gender: "女",
          idNum: "4300000199700008043",
          tel: "15555555555",
        },
      ],
      list: null,
      total: null,

      addDialogVisible: false,
      addWorkerForm: {
        instId: null,
        num: null,
        name: "",
        gender: "",
        idNum: "",
        tel: "",
      },
      addWorkerRules: {
        num: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        idNum: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          {
            min: 18,
            max: 18,
            message: "请输入18位身份证号",
            trigger: "blur",
          },
        ],
        tel: [{ required: true, message: "该项不能为空", trigger: "blur" }],
      },
    };
  },
  created() {
    console.log("worker created");
    this.getTotalList();
  },

  methods: {
    getTotalList() {
      this.listLoading = true;
      fetchList().then((res) => {
        this.totalList = res.data;
        this.list = this.totalList;
        this.listLoading = false;
      });
    },

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
        if (query.num && !item.num.includes(query.num)) {
          continue;
        } else if (query.name && !item.name.includes(query.name)) {
          continue;
        } else {
          this.list.push(item);
        }
      }
      this.listLoading = false;
    },

    showAddDialog() {
      this.addDialogVisible = true;
    },

    handleidNumChange() {
      var idNum = this.addWorkerForm.idNum;
      var len = idNum.length;
      if (len == 18) {
        if (idNum[len - 2] % 2) {
          this.addWorkerForm.gender = "男";
        } else {
          this.addWorkerForm.gender = "女";
        }
      } else {
        this.addWorkerForm.gender = "";
      }
    },

    addCancel() {
      this.$refs.addWorkerForm.resetFields();
      this.addDialogVisible = false;
    },

    addConfirm() {
      this.$refs.addWorkerForm.validate((valid) => {
        if (valid) {
          var formObj = {
            num: this.addWorkerForm.num,
            name: this.addWorkerForm.name,
            gender: this.addWorkerForm.gender,
            idnum: this.addWorkerForm.idNum,
            tel: this.addWorkerForm.tel,
          };
          console.log("输入的表单:", formObj);
          addWorker(formObj)
            .then((res) => {
              this.$message({
                message: "添加护工成功",
                type: "success",
              });
              this.$refs.addWorkerForm.resetFields();
              this.addDialogVisible = false;
              this.getTotalList();
            })
            .catch((err) => {
              console.log(err);
              this.$message({
                message: "添加失败",
                type: "warning",
              });
            });
        }
      });
    },

    addAgain() {
      this.$refs.addWorkerForm.validate((valid) => {
        if (valid) {
          var formObj = {
            num: this.addWorkerForm.num,
            name: this.addWorkerForm.name,
            gender: this.addWorkerForm.gender,
            idnum: this.addWorkerForm.idNum,
            tel: this.addWorkerForm.tel,
          };
          console.log("输入的表单:", formObj);
          addWorker(formObj)
            .then((res) => {
              this.$message({
                message: "添加护工成功",
                type: "success",
              });
              this.$refs.addWorkerForm.resetFields();
              this.addDialogVisible = true;
              this.getTotalList();
            })
            .catch((err) => {
              console.log(err);
              this.$message({
                message: "添加失败",
                type: "warning",
              });
            });
        }
      });
    },

    handleWorkerDetail(index, row) {
      console.log("handleWorkerDetail", row);
      this.$router.push({ path: "/wms/workerDetail", query: { id: row.id } });
    },

    handleDelete(index, row) {
      console.log("handleWorkerDelete", row);
      this.$confirm("是否要进行删除操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteWorker(row.id)
          .then((res) => {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            this.getTotalList();
          })
          .catch((err) => {
            this.$message({
              message: "删除失败",
              type: "warning",
            });
          });
      });
    },
  },
};
</script>
<style scoped>
.input-width {
  width: 203px;
}
</style>


