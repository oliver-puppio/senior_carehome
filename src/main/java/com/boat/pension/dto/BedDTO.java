package com.boat.pension.dto;

import com.boat.pension.model.Bed;
import com.boat.pension.model.Monitorlog;
import com.boat.pension.model.Senior;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.json.JSONObject;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class BedDTO {

    @ApiModelProperty("床位id")
    private Integer bedID;
    private Integer deviceID;
    private Integer seniorID;
    @ApiModelProperty("床位号")
    private Integer bedNum;
    private String name;
    private String gender;
    private String idNum;
    private String tel;
    private String cont1;
    private String cTel1;

    public BedDTO(Bed bed, Senior senior) {
        bedID=bed.getId();
        deviceID=bed.getDeviceid();
        seniorID=bed.getSeniorid();
        bedNum = bed.getNum();
        if (senior != null) {
            name= senior.getName();
            gender= senior.getGender();
            idNum= senior.getIdnum();
            tel=senior.getTel();
            cont1= senior.getCont1();
            cTel1=senior.getCtel1();
        }

    }
}
