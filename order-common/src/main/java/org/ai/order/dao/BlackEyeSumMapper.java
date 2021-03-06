package org.ai.order.dao;

import java.util.List;

import org.ai.order.model.BlackEyeSum;
import org.ai.order.model.BlackEyeSumExample;

public interface BlackEyeSumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int countByExample(BlackEyeSumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int deleteByExample(BlackEyeSumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int insert(BlackEyeSum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int insertSelective(BlackEyeSum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    List<BlackEyeSum> selectByExample(BlackEyeSumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    BlackEyeSum selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int updateByExampleSelective(BlackEyeSum record, BlackEyeSumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int updateByExample(BlackEyeSum record, BlackEyeSumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int updateByPrimaryKeySelective(BlackEyeSum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_eye_sum
     *
     * @mbggenerated Tue Apr 19 22:13:12 CST 2016
     */
    int updateByPrimaryKey(BlackEyeSum record);
}