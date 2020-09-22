package com.drug.console.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回前端实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
  public static final String SUCCESS = "success";
  public static final String WARNING = "warning";
  public static final String ERROR = "error";

  public String message = "";
  public String type;

  public BaseResult message(String message) {
    this.setMessage(message);
    return this;
  }

  public BaseResult success() {
    this.setType(SUCCESS);
    return this;
  }

  public BaseResult warning() {
    this.setType(WARNING);
    return this;
  }

  public BaseResult error() {
    this.setType(ERROR);
    return this;
  }
}
