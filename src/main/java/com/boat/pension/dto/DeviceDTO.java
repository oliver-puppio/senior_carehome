package com.boat.pension.dto;

import com.boat.pension.model.Device;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("设备显示")
public class DeviceDTO {
    @NotNull
    @ApiModelProperty(value = "设备id",required = true)
    private Integer deviceid;
    @NotEmpty
    @ApiModelProperty(value = "注册时间",required = true)
    private String registerTime;
    @ApiModelProperty(value = "详细位置",required = true)
    private String loc;
    @NotNull
    @ApiModelProperty(value = "设备状态",required = true)
    private Integer state;

    public DeviceDTO(Device device, String loc) {
        deviceid = device.getDeviceid();
        registerTime = TimeUtil.dateTime2String(device.getRegistertime());
        state = device.getState() + 1;
        this.loc = loc;
    }
}
