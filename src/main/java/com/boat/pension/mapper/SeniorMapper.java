package com.boat.pension.mapper;

import com.boat.pension.model.Senior;
import com.boat.pension.model.SeniorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeniorMapper {
    long countByExample(SeniorExample example);

    int deleteByExample(SeniorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Senior record);

    int insertSelective(Senior record);

    List<Senior> selectByExample(SeniorExample example);

    Senior selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Senior record, @Param("example") SeniorExample example);

    int updateByExample(@Param("record") Senior record, @Param("example") SeniorExample example);

    int updateByPrimaryKeySelective(Senior record);

    int updateByPrimaryKey(Senior record);
}