package com.boat.pensionwx.util;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import net.sf.json.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatUtil
 * @Description TODO
 * @Author eval
 * @Date 9:44 2019/3/20
 * @Version 1.0
 */
@Component
public class WechatUtil {
    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;
    public JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", appid);
        //小程序secret
        requestUrlParam.put("secret", secret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSONObject.fromObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    private JSONObject getAccessToken(){
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", appid);
        //小程序secret
        requestUrlParam.put("secret", secret);
        //默认参数
        requestUrlParam.put("grant_type", "client_credential");
        //发送get请求读取调用微信接口获取access_token
        JSONObject jsonObject = JSONObject.fromObject(HttpClientUtil.doGet(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public JSONObject getPhoneNumber(String code){
        JSONObject accessMessage=getAccessToken();
        String accessToken=accessMessage.getString("access_token");
        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+accessToken;
        JSONObject obj = new JSONObject();
        //小程序发来的code
        obj.put("code", code);
        //发送Post请求读取调用微信接口获取用户手机号
        JSONObject jsonObject = JSONObject.fromObject(HttpClientUtil.doPostJson(requestUrl, obj.toString()));
        return jsonObject;
    }

    public JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.fromObject(result);
            }
        } catch (Exception e) {
        }
        return null;
    }
}