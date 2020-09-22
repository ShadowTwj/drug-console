package com.drug.console.mapper;

import com.drug.console.entity.DrugShow;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2020/3/21
 */
public interface DrugShowMapper extends ICrudMapper<DrugShow> {

  /**
   * 根据药品id删除对应的drugShow数据
   *
   * @param drugId
   * @return
   */
  @Delete("DELETE FROM drug_show WHERE drug_id=#{drugId}")
  int delDrugShowByDrugId(@Param("drugId") Long drugId);
}
