<template>
  <!-- app-container 不包括侧边栏的右边主体部分内容 -->
  <div class="app-container">
    <el-tabs type="card" @tab-click="haddleTabClick">
      <el-tab-pane label="实时监测">
        <div v-if="visible1">
          <!-- <div class="realtime-container">
          实时监测
          <lineCharts
            title="实时监测?"
            :heart-data="heartData"
            :breath-data="breathData"
          ></lineCharts>
        </div> -->
          <el-card
            class="filter-container"
            shadow="never"
            style="color: rgb(196, 43, 28); font-weight: bold"
          >
            <i class="el-icon-warning"></i>
            <span>实时异常</span>
            <el-button
              type="primary"
              style="float: right; margin-top: -11px"
              v-show="!monitoring"
              icon="el-icon-video-play"
              @click="clickMonitor"
              >开始监测</el-button
            >
            <el-button
              type="primary"
              style="float: right; margin-top: -11px"
              v-show="monitoring"
              icon="el-icon-video-pause"
              @click="clickMonitor"
              >停止监测</el-button
            >
          </el-card>

          <div class="table-container">
            <el-table
              ref="eventTable"
              :data="list"
              style="width: 100%; margin-top: 20px"
              border
            >
              <!-- <el-table-column label="编号" width="70" align="center">
                <template slot-scope="scope">{{ scope.row.id }}</template>
              </el-table-column> -->

              <el-table-column label="老人姓名" width="120" align="center">
                <template slot-scope="scope">
                  {{ scope.row.name }}
                </template>
              </el-table-column>

              <el-table-column label="所在位置" align="center">
                <template slot-scope="scope">
                  {{ scope.row.location }}
                </template>
              </el-table-column>
              <!-- 
              <el-table-column label="当前状态" align="center">
                <template slot-scope="scope">
                  心率:
                  <span style="margin-right: 10px">{{ scope.row.heart }}</span>
                  呼吸:
                  <span style="margin-right: 10px">{{ scope.row.breath }}</span>
                  状态: <span> {{ scope.row.onBedState }}</span>
                </template>
              </el-table-column> -->

              <el-table-column label="护工" align="center" width="180">
                <template slot-scope="scope">
                  {{ scope.row.workerName }} <br />
                  {{ "Tel: " + scope.row.workerTel }}
                </template>
              </el-table-column>

              <el-table-column label="发生时间" width="180" align="center">
                <template slot-scope="scope">{{
                  scope.row.startTime
                }}</template>
              </el-table-column>

              <el-table-column label="持续时长" width="130" align="center">
                <template slot-scope="scope">
                  <span
                    style="
                      color: rgb(196, 43, 28);
                      font-size: 15px;
                      font-weight: bold;
                    "
                  >
                    {{ duration(scope.row.startTime) }}</span
                  >
                </template>
              </el-table-column>

              <el-table-column label="类型" width="110" align="center">
                <template slot-scope="scope">
                  <span style="font-weight: bold">{{
                    scope.row.type === 1 ? "心率/呼吸异常" : "离床未归"
                  }}</span></template
                >
              </el-table-column>

              <el-table-column label="操作" width="120" align="center">
                <template slot-scope="scope">
                  <el-button
                    type="danger"
                    size="small"
                    @click="handleConfirmEvent(scope.$index, scope.row)"
                    v-show="scope.row.state == 0"
                    style="width: 70px"
                    >确 认</el-button
                  ><el-button
                    size="small"
                    type="danger"
                    plain
                    v-show="scope.row.state == 1"
                    disabled
                    style="margin-left: 0; width: 70px"
                    >处理中</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>

          <el-card
            class="operate-container"
            shadow="never"
            style="font-weight: bold"
          >
            <i class="el-icon-tickets"></i>
            <span>监测数据</span>
          </el-card>
          <div class="table-container">
            <el-table
              ref="eventTable"
              :data="healthrepos"
              style="width: 100%; margin-top: 20px"
              border
            >
              <el-table-column label="老人姓名" width="120" align="center">
                <template slot-scope="scope">
                  {{ scope.row.name }}
                </template>
              </el-table-column>

              <el-table-column label="位置" align="center">
                <template slot-scope="scope">
                  {{ scope.row.location }}
                </template>
              </el-table-column>

              <el-table-column label="1分钟内平均心率" align="center">
                <template slot-scope="scope">
                  {{ scope.row.mean_heart }}
                </template>
              </el-table-column>

              <el-table-column label="1分钟内平均呼吸" align="center">
                <template slot-scope="scope">
                  {{ scope.row.mean_breath }}
                </template>
              </el-table-column>

              <el-table-column label="所在位置" align="center">
                <template slot-scope="scope">
                  {{ scope.row.location }}
                </template>
              </el-table-column>

              <el-table-column label="检查时间" width="180" align="center">
                <template slot-scope="scope">{{
                  scope.row.timestamp
                }}</template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-tab-pane>

      <el-tab-pane label="异常记录">
        <event-list v-if="visible2"></event-list>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import eventList from "@/views/home/eventList";
import lineCharts from "@/views/lineCharts";
import {
  fetchDoingList,
  confirmEvent,
  finishEvent,
  addEvent,
  fetchList,
} from "@/api/event";
import { fetchLiveData, fetchDeviceLive, checkLiveData } from "@/api/record.js";

