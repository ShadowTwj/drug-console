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
 * @date 2020/3/22
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellHistory extends IdEntity {
  private Long drugId;
  private String drugName;
  /**
   * 销售价格
   */
  private BigDecimal retailPrice;
  /**
   * 销售数量
   */
  private Integer number;
  /**
   * 销售总价
   */
  private BigDecimal totalPrice;
  private String statement;
  private String creator;
  private Date createTime;
}
