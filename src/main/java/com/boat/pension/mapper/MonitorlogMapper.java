package com.boat.pension.mapper;

import com.boat.pension.model.Monitorlog;
import com.boat.pension.model.MonitorlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorlogMapper {
    long countByExample(MonitorlogExample example);

    int deleteByExample(MonitorlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Monitorlog record);

    int insertSelective(Monitorlog record);

    List<Monitorlog> selectByExample(MonitorlogExample example);

    Monitorlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Monitorlog record, @Param("example") MonitorlogExample example);

    int updateByExample(@Param("record") Monitorlog record, @Param("example") MonitorlogExample example);

    int updateByPrimaryKeySelective(Monitorlog record);

    int updateByPrimaryKey(Monitorlog record);
}