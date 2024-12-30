package com.boat.pension.mapper;

import com.boat.pension.model.Institution;
import com.boat.pension.model.InstitutionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstitutionMapper {
    long countByExample(InstitutionExample example);

    int deleteByExample(InstitutionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Institution record);

    int insertSelective(Institution record);

    List<Institution> selectByExample(InstitutionExample example);

    Institution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Institution record, @Param("example") InstitutionExample example);

    int updateByExample(@Param("record") Institution record, @Param("example") InstitutionExample example);

    int updateByPrimaryKeySelective(Institution record);

    int updateByPrimaryKey(Institution record);
}