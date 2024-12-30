// pages/handleresult/handleresult.js
import {
  request,
} from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    errorid: 0,
    seniorid: 0,
    name: "",
    gender: "", 
    location: "",
    type: 0,
    handler: "",
    starttime: "",
    entertime: "",

    checked: false,
    label: [],
    textareatext: "",
    resulttext: "",
    obtnArry: [
      {
        name: "设备异常",
        num: 0,
        selected: false,
      },
      {
        name: "起夜摔倒",
        num: 1,
        selected: false,
      },
      {
        name: "送往医院",
        num: 2,
        selected: false,
      },
      {
        name: "已恢复",
        num: 3,
        selected: false,
      },
      {
        name: "逐渐平稳",
        num: 4,
        selected: false,
      },
      {
        name: "不幸离世",
        num: 5,
        selected: false,
      },
    ]
  },

  onLoad: function(options) {
    this.setData({
      errorid: options["errorid"],
      seniorid: options["seniorid"],
      name: options["name"],
      gender: options["gender"],
      location: options["location"],
      type: options["type"],
      handler: options["handler"],
      starttime: options["starttime"],
      entertime: options["entertime"],
    })
  },

  onReady() {

  },

  onShow() {

  },

  //处理提交
  handle(){
    const that = this
    wx.showModal({
      title: '提示',
      content: '确定已解决相应异常？',
      success(res) {
        if (res.confirm) {
          var text = ""
          for(var i = 0; i < that.data.label.length; i++) {
            text = text + that.data.label[i] + " "
          }
          text = that.data.textareatext + " " + text
          that.setData({
            resulttext: text
          })
          if(that.data.resulttext == " ") {
            wx.showToast({
              title: '请输入处理结果',
              icon: 'error',
            })
            return
          }
          that.finish()
          wx.switchTab({
            url: '/pages/errorlist/errorlist',
            // success: function(back) {
            //   var page = getCurrentPages().pop();
            //   if (page == undefined || page == null) return;
            //   page.onShow();
            //   console.log('跳转到index页面成功')  
            // },
            // fail: function(back) {
            //   console.log('跳转到index页面失败')
            // },
            // complete: function(back) {
            //   console.log('跳转到index页面完成')
            // }
          })          
          console.log('用户点击确定' + that.data.resulttext)
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  // 选择标签并输入到label数组中
   dealTap: function(e) {
      let string = "obtnArry[" + e.target.dataset.index + "].selected";
      // const checkedicon = "obtnArry[" + e.target.dataset.index + "].selected"; 
      console.log(!this.data.obtnArry[e.target.dataset.index].selected);
      this.setData({
        [string]: !this.data.obtnArry[e.target.dataset.index].selected
      })
      let detailValue = this.data.obtnArry.filter(it => it.selected).map(it => it.name)
      this.setData({
        label: detailValue
      })
      console.log(this.data.label)
    },

    async finish() { 
      const res = await request({
        url: 'event/' + this.data.errorid + '/finish',
        method: "POST",
        header: { 'content-type': 'application/x-www-form-urlencoded' },
        data: { 'resultText' : this.data.resulttext }
      })
      //console.log(res)
    },

    gettext: function(e) {
      this.setData({
        textareatext: e.detail.value
      }) 
   }, 
})