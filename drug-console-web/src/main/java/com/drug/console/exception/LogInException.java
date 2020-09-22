package com.drug.console.exception;

public class LogInException extends BaseException {

  public LogInException() {
    super(-1, "用户名或密码错误");
  }

  public LogInException(int code, String message) {
    super(code, message);
  }
}
