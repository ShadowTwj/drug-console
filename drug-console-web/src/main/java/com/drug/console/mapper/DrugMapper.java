package com.drug.console.mapper;

import com.drug.console.entity.Drug;
import com.drug.console.entity.User;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * @date 2020/3/21
 */
public interface DrugMapper extends ICrudMapper<Drug> {

  /**
   * 入库
   *
   * @param drugId
   * @param number
   * @return
   */
  @Update("UPDATE drug SET number=number+#{number} WHERE id=#{drugId}")
  int addDrugNumber(@Param("drugId") Long drugId, @Param("number") Integer number);

  /**
   * 出库
   *
   * @param drugId
   * @param number
   * @return
   */
  @Update("UPDATE drug SET number=number-#{number} WHERE id=#{drugId} AND number>=#{number}")
  int removeDrugNumber(@Param("drugId") Long drugId, @Param("number") Integer number);

  /**
   * 修改零售价格
   *
   * @param drugId
   * @param retailPrice
   * @return
   */
  @Update("UPDATE drug SET retail_price=#{retailPrice} WHERE id=#{drugId}")
  int editRetailPrice(@Param("drugId") Long drugId, @Param("retailPrice") BigDecimal retailPrice);

  /**
   * 获取所有药品id和药品名称
   *
   * @return
   */
  @Select("SELECT id,name FROM drug")
  List<Drug> getDrugIdAndName();

}
