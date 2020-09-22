package com.drug.console.mapper;

import com.drug.console.entity.User;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @date 2020/3/20
 */
public interface UserMapper extends ICrudMapper<User> {

  /**
   * 根据account获取user
   *
   * @param account
   * @return
   */
  @Select("SELECT * FROM `user` WHERE account = #{account}")
  User findByAccount(@Param("account") String account);

  /**
   * 批量删除
   *
   * @param idList
   * @return
   */
  int batchDeleteUser(@Param("idList") List<String> idList);
}
