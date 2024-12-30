package com.boat.pension.service;

import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.dto.CommunityParam;
import com.boat.pension.model.Institution;

import java.util.List;

/**
 * 社区管理Service
 * Created by hxy on 2022/7/1.
 */
public interface CommunityService {
    /**
     * 根据养老院id获取社区列表
     * @param instId 机构id
     * @return 机构列表
     */
    List<Institution> listAllByInstid(Integer instId);

    /**
     * 新增社区
     * @param communityParam 社区参数
     * @return 影响记录数
     */
    int addCommunity(Integer instId, CommunityParam communityParam);

    /**
     * 修改指定社区信息
     * @param id,communityParam 社区id，社区参数
     * @return 影响记录数
     */
    int update(Integer id, CommunityParam communityParam);

    /**
     * 删除指定社区
     * @param id 社区id
     * @return 影响记录数
     */
    int delete(Integer id);
}
