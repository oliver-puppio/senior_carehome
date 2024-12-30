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
          <el-form-item label="社区名称：">
            <el-select
              placeholder="不限"
              size="small"
              v-model="listQuery.name"
              style="width: 203px"
            >
              <el-option
                v-for="item in communityList"
                :key="item.id"
                :label="item.name"
                :value="item.name"
              >
              </el-option
            ></el-select>
          </el-form-item>
          <el-form-item label="负责护工：">
            <el-select
              placeholder="不限"
              size="small"
              v-model="listQuery.name"
              style="width: 203px"
            >
              <el-option
                v-for="item in workerList"
                :key="item.id"
                :label="item.name"
                :value="item.name"
              >
              </el-option
            ></el-select>
          </el-form-item>
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

    <el-card class="operate-container" shadow="never" style="margin-top: 20px">
      <div class="titleInfo">
        <i class="el-icon-tickets"></i>
        <span>社区房间</span>
      </div>

      <el-button
        class="btn-add"
        @click="clickAddCommunity()"
        type="primary"
        size="small"
        style="margin-top: -7px"
      >
        新增社区
      </el-button>
    </el-card>

    <!-- 这里要写一个循环遍历所有社区信息以及社区下的老人 -->
    <div class="room-out-wrapper" v-for="community in list" :key="community.id">
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
          接入数：{{ community.nums }}
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
            width="120px"
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
          <el-table-column
            prop="workername"
            align="center"
            label="负责护工"
            width="200px"
          >
          </el-table-column>
          <el-table-column
            prop="workertel"
            align="center"
            label="护工电话"
            width="220px"
          >
          </el-table-column>
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

    <el-dialog
      title="新增社区"
      :visible.sync="addCommunityVisible"
      width="40%"
      top="15vh"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form
        :model="addCommunityForm"
        :rules="addCommunityRules"
        ref="addCommunityForm"
        label-width="100px"
      >
        <el-form-item label="社区名称" prop="name">
          <el-input
            v-model="addCommunityForm.name"
            autoComplete="off"
            placeholder="请输入社区名称"
          ></el-input>
        </el-form-item>

        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="所在位置" prop="province">
              <el-select
                v-model="addCommunityForm.province"
                placeholder="省"
                @change="setProv"
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
          <el-col :span="6">
            <el-form-item prop="city" label-width="0">
              <el-select
                v-model="addCommunityForm.city"
                placeholder="市"
                @change="handleShi"
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
          <el-col :span="6">
            <el-form-item prop="area" label-width="0">
              <el-select v-model="addCommunityForm.area" placeholder="县/区">
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
        </el-row>

        <el-form-item label="详细地址" prop="detail" style="margin-bottom: 5px">
          <el-input
            v-model="addCommunityForm.detail"
            autoComplete="off"
            placeholder="请输入详细地址"
          ></el-input>
        </el-form-item>

        <el-form-item label="" style="margin-bottom: 15px">
          <div>{{ fullAddress }}</div>
        </el-form-item>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="社区主任" prop="leader">
              <el-input
                v-model="addCommunityForm.leader"
                autoComplete="off"
                placeholder="请输入主任姓名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="0" prop="tel">
              <el-input
                v-model="addCommunityForm.tel"
                autoComplete="off"
                placeholder="请输入主任联系方式"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="护工：" prop="workerID">
          <el-select
            placeholder="请选择负责护工"
            size="medium"
            v-model="addCommunityForm.workerID"
            style="width: 170px"
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
import { fetchOutRoomList } from "@/api/room";
import cityJson from "@/citys";
import { addCommunity, fetchList as fetchCommunityList } from "@/api/community";
import{ fetchList as fetchWorkerList } from "@/api/worker"

const defaultListQuery = {
  instid: null,
  seniorName: null,
};

