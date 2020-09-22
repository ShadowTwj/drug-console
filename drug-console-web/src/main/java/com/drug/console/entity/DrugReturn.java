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
public class DrugReturn extends IdEntity {
  private Long drugId;
  private String drugName;
  private Integer number;
  /**
   * 出售时间
   */
  private Date sellTime;
  /**
   * 退货原因
   */
  private String cause;
  private String creator;
  private Date createTime;
  private String updateUser;
  private Date updateTime;
}
