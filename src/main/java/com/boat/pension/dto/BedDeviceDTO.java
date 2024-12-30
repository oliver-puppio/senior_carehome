package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 床位设备添加/解绑请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BedDeviceDTO {
    @ApiModelProperty(value = "设备id",required = true)
    private Integer deviceid;
    @ApiModelProperty(value = "床位id",required = true)
    private Integer bedid;
}
