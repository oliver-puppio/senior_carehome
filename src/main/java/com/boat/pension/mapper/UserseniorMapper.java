package com.boat.pension.mapper;

import com.boat.pension.model.Usersenior;
import com.boat.pension.model.UserseniorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserseniorMapper {
    long countByExample(UserseniorExample example);

    int deleteByExample(UserseniorExample example);

    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("seniorid") Integer seniorid);

    int insert(Usersenior record);

    int insertSelective(Usersenior record);

    List<Usersenior> selectByExample(UserseniorExample example);

    Usersenior selectByPrimaryKey(@Param("userid") Integer userid, @Param("seniorid") Integer seniorid);

    int updateByExampleSelective(@Param("record") Usersenior record, @Param("example") UserseniorExample example);

    int updateByExample(@Param("record") Usersenior record, @Param("example") UserseniorExample example);

    int updateByPrimaryKeySelective(Usersenior record);

    int updateByPrimaryKey(Usersenior record);
}