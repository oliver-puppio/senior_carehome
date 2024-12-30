package com.boat.pensionwx.dto;

import com.boat.pension.model.Wechatuser;
//import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
//@ApiModel("用户详细信息")
public class WechatUserDetailDTO {
    private String tel;
    private Integer isWorker;
    private Integer workerID;

    public WechatUserDetailDTO(Wechatuser user) {
        tel = user.getTel();
        isWorker = user.getIsworker();
        workerID = user.getWorkerid();
    }
}
