package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 楼栋请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BuildingParam {
    @NotEmpty
    @ApiModelProperty(value = "楼栋名",required = true)
    private String name;

//    @NotNull
//    @ApiModelProperty(value = "机构ID",required = true)//前端会传过来？
//    private Integer instid;
}
