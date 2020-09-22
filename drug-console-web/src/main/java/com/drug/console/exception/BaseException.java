package com.drug.console.exception;

public class BaseException extends Exception {

  /**
   * 错误码
   */
  public int code = -1;
  /**
   * 错误说明
   */
  public String message;

  public int getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  public BaseException(int code) {
    this.code = code;
    this.message = String.format("error code: [ %d ]", code);
  }

  public BaseException(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
