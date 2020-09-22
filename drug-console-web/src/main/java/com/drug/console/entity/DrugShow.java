package com.drug.console.entity;

import com.github.mybatis.entity.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @date 2020/3/21
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugShow extends IdEntity {
  private Integer drugId;
  private String drugName;
  /**
   * 柜台编号
   */
  private String showId;
  /**
   * 柜台名称
   */
  private String showName;
  /**
   * 药品在柜台中的位置
   */
  private String drugPlace;
  private String creator;
  private Date createTime;
  private String updateUser;
  private Date updateTime;
}
