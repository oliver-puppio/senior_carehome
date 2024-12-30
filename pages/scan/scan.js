// pages/scan/scan.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
Page({
  data: {
    device: "",
    inputdevice: "",
    // code: 200,
    // message: "",
  },

  //获取扫码内容
  getScancode: function () {
    wx.scanCode({
      success: (res) => {
        var device = res.result;
        this.setData({
          device: device,
        })
        this.bind()
        console.log(this.data.device)
        this.setData({
          device: "",
        })
      }
    })
  },

  async bind() {  //发送设备绑定请求
    //2.1添加绑定老人,4号老人不归汪汪管，可以添加，对应设备id是8
    const res = await request({
      url: 'user/senior/add/' + this.data.device,
      method: "POST",
      header: { 'content-type': 'application/x-www-form-urlencoded' },
    })
    console.log(res)
    // this.setData({
    //   code: res.code,
    //   message: res.message
    // })
    if(res.code != 200) {
      wx.showToast({
        title: res.message,
        icon: 'error',
      })
    }
    else {
      wx.showToast({
        title: "绑定成功",
        icon: 'success',
      })
    }
  },

  getInputValue(e){
    this.setData({
      inputdevice: e.detail["value"]
    })
  },
  
  enterbind() {
    if(this.data.inputdevice == "") {
      wx.showToast({
        title: '请输入设备号',
        icon: 'error',
      })
      return
    }

    var that = this
    wx.showModal({
      title: '提示',
      content: '确认绑定该设备？',
      success(res) {
        if (res.confirm) {
          that.setData({
            device: that.data.inputdevice,
          })
          that.bind()
          that.setData({
            inputdevice: "",
            device: ""
          })
          
        } else if (res.cancel) {
        }
      }
    })
  }
})