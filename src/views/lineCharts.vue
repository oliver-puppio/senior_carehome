<template>
  <div class="echarts-wrapper" ref="echarts"></div>
</template>

<script>
const echarts = require("echarts/lib/echarts");
require("echarts/lib/component/title");
require("echarts/lib/component/tooltip");
require("echarts/lib/component/grid");
require("echarts/lib/component/legend");
require("echarts/lib/chart/line");

export default {
  name: "lineCharts",

  props: {
    length: {
      type: Number,
      default: 36,
    },
    title: {
      type: String,
      default: "",
    },
    xLabel: {
      type: Array,
      default: function () {
        var arr = [];
        for (var i = 0; i < this.length; i++) {
          arr.push("");
        }
        return arr;
      },
    },
    heartData: {
      type: Array,
      default: function () {
        // return [];
        return [
          51, 47, 53, 50, 51, 51, 47, 53, 50, 53, 50, 53, 50, 51, 47, 51, 47,
          53, 50, 53, 50, 53, 50, 47, 53, 50, 51, 47, 53, 50, 51, 47, 53, 50,
          53, 50,
        ];
      },
    },
    breathData: {
      type: Array,
      default: function () {
        // return [];
        return [
          18, 20, 19, 18, 20, 19, 22, 18, 20, 19, 22, 18, 20, 19, 22, 22, 18,
          20, 19, 22, 18, 20, 19, 18, 20, 19, 22, 18, 20, 19, 22, 18, 20, 19,
          22, 22,
        ];
      },
    },
  },

  data() {
    return {
      myChart: null,
    };
  },

  created() {},

  mounted() {
    this.drawLine(this.title, this.heartData, this.breathData);
  },

  methods: {
    drawLine(title, heartData, breathData) {
      this.myChart = echarts.init(this.$refs.echarts);
      var option = {
        title: {
          text: title,
          top: "0%",
          left: "0%",
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["心率", "呼吸"],
          bottom: "0%",
          itemGap: 20,
          itemWidth: 30,
          textStyle: {
            fontSize: 13,
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "8%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          // show: false,
          data: this.xLabel,
        },
        yAxis: [
          {
            type: "value",
            axisLabel: {
              fontSize: 13,
            },
          },
          {
            type: "value",
            axisLabel: {
              fontSize: 13,
            },
          },
        ],

        series: [
          {
            name: "心率",
            type: "line",
            color: "#3ba272",
            smooth: true,
            data: heartData,
            symbolSize: 4,
            areaStyle: {
              opacity: 0.4,
            },
          },
          {
            // yAxisIndex: 1,
            name: "呼吸",
            type: "line",
            color: "#5470c6",
            smooth: true,
            data: breathData,
            symbolSize: 4,
            areaStyle: {
              opacity: 0.4,
            },
          },
        ],
      };

      this.myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.echarts-wrapper {
  width: 600px;
  height: 400px;
  /* background-color: pink; */
}
</style>