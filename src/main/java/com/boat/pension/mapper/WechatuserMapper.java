package com.boat.pension.mapper;

import com.boat.pension.model.Wechatuser;
import com.boat.pension.model.WechatuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WechatuserMapper {
    long countByExample(WechatuserExample example);

    int deleteByExample(WechatuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wechatuser record);

    int insertSelective(Wechatuser record);

    List<Wechatuser> selectByExample(WechatuserExample example);

    Wechatuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wechatuser record, @Param("example") WechatuserExample example);

    int updateByExample(@Param("record") Wechatuser record, @Param("example") WechatuserExample example);

    int updateByPrimaryKeySelective(Wechatuser record);

    int updateByPrimaryKey(Wechatuser record);
}