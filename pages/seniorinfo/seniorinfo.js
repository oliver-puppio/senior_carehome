// pages/seniorinfo/seniorinfo.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var wxCharts = require('../../utils/wxcharts.js');
var lineChart = null;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    isworker: false,
    ismonitoring: false,
    seniorid: "",
    haveerror: true,
    errorlength: 0,
    seniorinfo: [],
    errorlist: [],
    seniortime: [],
    // x_data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
    // heart_data: [48, 46, 46, 50, 51, 47, 49, 48, 48, 46, 53, 49],
    // breath_data: [16, 15, 16, 17, 15, 18, 17, 16, 17, 15, 19, 21],
    // textcolor1: '#014f8e',
    // textcolor2: '#bfbfbf',
  },

  onLoad: function (options) {
    //get senior/{id}/detail
    //将返回结果存入data中以供渲染
    //下面是心率显示的数据，只需替换掉数据折现就会发生变化实现动态生成
    //todo：在老人详情中添加异常记录和健康报告跳转页面
    //绘制折线图
    this.setData({
      seniorid: options["id"],
      isworker: getApp().globalData.isworker,
      ismonitoring: getApp().globalData.ismonitoring
    })
    this.getseniorinfo(this.data.seniorid)
    this.geterrorlist(this.data.seniorid)
    //this.getseniorinfo(2)
    //this.unbind(11)
    //this.getallinfo()
    //this.OnWxChart(this.data.x_data, this.data.heart_data, '心率')
  },

  onshow() {},

  //更换折线图数据为图表一数据
  // canvas1_click: function () {
  //   this.setData({
  //     textcolor1: '#014f8e',
  //     textcolor2: '#bfbfbf',
  //   })
  //   this.OnWxChart(this.data.x_data, this.data.heart_data, '心率')
  // },

  // //更换折线图数据为图表二数据
  // canvas2_click: function () {
  //   this.setData({
  //     textcolor1: '#bfbfbf',
  //     textcolor2: '#014f8e',
  //   })
  //   this.OnWxChart(this.data.x_data, this.data.breath_data, '呼吸')
  // },

  // //图表点击事件
  // touchcanvas: function (e) {
  //   lineChart.showToolTip(e, {
  //     format: function (item, category) {
  //       return item.name + '：' + item.data
  //     }
  //   });
  // },

  // //折线图绘制方法
  // OnWxChart: function (x_data, y_data, name) {
  //   var windowWidth = '',
  //     windowHeight = ''; //定义宽高
  //   try {
  //     var res = wx.getSystemInfoSync(); //试图获取屏幕宽高数据
  //     windowWidth = res.windowWidth / 750 * 630; //以设计图750为主进行比例算换
  //     windowHeight = res.windowWidth / 750 * 500 //以设计图750为主进行比例算换
  //   } catch (e) {
  //     console.error('getSystemInfoSync failed!'); //如果获取失败
  //   }
  //   lineChart = new wxCharts({
  //     canvasId: 'lineCanvas', //输入wxml中canvas的id
  //     type: 'line',
  //     categories: x_data, //模拟的x轴横坐标参数
  //     animation: false, //是否开启动画
  //     background: "#F0F0F0",

  //     series: [{
  //       name: name,
  //       data: y_data,
  //       format: function (val, name) {
  //         return val;
  //       }
  //     }],
  //     xAxis: { //是否隐藏x轴分割线
  //       disableGrid: true,
  //     },
  //     yAxis: { //y轴数据
  //       title: '', //标题
  //       format: function (val) { //返回数值
  //         return val.toFixed(0);
  //       },
  //       min: 400000.00, //最小值
  //       gridColor: '#D8D8D8',
  //     },
  //     width: windowWidth * 1.1, //图表展示内容宽度
  //     height: windowHeight, //图表展示内容高度
  //     dataLabel: false, //是否在图表上直接显示数据
  //     dataPointShape: true, //是否在图标上显示数据点标志
  //     extra: {
  //       lineStyle: 'Broken' //曲线
  //     },
  //   });
  // },

  //解绑
  delete() {
    const that = this
    wx.showModal({
      title: '提示',
      content: '确认解绑设备相关的老人？',
      success(res) {
        if (res.confirm) {
          that.unbind(that.data.seniorid)
          wx.switchTab({
            url: '/pages/monitor/monitor',
            success: function(e) {
              var page = getCurrentPages().pop();
              if (page == undefined || page == null) return;
              page.onLoad();
          }
          })
          //wx.navigateBack()
          //console.log('用户点击确定')
        } else if (res.cancel) {
          //console.log('用户点击取消')
        }
      }
    })
  },

  async getseniorinfo(senior) {
    //3.0获取老人详情 2
    const res = await request({
      url: 'senior/' + senior + '/detail'
    })
    this.setData({
      seniorinfo: res.data
    })
    console.log(res)
  },

  // gettimemonitor(device) {
  //   // //4.获取老人实时健康监测记录,输入的是设备id  4
  //   // const res = await request({
  //   //   url: 'device/' + device + '/live',
  //   // })
  //   // this.setData({
  //   //   seniortime: [res.data]
  //   // })
  //   wx.request({
  //     url: 'device/' + device + '/live',
  //     method: 'GET',
  //     success: (res) => {
  //       console.log(res)
  //       this.setData({
  //         seniortime: [res.data]
  //       })
  //     }
  //   })
  // },

  async geterrorlist(senior) {
    //3.1获取老人异常事件列表 2
    const res = await request({
      url: 'senior/' + senior + '/events',
    })
    console.log(res)
    var j = 0
    if(res.data != null) {
      for(var i = 0; i < res.data.length; i++) {
        if(res.data[i]["endTime"] != null) {
          j++
        }
      }
    }

    this.setData({
      errorlist: res.data,
      errorlength: j
    })
  },

  async unbind(senior) {
    //2.2解除绑定老人，只能删除原来自己手动绑定的老人。这里把原来绑定的4号老人又删掉了 4
    const res = await request({
      url: 'user/senior/delete/' + senior,
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
    })
  },

  // getallinfo() {
  //   this.getseniorinfo(this.data.seniorid)
  //   this.geterrorlist(this.data.seniorid)
  //   this.gettimemonitor(this.data.seniorid)
  //   this.data.seniorinfo["hearttime"] = this.data.seniortime["heart_rate"]
  //   this.data.seniorinfo["breathtime"] = this.data.seniortime["breath_freguency"]
  //   if (this.data.seniortime["heart_rate"] == 0 && this.data.seniortime["breath_freguency"] == 0) {
  //     this.data.seniorinfo["bed_state"] = "离床"
  //   } else {
  //     this.data.seniorinfo["bed_state"] = "睡眠"
  //   }
  //   this.data.heart_data = new Array(12).fill(this.data.seniorinfo["hearttime"] == 0 ? null : this.data.seniorinfo["hearttime"])
  //   this.data.breath_data = new Array(12).fill(this.data.seniorinfo["breathtime"] == 0 ? null : this.data.seniorinfo["breathtime"])
  // },

  // updatearray() {
  //   this.gettimemonitor(this.data.seniorid)
  //   this.data.heart_data.shift()
  //   this.data.heart_data.push(this.data.seniortime["heart_rate"])
  //   this.data.breath_data.shift()
  //   this.data.breath_data.push(this.data.seniortime["breath_rate"])
  // },

})