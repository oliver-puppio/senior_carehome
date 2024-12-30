package com.boat.pension.dto;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class SeniorParam {
//    @NotNull
//    @ApiModelProperty(value = "机构ID",required = true)
//    private Integer instid;
//    //老人所属机构ID由前端传过来？
    @ApiModelProperty(value = "照片",required = false)
    private String pic;
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @ApiModelProperty(value = "性别",required = true)
    private String gender;
    @ApiModelProperty(value = "身份证号",required = true)
    private String idnum;
    @ApiModelProperty(value = "联系方式",required = false)
    private String tel;
    @ApiModelProperty(value = "心率",required = true)
    private Integer heart;
    @ApiModelProperty(value = "呼吸",required = true)
    private Integer breath;
    @ApiModelProperty(value = "联系人1",required = true)
    private String cont1;
    @ApiModelProperty(value = "联系人电话1",required = true)
    private String ctel1;
    @ApiModelProperty(value = "联系人2",required = false)
    private String cont2;
    @ApiModelProperty(value = "联系人电话2",required = false)
    private String ctel2;
    @ApiModelProperty(value = "床位",required = false)
    private Integer bedid;
    @ApiModelProperty(value = "是否为社区",required = true)
    private Integer flag;
    @ApiModelProperty(value = "楼栋名",required = false)
    private String buildingname;
    @ApiModelProperty(value = "社区ID",required = false)
    private Integer communityid;
    @ApiModelProperty(value = "设备ID",required = false)
    private Integer deviceid;
    @ApiModelProperty(value = "房间号",required = false)
    private String num;


}
