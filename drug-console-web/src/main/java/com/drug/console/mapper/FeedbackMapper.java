package com.drug.console.mapper;

import com.drug.console.entity.Feedback;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * @date 2020/3/21
 */
public interface FeedbackMapper extends ICrudMapper<Feedback> {

  /**
   * 查询投诉、反馈列表
   *
   * @param type
   * @return
   */
  @Select("SELECT * FROM feedback WHERE type = #{type}")
  List<Feedback> getFeedbackByType(@Param("type") Integer type);

  /**
   * 更新状态
   *
   * @param status
   * @param id
   * @return
   */
  @Update("UPDATE feedback SET status=#{status} WHERE id=#{id}")
  int updateStatus(@Param("status") Integer status, @Param("id") Long id);
}
