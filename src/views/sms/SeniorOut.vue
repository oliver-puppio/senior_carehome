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
              v-model="listQuery.worker"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
          <el-form-item label="社区名称：">
            <el-select
              v-model="listQuery.instid"
              style="width: 203px"
              placeholder="不限"
              clearable
            >
              <el-option
                v-for="item in totalList"
                :key="item.instid"
                :label="item.instName"
                :value="item.instid"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <!-- 循环遍历所有社区信息以及社区下的老人 -->
    <div
      class="senior-out-wrapper"
      v-for="community in list"
      :key="community.instid"
    >
      <div class="community-info">
        <div class="community-name-loc" style="width: 480px">
          <svg-icon
            icon-class="login-mall"
            style="width: 16px; color: gray"
          ></svg-icon>
          {{ community.instName }}
          <div class="community-loc">{{ community.loc }}</div>
        </div>
        <div class="community-leader" style="width: 200px">
          社区主任：{{ community.leader }}
        </div>
        <div class="community-leader-tel" style="width: 200px">
          电话：{{ community.leaderTel }}
        </div>
        <div class="community-size" style="width: 130px; text-align: right">
          接入数：{{ community.size }}
        </div>
      </div>
      <div class="table-wrapper">
        <el-table
          :data="community.seniors"
          style="width: 100%"
          border
          max-height="300px"
        >
          <el-table-column
            prop="seniorID"
            align="center"
            label="编号"
            width="80px"
          >
          </el-table-column>
          <el-table-column
            prop="seniorName"
            align="center"
            label="姓名"
            width="120px"
          >
          </el-table-column>
          <el-table-column
            prop="gender"
            align="center"
            label="性别"
            width="60px"
          >
          </el-table-column>
          <el-table-column prop="seniorLoc" align="center" label="位置">
          </el-table-column>
          <el-table-column
            prop="registerTime"
            align="center"
            label="绑定时间"
            width="180px"
          >
          </el-table-column>
          <el-table-column
            prop="tel"
            align="center"
            label="联系方式"
            width="150px"
          >
          </el-table-column>
          <el-table-column
            prop="worker"
            align="center"
            label="负责护工"
            width="120px"
          >
          </el-table-column>
          <el-table-column
            prop="workerTel"
            align="center"
            label="护工电话"
            width="150px"
          >
          </el-table-column>
          <el-table-column align="center" label="操作" width="160px">
            <template slot-scope="scope">
              <el-button
                size="small"
                @click="handleSeniorDetail(scope.$index, scope.row)"
                >详情
              </el-button>
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >解绑
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <hr style="margin: 30px 0px" />
    </div>
  </div>
</template>

<script>
import { fetchList } from "@/api/senior";

const defaultListQuery = {
  seniorName: "",
  worker: "",
  instid: "",
};
export default {
  name: "SeniorOut",

  data() {
    return {
      totalList: [],

      list: [],

      listQuery: Object.assign({}, defaultListQuery),
    };
  },

  created() {
    this.getTotalList();
  },

  mounted() {},

  methods: {
    getTotalList() {
      fetchList(1).then((res) => {
        console.log("fetchList, seniors in community: ", res);
        this.totalList = res.data;
        this.list = this.totalList;
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
        if (query.instid && item.instid != query.instid) {
          continue;
        } else {
          var arr = [];
          for (let j = 0; j < item.seniors.length; j++) {
            const senior = item.seniors[j];
            if (
              query.seniorName &&
              !senior.seniorName.includes(query.seniorName)
            ) {
              continue;
            } else if (query.worker && !senior.worker.includes(query.worker)) {
              continue;
            } else {
              arr.push(senior);
            }
          }
          if (arr.length > 0) {
            this.list.push({
              instid: item.instid,
              instName: item.instName,
              loc: item.loc,
              leader: item.leader,
              leaderTel: item.leaderTel,
              size: item.size,
              seniors: arr.slice(0),
            });
          }
        }
      }
      this.listLoading = false;
    },

    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.list = this.totalList;
    },

    // 查看老人详情
    handleSeniorDetail(index, row) {
      console.log("handleSeniorDetail", row);
      console.log(
        "这里需要跳转老人信息详情页，调用【this.$router.push({path:'/sms/seniorDetail',query:{id:row.id}})】path路径，query:参数对象列表"
      );
      this.$router.push({
        path: "/sms/seniorDetail",
        query: { id: row.seniorID },
      });
    },

    handleDelete(index, row) {
      this.$confirm("是否要进行解绑操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        let ids = [];
        ids.push(row.id);
        // this.updateDeleteStatus(1, ids);
      });
    },
  },
};
</script>

<style scoped>
.community-info {
  margin: 20px 0px;
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