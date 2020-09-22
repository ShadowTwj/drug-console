package com.drug.console.entity;

import com.github.mybatis.entity.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
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
public class Drug extends IdEntity {
  private String name;
  /**
   * 生产厂商
   */
  private String manufacturers;
  /**
   * 零售价
   */
  private BigDecimal retailPrice;
  /**
   * 经销商
   */
  private String dealers;
  private Integer status;
  private Integer number;
  private String creator;
  private Date createTime;
  private String updateUser;
  private Date updateTime;

  @Transient
  private Integer sellNumber;
  @Transient
  private BigDecimal totalPrice;
}
