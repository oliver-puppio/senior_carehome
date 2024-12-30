package com.boat.pension.mapper;

import com.boat.pension.model.Institution;
import com.boat.pension.model.InstitutionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstitutionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    long countByExample(InstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int deleteByExample(InstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int insert(Institution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int insertSelective(Institution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    List<Institution> selectByExample(InstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    Institution selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int updateByExampleSelective(@Param("record") Institution record, @Param("example") InstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int updateByExample(@Param("record") Institution record, @Param("example") InstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int updateByPrimaryKeySelective(Institution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    int updateByPrimaryKey(Institution record);
}