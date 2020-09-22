package com.drug.console.web;

import com.drug.console.bean.BaseResult;
import com.drug.console.entity.DrugShow;
import com.drug.console.service.DrugShowService;
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
@RequestMapping(value = "/drug-show/")
public class DrugShowController {
  @Resource
  private DrugShowService drugShowService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<DrugShow> getDrugShow() {
    try {
      return drugShowService.getDrugShowList();
    } catch (Exception e) {
      log.error("getDrugShow error", e);
      return Lists.newArrayList();
    }
  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public BaseResult editDrugShow(@RequestBody DrugShow drugShow) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = drugShowService.updateDrugShow(drugShow);
      if (result > 0) {
        return baseResult.message("更新成功").success();
      } else {
        log.warn("editDrugShow error, drugShow={}", drugShow);
        return baseResult.message("药品不存在").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("editDrugShow error,param is invalid,drugShow={}", drugShow, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("editDrugShow error, drugShow={}", drugShow, e);
      return baseResult.message("更新失败").error();
    }
  }
}
