// pages/monitor/monitor.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
Page({
  data: {
    isworker: false,
    ismonitoring: false,
    seniors: [],
    //length: 0,
    errors: [],
    seniorstime: [],
    errortime: [],
    errorlist: [],
    loanTime: "",
  },

  onLoad(options) {
    this.getseniorlist()
    this.getallerror()
    this.setData({
      isworker: getApp().globalData.isworker,
      ismonitoring: getApp().globalData.ismonitoring
    })
  },

  onShow() {
    this.getseniorlist()
    //console.log(this.data.seniors)
    this.getallerror()
    // this.getall()
  },

  start() {
    getApp().globalData.ismonitoring = true
    const that = this
    that.setData({
      ismonitoring: getApp().globalData.ismonitoring,
    })
    that.timer()
  },

  end() {
    const that = this
    var app = getApp()
    app.globalData.ismonitoring = false
    that.setData({
      ismonitoring: app.globalData.ismonitoring,
    })
    clearInterval(that.data.loanTime)
  },

  //定时器方法
  timer: function () {
    let that = this
    that.data.loanTime = setInterval(function () {
      that.getallerror()
      // if(that.data.errors.length != 0) {
      //   for (var i = 0; i < that.data.errors.length; i++) {
      //     that.gettimeerror(that.data.errors[i]["deviceID"])
      //     that.data.errors[i]["hearttime"] = that.data.errortime[i]["heart_rate"]
      //     that.data.errors[i]["breathtime"] = that.data.errortime[i]["breath_frequency"]
      //     if (that.data.errors[i]["breathtime"] == 0 && that.data.errors[i]["hearttime"] == 0) {
      //       that.data.errors[i]["bed_state"] = "离床"
      //     } else {
      //       that.data.errors[i]["bed_state"] = "睡眠"
      //     }
      //   }
      // }
      // that.setData({
      //   errors: that.data.errors,
      // })
      that.getallerror()
      for (var i = 0; i < that.data.seniors.length; i++) {
        that.gettimemonitor(that.data.seniors[i]["deviceID"])
        that.data.seniors[i]["hearttime"] = that.data.seniorstime[i]["heart_rate"]
        that.data.seniors[i]["breathtime"] = that.data.seniorstime[i]["breath_frequency"]
        if (that.data.seniors[i]["breathtime"] == 0 && that.data.seniors[i]["hearttime"] == 0) {
          that.data.seniors[i]["bed_state"] = "离床"
        } else {
          that.data.seniors[i]["bed_state"] = "睡眠"
        }
      }
      that.setData({
        seniors: that.data.seniors,
      })
    }, 1000)
  },

  getseniorlist() {
    wx.request({
      url: 'http://localhost:7015/api/user/senior/list/',
      method: 'GET',
      header: {
        Authorization: wx.getStorageSync('token')
      },

      success: (res) => {
        if(res.data.data.seniors) {
          this.setData({
            seniors: res.data.data.seniors,
            seniorstime: res.data.data.seniors,
            //length: res.data.seniors.length,
          })
        }
        else {
          this.setData({
            seniors: [],
            seniorstime: [],
            //length: res.data.seniors.length,
          })
        }
        //console.log(res)

      },
    })
  },

  gettimemonitor(device) {
    wx.request({
      url: 'http://localhost:7015/api/device/' + device + '/live',
      method: 'GET',
      header: {
        Authorization: wx.getStorageSync('token')
      },
      success: (res) => {
        //console.log(res)
        if(res.data.data) {
          //console.log("hhhhh")
          console.log(res.data.data)
          for(var i = 0; i < this.data.seniorstime.length; i++) {
            if(device == this.data.seniorstime[i].deviceID) {
              this.data.seniorstime[i].heart_rate = res.data.data["heart_rate"]
              this.data.seniorstime[i].breath_frequency = res.data.data["breath_frequency"]
            }
          }
        }
        this.setData({
          seniorstime: this.data.seniorstime,
          //errortime: this.data.errortime
        })
        //console.log(this.data.seniorstime)
      }
    })
  },
  
  gettimeerror(device) {
    wx.request({
      url: 'http://localhost:7015/api/device/' + device + '/live',
      method: 'GET',
      header: {
        Authorization: wx.getStorageSync('token')
      },
      success: (res) => {
        console.log(res) 
        for(var i = 0; i < this.data.errortime.length; i++) {
          if(device == this.data.errortime[i].deviceID) {
            this.data.errortime[i].heart_rate = res.data.data["heart_rate"]
            this.data.errortime[i].breath_frequency = res.data.data["breath_frequency"]
          }
        }
        this.setData({
          errortime: this.data.errortime
        })
        //console.log(this.data.errortime)
      }
    })
  },

  getallerror() {
    if(!this.data.seniors) return
    else {
      wx.request({
        url: 'http://localhost:7015/api/event/list/active',
        method: 'GET',
        header: {
          Authorization: wx.getStorageSync('token')
        },
        success: (res) => {
          console.log(res)
          //console.log(res)
          var errorlist = res.data.data
          if (errorlist != null) {
            for (var i = 0; i < errorlist.length; i++) {
              errorlist[i]["errorTime"] = this.gettime(errorlist[i]["startTime"])
              // this.gettimemonitor(errorlist[i]["deviceID"]) //this.data.seniors[i]["deviceID"]
              // if (this.data.seniorstime != null) {
              //   errorlist[i]["hearttime"] = this.data.seniorstime["heart_rate"]
              //   errorlist[i]["breathtime"] = this.data.seniorstime["breath_frequency"]
              //   if (this.data.seniorstime["breath_frequency"] == 0 && this.data.seniorstime["heart_rate"] == 0) {
              //     errorlist[i]["bed_state"] = "离床"
              //   } else errorlist[i]["bed_state"] = "睡眠"
              // }
            }
          }
          this.setData({
            errors: errorlist,
            errortime: errorlist
          })
        },
        fail:(res) => {
          console.log("fail")
          console.log(res)
        },
      })
    }
  },

  //字符串转datetime
  getstarttime(dateString) {
    var arr1 = dateString.split(" ")
    var sdate = arr1[0].split('-')
    var tdate = arr1[1].split(':')
    var date = new Date(sdate[0], sdate[1] - 1, sdate[2], tdate[0], tdate[1], tdate[2])
    return date
  },

  //计算异常持续时间
  gettime(dateString) {
    var timestamp = Date.parse(new Date(dateString))
    var timenow = Date.parse(new Date())
    var time = timenow - timestamp
    var leave1 = time % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
    var hours = Math.floor(leave1 / (3600 * 1000))
    var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
    var minutes = Math.floor(leave2 / (60 * 1000))
    var leave3 = leave2 % (60 * 1000) //计算分钟数后剩余的毫秒数
    var seconds = Math.round(leave3 / 1000)
    return hours + "h " + minutes + "min " + seconds + "s"
  },

  //确认
  enter: function (e) {
    const that = this
    wx.showModal({
      title: '提示',
      content: '确认收到异常信息',

      success(res) {
        if (res.confirm) {
          that.confirm(e.currentTarget.dataset.value)

        } else if (res.cancel) {}
      }
    })
  },

  async confirm(event) {
    const res = await request({
      url: 'event/' + event + '/confirm',
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
    })
    //console.log(res)
    this.getallerror()
    this.getseniorlist()
  },

    // getallinfo() {
  //   this.getseniorlist()
  //   for (var i = 0; i < this.data.length; ++i) {
  //     gettimemonitor(this.data.seniors[i]["deviceID"])
  //     this.data.seniors[i]["hearttime"] = this.data.seniorstime["heart_rate"]
  //     this.data.seniors[i]["breathtime"] = this.data.seniorstime["breath_frequency"]
  //     geterror(this.data.data.seniors[i]["id"])
  //     this.data.seniors[i]["starttime"] = this.data.errors["startTime"]
  //     if (this.data.errors.length != 0) {
  //       if (this.data.errors["state"] == 0) {
  //         this.data.seniors[i]["bed_state"] = "睡眠"
  //       } else {
  //         this.data.seniors[i]["bed_state"] = "离床"
  //       }

  //       if (this.data.errors["type"] == 0) {
  //         this.data.seniors[i]["bed_state"] = "数据异常"
  //       } else {
  //         this.data.seniors[i]["bed_state"] = "离床未归"
  //       }

  //       this.data.seniors[i]["errortime"] = this.gettime(this.data.errors["startTime"])
  //     }
  //   }
  // },

  // updateinfo() {
  //   for (var i = 0; i < this.data.length; ++i) {
  //     gettimemonitor(this.data.seniors[i]["deviceID"])
  //     this.data.seniors[i]["hearttime"] = seniorstime["heart_rate"]
  //     this.data.seniors[i]["breathtime"] = seniorstime["breath_frequency"]
  //     this.data.seniors[i]["errortime"] = this.gettime(this.data.seniors[i]["startTime"])
  //   }
  // },

  // geterror(senior) {
  //   wx.request({
  //     url: 'http://localhost:7015/api/senior/' + senior + '/events',
  //     method: 'GET',
  //     header: {
  //       Authorization: wx.getStorageSync('token')
  //     },
  //     success: (res) => {
  //       console.log(res)
  //       // this.setData({
  //       //   errors: [res.data]
  //       // })
  //     }
  //   })
  // },
})