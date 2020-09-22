package com.drug.console.entity;

import com.github.mybatis.entity.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @date 2020/3/20
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends IdEntity {
  private String account;
  private String password;
  @Transient
  private String checkPass;
  private String name;
  /**
   * 职位
   */
  private String post;
  private String creator;
  private Date createTime;
  private Date updateTime;
}
