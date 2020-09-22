package com.drug.console.mapper;

import com.drug.console.entity.DrugInventory;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @date 2020/3/21
 */
public interface DrugInventoryMapper extends ICrudMapper<DrugInventory> {

  /**
   * 出入库历史
   *
   * @param drugId
   * @return
   */
  List<DrugInventory> getDrugInventoryList(@Param("drugId") Long drugId);
}
