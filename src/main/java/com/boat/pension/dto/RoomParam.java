package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoomParam {
    @ApiModelProperty(value = "楼栋id",required = true)
    private Integer buildingid;
    @ApiModelProperty(value = "房间号",required = true)
    private String num;
    @ApiModelProperty(value = "容量",required = true)
    private Integer size;
    @ApiModelProperty(value = "护工ID",required = true)
    private Integer wokerid;
    @ApiModelProperty(value = "机构ID",required = true)
    private Integer instid;

}
