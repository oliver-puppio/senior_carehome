package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 养老院请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CarehomeParam {
    @ApiModelProperty(value = "养老院名称",required = true)
    private String name;
    @ApiModelProperty(value = "地址",required = true)
    private String loc;
    @ApiModelProperty(value = "公司名称",required = true)
    private String companyName;
    @ApiModelProperty(value = "营业执照",required = true)
    private String license;
    @ApiModelProperty(value = "负责人姓名",required = true)
    private String leader;
    @ApiModelProperty(value = "联系方式",required = true)
    private String tel;

}
