package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 管理员请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminParam {
    @ApiModelProperty(value = "企业名称",required = true)
    private String cname;
    @ApiModelProperty(value = "营业执照",required = true)
    private String license;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "机构名称",required = true)
    private String name;
    @ApiModelProperty(value = "地点",required = true)
    private String loc;
    @ApiModelProperty(value = "负责人",required = true)
    private String leader;
    @ApiModelProperty(value = "联系方式",required = true)
    private String tel;
}
