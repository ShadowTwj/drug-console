package com.drug.console.web;

import com.drug.console.bean.BaseResult;
import com.drug.console.entity.DrugReturn;
import com.drug.console.service.DrugReturnService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/3/21
 */
@RestController
@Slf4j
@RequestMapping(value = "/drug-return/")
public class DrugReutrnController {
  @Resource
  private DrugReturnService drugReturnService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<DrugReturn> getDrugReturnList() {
    try {
      return drugReturnService.getDrugReturnList();
    } catch (Exception e) {
      log.error("getDrugReturnList error", e);
      return Lists.newArrayList();
    }
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addDrugReturn(@RequestBody DrugReturn drugReturn) {
    BaseResult baseResult = new BaseResult();
    try {
      drugReturnService.addDrugReturn(drugReturn);
      return baseResult.message("新增成功").success();
    } catch (IllegalArgumentException e) {
      log.warn("addDrugReturn error,param is invalid,drugReturn={}", drugReturn, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("addDrugReturn error, drugReturn={}", drugReturn, e);
      return baseResult.message("新增失败").error();
    }
  }
}
