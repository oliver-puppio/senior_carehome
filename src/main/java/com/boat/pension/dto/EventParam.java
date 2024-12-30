package com.boat.pension.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 异常事件请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventParam {
    @ApiModelProperty(value = "老人ID",required = true)
    private Integer seniorid;
    @ApiModelProperty(value = "设备ID",required = true)
    private Integer deviceid;
//    @NotNull
//    @ApiModelProperty(value = "机构ID",required = true)
//    private Integer instid;
    @ApiModelProperty(value = "异常类型",required = true)
    private Integer type;
}
