package com.drug.console.mapper;

import com.drug.console.entity.SellHistory;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @date 2020/3/22
 */
public interface SellHistoryMapper extends ICrudMapper<SellHistory> {

  /**
   * 销售历史
   *
   * @return
   */
  //  @Select("SELECT * FROM sell_history WHERE create_time >= #{start} AND create_time <= #{end}")
  List<SellHistory> getSellHistoryList(@Param("start") Date start, @Param("end") Date end);
}
