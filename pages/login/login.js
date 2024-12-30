// pages/login/login.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

Page({
  data: {
    //code: 0,
    userInfo: {},
    gotUserInfo: false,
    isOn: false,
  },

  onLoad(options) {
    //登录检查，获取code发送至后端获取token；返回用户信息，
    //若为空则提示用户注册，显示注册按钮
    //否则跳转主页，并将用户信息存入缓存。

  },

  onShow() {
    this.tryLogin();
  },

  async tryLogin() {
    wx.login({
      success: (res) => {
        console.log(res)
        let code = res.code;
        request({
          url: "user/wxlogin",
          method: "POST",
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: {
            code: code
          }
        }).then((result, err) => {
          if (result) {
            //console.log(result)
            const {
              token
            } = result.data;
            wx.setStorageSync('token', token)
            this.getuserinfo()
            //this.checkReservation();
          } else {
            //this.tryLogin()
            //console.log(err);
          }
        })
      }
    })
  },

  // 获取手机号码
  async getPhoneNumber(e) {
    //console.log(e)
    const {
      code
    } = e.detail;
    if (code == null || code == "") return; //设置拦截

    const userInfo = await request({
      url: "user/register",
      method: "POST",
      data: {
        code
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      }
    })
    //console.log(userInfo)
    if(userInfo.code == 200) {
      this.getuserinfo()
    }
  },

  async getuserinfo() {
    //1.获取用户详情
    const res = await request({
      url: 'user/detail'
    })
    console.log(res)
    if (res.data) {
      wx.setStorageSync('phoneNumber', res.data);
      this.setData({
        userInfo: res.data,
        isOn: true,
        gotUserInfo: true
      });

      if(res.data.user["workerID"] == 1) {
        var app = getApp()
        app.globalData.isworker = true
      }
      wx.switchTab({
        url: '/pages/monitor/monitor'
      })
    }
  },

})