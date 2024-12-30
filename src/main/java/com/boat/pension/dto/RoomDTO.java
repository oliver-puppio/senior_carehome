package com.boat.pension.dto;

import com.boat.pension.model.Building;
import com.boat.pension.model.Event;
import com.boat.pension.model.Room;
import com.boat.pension.model.Worker;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.json.JSONArray;

import java.util.List;

@Data
@ApiModel("房间信息传输对象")
public class RoomDTO {
    private Integer roomID;
    @ApiModelProperty("楼栋id")
    private Integer buildingID;
    @ApiModelProperty("楼栋名称")
    private String buildingname;
    @ApiModelProperty("房间号")
    private String roomnum;
    @ApiModelProperty("房间id")
    private String roomid;
    //    @ApiModelProperty("容纳人数")
//    private Integer size;
    @ApiModelProperty("负责人")
    private String workername;
    @ApiModelProperty("负责人联系方式")
    private String tel;

    @ApiModelProperty("床位空闲状态，0表示空闲，1表示有老人")
    private JSONArray states;

    private String chosenbed;

    public RoomDTO(Building building, Room room, JSONArray bedStates, Worker worker) {
        roomID=room.getId();
        buildingID = building.getId();
        buildingname = room.getBuildingname();
        roomnum = room.getNum();
        roomID = room.getId();
//        size = room.getSize();
        states = bedStates;
        if(worker != null){
            workername = worker.getName();
            tel = worker.getTel();
        }
        else {
            workername = null;
            tel = null;
        }
        chosenbed = null;
    }
}