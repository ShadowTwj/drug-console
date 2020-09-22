package com.drug.console.service;

import com.drug.console.entity.User;
import com.drug.console.exception.LogInException;
import com.drug.console.mapper.UserMapper;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService {
  @Resource
  private UserMapper userMapper;

  public User login(@NonNull String account, @NonNull String password) throws LogInException {
    if (account == null || StringUtils.isBlank(account)) {
      throw new LogInException();
    }

    if (password == null || StringUtils.isBlank(password)) {
      throw new LogInException();
    }

    User user = userMapper.findByAccount(account.trim());

    if (user == null) {
      throw new LogInException();
    }

    if (password.equals(user.getPassword())) {
      return user;
    }

    throw new LogInException();
  }

  public List<User> getUserList(@NonNull String account) {
    if (StringUtils.equals(account, "admin")) {
      return userMapper.findAll();
    } else {
      return Lists.newArrayList(userMapper.findByAccount(account));
    }
  }

  public int addUser(@NonNull User user) {
    if (StringUtils.isBlank(user.getName())) {
      user.setName(user.getAccount());
    }
    user.setCreateTime(new Date());
    user.setUpdateTime(new Date());
    return userMapper.insert(user);
  }

  public User findAccount(@NonNull String account) {
    return userMapper.findByAccount(account);
  }

  public int remove(@NonNull String id) {
    //    userMapper.deleteUser(Integer.parseInt(id));
    return userMapper.deleteById(Long.parseLong(id));
  }

  public int edit(@NonNull User user) {
    //    return userMapper.updateUser(user);
    return userMapper.update(user);
  }

  public int batchRemove(@Nonnull List<String> idList) {
    return userMapper.batchDeleteUser(idList);
  }
}
