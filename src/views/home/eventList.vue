<template>
  <div class="event-container">
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
          <el-form-item label="异常类型：">
            <el-select
              v-model="listQuery.type"
              placeholder="全部"
              style="width: 203px"
              clearable
            >
              <el-option
                v-for="item in typeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="老人姓名：">
            <el-input
              v-model="listQuery.name"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
          <el-form-item label="处理人：">
            <el-input
              v-model="listQuery.handler"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
          <!-- <el-form-item label="发生时间：">
            <el-date-picker
              v-model="listQuery.startTime"
              type="date"
              style="width: 203px"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item> -->
          <el-form-item label="所在位置：">
            <el-input
              v-model="listQuery.location"
              style="width: 203px"
              placeholder="不限"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>历史异常</span>
    </el-card>

    <div class="table-container">
      <el-table
        ref="eventTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
        max-height="680px"
      >
        <el-table-column label="编号" width="80" align="center">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>

        <el-table-column label="老人姓名" width="110" align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>

        <el-table-column label="所在位置" align="center">
          <template slot-scope="scope">
            {{ scope.row.location }}
          </template>
        </el-table-column>

        <el-table-column label="异常类型" width="130" align="center">
          <template slot-scope="scope">{{
            scope.row.type == 2 ? "离床未归" : "数据异常"
          }}</template>
        </el-table-column>

        <el-table-column label="发生时间" width="180" align="center">
          <template slot-scope="scope">{{ scope.row.startTime }}</template>
        </el-table-column>

        <el-table-column label="确认时长" width="120" align="center">
          <template slot-scope="scope">{{ scope.row.enterDuration }}</template>
        </el-table-column>

        <el-table-column label="总时长" width="120" align="center">
          <template slot-scope="scope">{{ scope.row.duration }}</template>
        </el-table-column>

        <el-table-column label="处理结果" width="160" align="center">
          <template slot-scope="scope">
            <p>{{ scope.row.resultText }}</p>
          </template>
        </el-table-column>

        <el-table-column label="处理人" width="140" align="center">
          <template slot-scope="scope">{{ scope.row.handler }}</template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import { fetchDoneList } from "@/api/event";
import { test } from "@/api/login";

const defaultListQuery = {
  name: null,
  location: null,
  type: null,
  handler: null,
};
export default {
  name: "eventList",
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: [],
      totalList: [],
      listLoading: false,

      typeOptions: [
        {
          value: 1,
          label: "数据异常",
        },
        {
          value: 2,
          label: "离床未归",
        },
      ],
    };
  },
  created() {
    this.getTotalList();
  },

  methods: {
    getTotalList() {
      // 显示加载中
      this.listLoading = true;
      fetchDoneList()
        .then((response) => {
          console.log("fetchDoneList().response");
          this.totalList = response.data;
          this.list = this.totalList;
          // 对象数组深拷贝
          // this.list = this.totalList.map((o) => Object.assign({}, o));
          this.listLoading = false;
        })
        .catch((err) => {
          console.log("fetchDoneList().err");

          console.log(err);
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
        if (query.name && !item.name.includes(query.name)) {
          continue;
        } else if (query.location && !item.location.includes(query.location)) {
          continue;
        } else if (
          (query.type == 1 || query.type == 2) &&
          item.type != query.type
        ) {
          continue;
        } else if (query.handler && !item.handler.includes(query.handler)) {
          continue;
        } else {
          this.list.push(item);
        }
      }
      this.listLoading = false;
    },

    // 筛选搜索重置
    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.list = this.totalList;
    },
  },
};
</script>

<style scoped>
.event-container {
  height: 100%;
}
</style>