export default {
  name: "SeniorOut",

  data() {
    return {
      cityJson,
      prov_list: [],
      city_list: [],
      area_list: [],
      workerList: [],
      communityList:[],

      addCommunityVisible: false,
      addCommunityForm: {
        name: "",
        province: "",
        city: "",
        area: "",
        detail: "",
        location: "",
        leader: "",
        tel: "",
        workerID: "",
      },

      addCommunityRules: {
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        province: [
          { required: true, message: "该项不能为空", trigger: "change" },
        ],
        city: [{ required: true, message: "该项不能为空", trigger: "change" }],
        detail: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        area: [{ required: true, message: "该项不能为空", trigger: "change" }],
        leader: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        tel: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        workerID: [
          { required: true, message: "该项不能为空", trigger: "change" },
        ],
      },

      list: [],
      totalList: [],

      listQuery: Object.assign({}, defaultListQuery),
    };
  },

  created() {
    this.getTotalList();
    this.getWorkerList();
    this.getCommunityList();
  },

  mounted() {
    this.handleGetCitys();
  },

  computed: {
    fullAddress: function () {
      if (this.addCommunityForm.province === this.addCommunityForm.city) {
        this.addCommunityForm.location =
          this.addCommunityForm.province +
          "-" +
          this.addCommunityForm.area +
          "-" +
          this.addCommunityForm.detail;
        return this.addCommunityForm.location;
      } else {
        this.addCommunityForm.location =
          this.addCommunityForm.province +
          "-" +
          this.addCommunityForm.city +
          "-" +
          this.addCommunityForm.area +
          "-" +
          this.addCommunityForm.detail;
        return this.addCommunityForm.location;
      }
    },
  },

  methods: {
    getWorkerList() {
      fetchWorkerList().then((res) => {
        console.log("fetchWorkerList().response:", res);
        this.workerList = res.data;
      });
    },

    getCommunityList(){
      fetchCommunityList().then ((res)=>{
        console.log("fetchCommunityList().response:", res);
        this.communityList = res.data;
      })
    
    },

    getTotalList() {
      fetchOutRoomList().then((res) => {
        console.log("fetchOutRoomList(), reaponse: ", res);
        this.totalList = res.data;
        this.list = this.totalList;
      });
    },

    handleGetCitys() {
      this.prov_list = cityJson;
    },

    setProv() {
      this.handleSheng();
      this.handleShi();
    },

    handleSheng() {
      this.city_list = [];
      this.area_list = [];
      if (this.prov_list.length > 0) {
        this.prov_list.forEach((item) => {
          if (this.addCommunityForm.province === item.name) {
            console.log(item.name);
            this.city_list = Object.assign([], item.cityList);
            // this.addCommunityForm.city = this.city_list[0].name;
          }
        });
      }
    },

    handleShi() {
      if (this.city_list.length > 0) {
        this.city_list.forEach((item) => {
          if (this.addCommunityForm.city === item.name) {
            this.area_list = Object.assign([], item.areaList);
            // this.addCommunityForm.area = this.area_list[0].name;
          }
        });
      }
    },

    // 新增社区
    clickAddCommunity() {
      this.addCommunityVisible = true;
    },

    addReset() {
      this.city_list = [];
      this.area_list = [];
      this.$refs.addCommunityForm.resetFields();
    },

    addCancel() {
      this.addReset();
      this.addCommunityVisible = false;
    },

    addConfirm() {
      this.$refs.addCommunityForm.validate((valid) => {
        if (valid) {
          var formObj = {
            name: this.addCommunityForm.name,
            loc: this.addCommunityForm.location,
            leader: this.addCommunityForm.leader,
            tel: this.addCommunityForm.tel,
            workerID: this.addCommunityForm.workerID,
          };
          addCommunity(formObj).then((res) => {
            this.addCommunityVisible = false;
            this.addReset();
            this.getTotalList();
          });
        }
      });
    },

    addAgain() {
      this.$refs.addCommunityForm.validate((valid) => {
        if (valid) {
          var formObj = {
            name: this.addCommunityForm.name,
            location: this.addCommunityForm.location,
            leader: this.addCommunityForm.leader,
            tel: this.addCommunityForm.tel,
            workerID: this.addCommunityForm.workerID,
          };
          addCommunity(formObj).then((res) => {
            this.addReset();
            this.getTotalList();
          });
        }
      });
    },

    // 查看房间详情
    handleRoomDetail(index, row) {
      this.$router.push({ path: "/rms/roomDetail", query: { id: row.id } });
    },

    handleDelete(index, row) {
      this.$confirm("是否要进行删除操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 删除房间
      });
    },
  },
};
</script>

<style scoped>
.operate-container .titleInfo {
  display: inline-block;
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