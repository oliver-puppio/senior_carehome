package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkerParam {
//    @NotNull
//    @ApiModelProperty(value = "机构ID",required = true)
//    private Integer instid;
    @NotEmpty
    @ApiModelProperty(value = "工号",required = true)
    private String num;
    @NotEmpty
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @NotEmpty
    @ApiModelProperty(value = "性别",required = true)
    private String gender;
    @NotEmpty
    @ApiModelProperty(value = "身份证号",required = true)
    private String idnum;
    @NotEmpty
    @ApiModelProperty(value = "联系方式",required = true)
    private String tel;
}
