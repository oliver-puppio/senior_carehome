// pages/report/report.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var wxCharts = require('../../utils/wxcharts.js');
var lineChart1 = null;
var lineChart2 = null;
var lineChart = [lineChart1, lineChart2]

Page({
  data: {
    //url: app.domain,
    // tabs: ['月', '周', '日'],
    // current: 0,
    seniorid: "",
    havedata: false,
    date_time: "",
    num: 0,
    startTime: "",
    endTime: "",
    x_data: [],
    heart_data: [],
    breath_data: [],
    // x: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    // x: ["","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""],
    // heart: [48, 46, 46, 50, 51, 47, 49, 48, 48, 46, 46, 50, 51, 47, 49, 48, 48,46, 46, 50, 51, 47, 49, 48, 48, 46, 46, 50, 51, 47, 49, 48, 48,46],
    // breath: [16, 15, 16, 17, 15, 18, 17, 16, 17, 15, 16, 17, 15, 18, 17, 16, 17,15, 16, 17, 15, 18, 17, 16, 17,15, 16, 17, 15, 18, 17, 16, 17,15],
    alltime: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      seniorid: options["id"],
    })
    this.getmonitorlog(this.data.seniorid)

  },

  async getmonitorlog(senior) {
    //3.2获取老人健康日志
    const res = await request({
      url: 'senior/' + senior + '/log',
    })
    console.log(res)
    // for(var i = 0; i < res.data.breathArr.length; i++) {
    //   this.data.x_data.push(res.data[i].time)
    //   this.data.breath_data.push(res.data.breathArr[i].breath)
    //   this.data.heart_data.push(res.data.heartArr[i].heart)
    // }
    if (res.data != null) {
      this.setData({
        date_time: res.data.logDate,
        num: res.data.num,
        breath_data: res.data.breathArr.breath,
        heart_data: res.data.heartArr.heart,
        startTime: res.data.startTime.slice(12),
        endTime: res.data.endTime.slice(12),
      })
      var t = this.data.alltime.unshift(res.data.logDate)
      this.data.x_data = new Array(this.data.heart_data.length).fill("")
    }
    console.log(this.data.date_time)
    if (this.data.date_time != "") {
      //绘制折线图
      this.OnWxChart(this.data.x_data, this.data.heart_data, 'lineCanvas1', '心率', 0)
      this.OnWxChart(this.data.x_data, this.data.breath_data, 'lineCanvas2', '呼吸', 1)
      this.setData({
        havedata: true
      })
    } else {
      this.setData({
        date_time: "暂无数据",
        havedata: false
      })
    }
  },

  async getminusdaymonitorlog(senior, date_time) {
    //3.3按日期获取老人健康日志'2022-07-22'
    const res = await request({
      url: 'senior/' + senior + '/log/' + date_time,
    })
    console.log(res)
    this.setData({
      date_time: res.data.logDate,
      num: res.data.num,
      breath_data: res.data.breathArr.breath,
      heart_data: res.data.heartArr.heart,
      startTime: res.data.startTime.slice(12),
      endTime: res.data.endTime.slice(12),
    })
    var t = this.data.alltime.unshift(res.data.logDate)
    this.data.x_data = new Array(this.data.heart_data.length).fill("")
    this.OnWxChart(this.data.x_data, this.data.heart_data, 'lineCanvas1', '心率', 0)
    this.OnWxChart(this.data.x_data, this.data.breath_data, 'lineCanvas2', '呼吸', 1)
  },

  async getadddaymonitorlog(senior, date_time) {
    const res = await request({
      url: 'senior/' + senior + '/log/' + date_time,
    })
    console.log(res)
    this.setData({
      date_time: res.data.logDate,
      num: res.data.num,
      breath_data: res.data.breathArr.breath,
      heart_data: res.data.heartArr.heart,
      startTime: res.data.startTime.slice(12),
      endTime: res.data.endTime.slice(12),
    })
    var t = this.data.alltime.shift(res.data.logDate)
    this.data.x_data = new Array(this.data.heart_data.length).fill("")
    this.OnWxChart(this.data.x_data, this.data.heart_data, 'lineCanvas1', '心率', 0)
    this.OnWxChart(this.data.x_data, this.data.breath_data, 'lineCanvas2', '呼吸', 1)
  },

  minus() {
    var _time = this.data.date_time
    if (this.data.num == 1) {
      wx.showToast({
        title: '已是最早数据',
        icon: 'error',
      })
      return
    } else {
      this.getminusdaymonitorlog(this.data.seniorid, this.minusdate(_time))
    }
  },

  add() {
    var _time = this.data.alltime[1]
    if (this.data.alltime.length == 1) {
      wx.showToast({
        title: '已是最新数据',
        icon: 'error',
      })
      return
    }
    this.getadddaymonitorlog(this.data.seniorid, _time)
  },

  gettime(dateString) {
    var sdate = dateString.split('-')
    var date = new Date(sdate[0], sdate[1] - 1, sdate[2])
    return date
  },

  dateToString(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString();
    var day = (date.getDate()).toString();
    if (month.length == 1) {
      month = "0" + month;
    }
    if (day.length == 1) {
      day = "0" + day;
    }
    var dateTime = year + "-" + month + "-" + day;
    return dateTime;
  },

  minusdate(time) {
    var date = this.gettime(time).setDate(this.gettime(time).getDate() - 1)
    return this.dateToString(new Date(date))
  },

  adddate(time) {
    var date = this.gettime(time).setDate(this.gettime(time).getDate() + 1)
    return this.dateToString(new Date(date))
  },

  //折线图绘制方法
  OnWxChart: function (x_data, y_data, Id, name, i) {
    var windowWidth = '',
      windowHeight = ''; //定义宽高
    try {
      var res = wx.getSystemInfoSync(); //试图获取屏幕宽高数据
      windowWidth = res.windowWidth / 750 * 630; //以设计图750为主进行比例算换
      windowHeight = res.windowWidth / 750 * 500 //以设计图750为主进行比例算换
    } catch (e) {
      console.error('getSystemInfoSync failed!'); //如果获取失败
    }

    lineChart[i] = new wxCharts({
      canvasId: Id, //输入wxml中canvas的id
      type: 'line',
      // type: 'area',
      
      categories: x_data, //模拟的x轴横坐标参数
      animation: true, //是否开启动画
      background: "#F0F0F0",

      series: [{
        name: name,
        data: y_data,
        format: function (val, name) {
          return val;
        }
      }],
      xAxis: { //是否隐藏x轴分割线
        disableGrid: true,
      },
      yAxis: { //y轴数据
        title: '', //标题
        format: function (val) { //返回数值
          return val.toFixed(0);
        },
        min: 400000.00, //最小值
        gridColor: '#D8D8D8',
      },
      width: windowWidth * 1.1, //图表展示内容宽度
      height: windowHeight, //图表展示内容高度
      dataLabel: false, //是否在图表上直接显示数据
      dataPointShape: true, //是否在图标上显示数据点标志
      extra: {
        lineStyle: 'curve' //曲线
      },
    });
  },

  //图表点击事件
  touchcanvas0: function (e) {
    lineChart[0].showToolTip(e, {
      format: function (item, category) {
        return item.data
      }
    });
  },

  //图表点击事件
  touchcanvas1: function (e) {
    lineChart[1].showToolTip(e, {
      format: function (item, category) {
        return item.data
      }
    });
  },

  // tabSelect: function (e) {
  //   var current = e.currentTarget.dataset.id
  //   this.setData({
  //     current: current
  //   })
  // },
})