//同时发送异步代码的次数
let ajaxTimes = 0;
export const request = (params) => {
    //判断url中是否带有my,若存在则说明这是一个私有路径，需要附加请求头header token
    let header = {...params.header };
    header["Authorization"] = wx.getStorageSync('token');
    ajaxTimes++;
    wx.showLoading({
        title: '加载中',
        mask: true
    });

    // 定义公共url
    //http://localhost:50364/api/
    //https://81.70.119.202:88/api/
    const baseUrl = 'http://localhost:7015/api/';
    //const baseUrl = "https://81.70.119.202:88/api/";

    return new Promise(
        (resolve, reject) => {
            wx.request({
                ...params,
                header: header,
                url: baseUrl + params.url,
                success: (result) => { resolve(result.data); },
                fail: (err) => { reject(err) },
                complete: () => {
                    ajaxTimes--;
                    if (ajaxTimes == 0) {
                        wx.hideLoading();
                    }
                }
            });
        }
    );
}


//测试开放接口数据
let testajaxTimes = 0;
export const testrequest = (params) => {
    // 判断url中是否带有my,若存在则说明这是一个私有路径，需要附加请求头header token
    let header = {...params.header };

    //下一句可能得改，我们不一定用到my
    if (params.url.includes("/my/")) {
        header["Authorization"] = wx.getStorageSync('token');
    }
    testajaxTimes++;
    wx.showLoading({
            title: '加载中',
            mask: true
        })
        // 定义公共url
        //localtest http://localhost:50364
        //https://81.70.119.202:88
    const baseUrl = "https://api-hmugo-web.itheima.net/api/public/v1";
    return new Promise(
        (resolve, reject) => {
            wx.request({
                ...params,
                header: header,
                url: baseUrl + params.url,
                success: (result) => { resolve(result.data.message); },
                fail: (err) => { reject(err) },
                complete: () => {
                    testajaxTimes--;
                    if (testajaxTimes == 0) {
                        wx.hideLoading();
                    }
                }
            });
        }
    );
}