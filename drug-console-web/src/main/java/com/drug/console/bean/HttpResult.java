package com.drug.console.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Http返回结果封装
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult extends BaseResult {
  private Object data;
  private int code;

  public HttpResult data(Object data) {
    this.setData(data);
    return this;
  }

  public HttpResult code(int code) {
    this.setCode(code);
    return this;
  }
}
