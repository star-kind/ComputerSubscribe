package com.computer.subscribe.mapper;

import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.TUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int countByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int deleteByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int insert(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int insertSelective(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    List<TUser> selectByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    TUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int updateByPrimaryKeySelective(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Tue Mar 16 04:16:37 CST 2021
     */
    int updateByPrimaryKey(TUser record);
}