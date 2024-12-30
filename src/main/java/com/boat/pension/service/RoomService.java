package com.boat.pension.service;

import com.boat.pension.dto.RoomDTO;
import com.boat.pension.dto.RoomParam;
import com.boat.pension.model.Admin;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public interface RoomService {
    /***
     * 根据房间id获取房间内床位的状态
     * @param roomId 房间id
     * @return  形如{ id,num,state }的json对象
     */
    JSONArray getRoomBedState(Integer roomId);

    /**
     * 根据房间id获取房间
     * @param roomId 房间id
     * @return 房间
     */
    JSONObject getRoomDetailByRoomId(Integer roomId);
    /**
     * 根据机构id获取房间列表和入住情况
     * @param instId 机构id
     * @return 房间列表
     */
    List<RoomDTO> listAllByInstId(Integer instId,Integer flag);

    /***
     * 根据机构id获取其接入社区列表和入住情况
     * @param instId 养老院id
     * @return 社区列表
     */
    JSONArray listAllInCommunityByInstId(Integer instId);

    /**
     * 新增房间
     * @param roomParam 房间参数
     * @return 影响记录数
     */
    int addRoom(Admin admin, RoomParam roomParam);

    /**
     * 修改指定房间信息
     * @param id,roomParam 房间id，房间参数
     * @return 影响记录数
     */
    int update(Integer id, RoomParam roomParam);

    /**
     * 删除指定房间
     * @param id 房间id
     * @return 影响记录数
     */
    int delete(Integer id);
}
