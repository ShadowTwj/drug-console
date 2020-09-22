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
public class Feedback extends IdEntity {
  private String title;
  private String info;
  /**
   * 0：投诉
   * 1：反馈
   */
  private Integer type;
  /**
   * 0：新建
   * 1：跟进中
   * 2：完成
   */
  private Integer status;
  private String statement;
  private String creator;
  private Date createTime;
  private String updateUser;
  private Date updateTime;
}
