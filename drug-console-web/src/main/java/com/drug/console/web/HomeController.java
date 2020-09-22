package com.drug.console.web;

import com.drug.console.bean.HttpResult;
import com.drug.console.entity.User;
import com.drug.console.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @date 2020/3/20
 */
@Controller
@Slf4j
public class HomeController {
  @Resource
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public HttpResult login(@RequestBody User user) {
    HttpResult httpResult = HttpResult.builder().build();
    try {
      User result = userService.login(user.getAccount(), user.getPassword());
      httpResult.code(HttpStatus.OK.value()).data(result).success().message("登录成功");
    } catch (Exception e) {
      log.error("login error,user:{}", user);
      httpResult.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error().message(e.getMessage());
    }

    return httpResult;
  }

  @RequestMapping(value = {"/"})
  public String home() {
    return "index";
  }

}
