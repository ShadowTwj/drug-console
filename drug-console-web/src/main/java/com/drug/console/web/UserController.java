package com.drug.console.web;

import com.alibaba.fastjson.JSONObject;
import com.drug.console.bean.BaseResult;
import com.drug.console.entity.User;
import com.drug.console.service.UserService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/3/20
 */
@RestController
@Slf4j
@RequestMapping(value = "/user/")
public class UserController {
  @Resource
  private UserService userService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<User> getUserList(@RequestParam String account) {
    try {
      return userService.getUserList(account);
    } catch (Exception e) {
      log.error("getUserList error", e);
      return Lists.newArrayList();
    }
  }

  /**
   * 新增用户
   */
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addUser(@RequestBody User user) {
    BaseResult baseResult = new BaseResult();
    try {
      if (!user.getPassword().trim().equals(user.getCheckPass().trim())) {
        log.error("password checkPass is no equals, user={}", user);
        return baseResult.message("新增失败,密码不一致").error();
      }
      int result = userService.addUser(user);
      if (result > 0) {
        return baseResult.message("新增成功").success();
      } else {
        log.error("addUser error, user={}", user);
        return baseResult.message("新增失败").error();
      }
    } catch (Exception e) {
      log.error("addUser error, user={}", user, e);
      return baseResult.message("新增失败").error();
    }
  }

  /**
   * 判断账号是否存在
   */
  @RequestMapping(value = "findAccount", method = RequestMethod.GET)
  public BaseResult findAccount(@RequestParam String account) {
    BaseResult baseResult = new BaseResult();
    try {
      User user = userService.findAccount(account);
      if (user == null) {
        return baseResult.message("该账号可用").success();
      } else {
        return baseResult.message("该账号已存在").warning();
      }
    } catch (Exception e) {
      log.error("findAccount error, account={}", account, e);
      return baseResult.message("查询出错").error();
    }
  }

  @RequestMapping(value = "remove", method = RequestMethod.POST)
  public BaseResult remove(@RequestBody String data) {
    BaseResult baseResult = new BaseResult();
    try {
      String id = JSONObject.parseObject(data).getString("id");
      int result = userService.remove(id);
      if (result > 0) {
        return baseResult.message("删除成功").success();
      } else {
        log.error("deleteUser error, userId={}", id);
        return baseResult.message("删除失败").error();
      }
    } catch (Exception e) {
      log.error("deleteUser error, {}", data, e);
      return baseResult.message("删除失败").error();
    }
  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public BaseResult edit(@RequestBody User user) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = userService.edit(user);
      if (result > 0) {
        return baseResult.message("更新成功").success();
      } else {
        log.error("editUser error, user={}", user);
        return baseResult.message("更新失败").error();
      }
    } catch (Exception e) {
      log.error("editUser error, user={}", user, e);
      return baseResult.message("更新失败").error();
    }
  }

  @RequestMapping(value = "batchRemove", method = RequestMethod.POST)
  public BaseResult batchRemove(@RequestBody String data) {
    BaseResult baseResult = new BaseResult();
    try {
      String ids = JSONObject.parseObject(data).getString("ids");
      List<String> idList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(ids);
      int result = userService.batchRemove(idList);
      if (result > 0) {
        return baseResult.message("批量删除成功").success();
      } else {
        log.error("batchDeleteUser error, userIds={}", ids);
        return baseResult.message("批量删除失败").error();
      }
    } catch (Exception e) {
      log.error("batchDeleteUser error, data={}", data, e);
      return baseResult.message("批量删除失败").error();
    }
  }
}
