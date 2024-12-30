package com.boat.pension.dto;

import net.sf.json.JSONArray;
import com.boat.pension.model.Institution;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("老人列表")
public class SeniorDTO {
    @ApiModelProperty(value = "机构ID",required = true)
    private Integer instid;
    @ApiModelProperty(value = "机构名称",required = true)
    private String instName;
    @ApiModelProperty(value = "地址",required = true)
    private String loc;
    @ApiModelProperty(value = "负责人",required = true)
    private String leader;
    @ApiModelProperty(value = "联系方式",required = true)
    private String leaderTel;
    @ApiModelProperty(value = "老人数量",required = true)
    private Integer size;
    @ApiModelProperty(value = "老人列表",required = true)
    private JSONArray seniors;

    public SeniorDTO(Institution institution, JSONArray seniorList) {
        instid = institution.getId();
        instName = institution.getName();
        loc = institution.getLoc();
        leader = institution.getLeader();
        leaderTel = institution.getTel();
        size = seniorList.size();
        seniors = seniorList;
    }
}

