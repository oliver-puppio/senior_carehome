package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 社区请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommunityParam {
    @NotEmpty
    @ApiModelProperty(value = "社区名称",required = true)
    private String name;
    @ApiModelProperty(value = "地址",required = true)
    private String loc;
    @NotEmpty
    @ApiModelProperty(value = "负责人姓名",required = true)
    private String leader;
    @NotEmpty
    @ApiModelProperty(value = "联系方式",required = true)
    private String tel;
//    @NotNull
//    @ApiModelProperty(value = "养老院ID",required = true)
//    private Integer aff;
    //社区所属养老院ID由前端传过来？
}