export default {
  name: "home",
  components: { eventList, lineCharts },
  data() {
    return {
      visible1: true,
      visible2: false,
      monitoring: false,
      date: new Date(),
      // heartData: [51, 49, 53, 50, 51, 49, 53, 50, 51, 49, 53, 50],
      // breathData: [18, 20, 19, 22, 18, 20, 19, 22, 18, 20, 19, 22],

      list: [],

      healthrepos: [],
      // liveDataList: [],
    };
  },

  created() {
    console.log("Home created");
  },

  mounted() {},

  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
    if (this.timer_monitor) {
      clearInterval(this.timer_monitor);
    }
    if (this.timer_check) {
      clearInterval(this.timer_check);
    }
  },

  methods: {
    clickMonitor() {
      this.monitoring = !this.monitoring;
      if (this.monitoring) {
        this.$message({
          message: "实时监测中",
          type: "info",
          duration: 10000,
          showClose: true,
        });

        let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
        this.timer = setInterval(() => {
          _this.date = new Date(); // 更新当前日期date
        }, 1000);

        this.timer_monitor = setInterval(() => {
          // this.getLiveData();// 每隔一秒钟获取一次数据库record表中的realtime记录
          checkLiveData()
            .then((res) => {
              //将返回数据放到另一张表中
              this.healthrepos = res.data;
            })
            .catch((err) => {
              console.log(err);
            });
        }, 1000 * 5);

        this.timer_check = setInterval(() => {
          fetchDoingList()
            .then((response) => {
              console.log("fetchDoingList");
              console.log(response.data);
              this.list = response.data;
              if (response.data.length > 0) {
                this.$message({
                  message: "监测发现异常，请及时处理",
                  type: "warning",
                  duration: 10000,
                  showClose: true,
                });
              }
            })
            .catch((err) => {
              console.log(err);
              this.list = [];
            });
        }, 1000 * 3);
      } else {
        if (this.timer) {
          clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
        }
        if (this.timer_monitor) {
          clearInterval(this.timer_monitor);
        }
        if (this.timer_check) {
          clearInterval(this.timer_check);
        }
      }
    },

    // 每隔一秒钟获取一次数据库record表中的realtime记录
    getLiveData() {
      fetchLiveData().then((response) => {
        var data = response.data;
        var len = data.length;
        console.log("Got live data:");
        console.log(response.data);
        if (this.liveDataList.length == 0) {
          for (let i = 0; i < len; i++) {
            this.liveDataList.push(null);
          }
        }

        for (let i = 0; i < len; i++) {
          if (this.liveDataList[i] == null) {
            const item = {
              seniorID: data[i].seniorID,
              deviceID: data[i].deviceID,
              heartList: [data[i].heart_rate],
              breathList: [data[i].breath_frequency],
              onBedList: [
                data[i].heart_rate == 0 && data[i].breath_frequency == 0
                  ? 0
                  : 1,
              ],
            };
            this.liveDataList[i] = item;
          } else {
            if (this.liveDataList[i].heartList.length == 20) {
              this.liveDataList[i].heartList.shift();
            }
            if (this.liveDataList[i].breathList.length == 20) {
              this.liveDataList[i].breathList.shift();
            }
            if (this.liveDataList[i].onBedList.length == 20) {
              this.liveDataList[i].onBedList.shift();
            }
            this.liveDataList[i].heartList.push(data[i].heart_rate);
            this.liveDataList[i].breathList.push(data[i].breath_frequency);
            this.liveDataList[i].onBedList.push(
              data[i].heart_rate == 0 && data[i].breath_frequency == 0 ? 0 : 1
            );
          }
        }
        console.log("this.liveDataList", this.liveDataList);
      });
    },

    // 获取异常列表
    getList() {
      fetchDoingList()
        .then((response) => {
          this.list = response.data;
          this.$message({
            message: "监测发现异常，请及时处理",
            type: "warning",
            duration: 3000,
            showClose: true,
          });
        })
        .catch((err) => {
          console.log(err);
          this.list = [];
        });
    },
    haddleTabClick(tab) {
      this.visible1 = tab.label == "实时监测" ? true : false;
      this.visible2 = tab.label == "异常记录" ? true : false;
    },

    dateFormat(time) {
      var date = new Date(time);
      var year = date.getFullYear();
      /* 在日期格式中，月份是从0开始的，因此要加0
       * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
       * */
      var month =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      var hours =
        date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      var minutes =
        date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      var seconds =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return (
        year +
        "-" +
        month +
        "-" +
        day +
        " " +
        hours +
        ":" +
        minutes +
        ":" +
        seconds
      );
    },

    duration(start) {
      //  var current = dateFormat(this.date);
      var end_str = this.dateFormat(this.date).replace(/-/g, "/");
      var end_date = new Date(end_str);
      // console.log("end",end_str,end_date);

      var sta_str = start.replace(/-/g, "/");
      var sta_date = new Date(sta_str);
      // console.log("sta",sta_str,sta_date);

      var stamp = (end_date - sta_date) / 1000; // 单位是秒
      // var day = parseInt(stamp / (60 * 60 * 24));
      var hour = parseInt((stamp % (60 * 60 * 24)) / (60 * 60));
      var minute = parseInt((stamp % (60 * 60)) / 60);
      var second = parseInt(stamp % 60);

      var str = "";
      if (hour > 0) {
        str += hour + "h ";
      }
      if (minute > 0) {
        str += minute + "min ";
      }
      if (second > 0) {
        str += second + "s";
      }

      return str;
    },

    handleConfirmEvent(index, row) {
      console.log("确认异常：", row);
      console.log("调用接口并重新获取数据");
      confirmEvent(row.id)
        .then((response) => {
          // 确认成功
          this.getList();
        })
        .catch((err) => {
          // 操作失败
        });
    },
  },
};
</script>

<style scoped>
</style>
