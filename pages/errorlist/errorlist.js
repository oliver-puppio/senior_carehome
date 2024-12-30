// pages/errorlist/errorlist.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

Page({
  data: {
    isworker: false,
    seniorlist: [],
    seniorids: [],
    errorlist: [],
    unworkerlength: 0,
  },

  onLoad(options) {
    this.setData({
      isworker: getApp().globalData.isworker
    })
    this.getallinfo()
    
  },

  onShow() {
    this.getallinfo()
  },

  //确认
  enter: function(e) {
    const that = this
    wx.showModal({
      title: '提示',
      content: '确认收到异常信息',
      success(res) {
        if (res.confirm) {
          that.confirm(e.currentTarget.dataset.value)
          that.getallinfo()
        } else if (res.cancel) {
        }
      }
    })
  },

  async getallinfo() {
    //2.0获取老人列表
    const res = await request({
      url: 'user/senior/list'
    })
    this.setData({
      seniorlist: res.data.seniors,
    })
    var zhong = []
    if (res.data.seniors != null) {
      for (var i = 0; i < res.data.seniors.length; i++) {
        zhong.push(res.data.seniors[i]["id"])
      }
    }
    this.setData({
      seniorids: zhong
    })
///////
    this.getallerror()
    this.setData({
      seniorids: zhong
    })
    // var elist = []
    // for (var i = 0; i < this.data.seniorids.length; i++) {
    //   const res = await request({
    //     url: 'senior/' + this.data.seniorids[i] + '/events',
    //   })
    //   if(res.data != null) {
    //     for(var j = 0; j < res.data.length; j++) {
    //       elist.push(res.data[j])
    //     }
    //   }
    // }
    // var t = 0
    // if(elist != null) {
    //   for(var i = 0; i < elist.length; i++) {
    //     if(elist[i]["endTime"] != null) {
    //       t++
    //     }
    //   }
    // }
    // elist.sort(function(a, b) {
    //   return b.startTime < a.startTime ? -1 : 1
    // })

    // this.setData({
    //   errorlist: elist,
    //   unworkerlength: t
    // })
  },

  async confirm(event) { 
    const res = await request({
      url: 'event/' + event + '/confirm',
      method: "POST",
      header: { 'content-type': 'application/x-www-form-urlencoded' },
    })
    //console.log(res)
  },

  async getallerror() {
    const res = await request({
      url: 'event/list'
    })

    this.setData({
      errorlist: res.data,
    })
    var t = 0
    if(this.data.errorlist != null) {
      for(var i = 0; i<this.data.errorlist.length; i++) {
        if(this.data.errorlist[i]["endTime"] != null) {
          t++
        }
      }
    }
    this.setData({
      unworkerlength: t
    })
    console.log(res)
    // wx.request({
    //   url: 'http://localhost:7015/api/event/list',
    //   method: 'GET',
    //   header: {
    //     Authorization: wx.getStorageSync('token')
    //   },
    //   success: (res) => {
    //     console.log(res)
    //     // this.setData({
    //     //   errors: [res.data]
    //     // })
    //   }
    // })
  },

  // handle:function(e) {
  //   console.log(e.currentTarget.dataset.item)
  //   wx.navigateTo({
  //     url: '/pages/handleresult/handleresult?error=' + JSON.stringify(e.currentTarget.dataset.item),
  //   })
  // },

})