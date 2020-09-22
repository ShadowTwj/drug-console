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
 * 药品库存管理
 *
 * @date 2020/3/21
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugInventory extends IdEntity {
  private Long drugId;
  private String drugName;
  /**
   * 生产日期
   */
  private Date productionTime;
  /**
   * 过期时间
   */
  private Date deadline;
  /**
   * 入库价格
   */
  private BigDecimal price;
  /**
   * 0:入库
   * 1:出库
   */
  private Integer type;
  /**
   * 出入库数量
   */
  private Integer number;
  private String statement;
  private String creator;
  private Date createTime;
}
