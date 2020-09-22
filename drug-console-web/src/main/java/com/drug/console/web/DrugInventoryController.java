package com.drug.console.web;

import com.drug.console.bean.BaseResult;
import com.drug.console.entity.DrugInventory;
import com.drug.console.service.DrugInventoryService;
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
 * @date 2020/3/21
 */
@RestController
@Slf4j
@RequestMapping(value = "/drug-inventory/")
public class DrugInventoryController {

  @Resource
  private DrugInventoryService drugInventoryService;

  /**
   * 入库
   */
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addDrugInventory(@RequestBody DrugInventory drugInventory) {
    BaseResult baseResult = new BaseResult();
    try {
      drugInventoryService.addDrugInventory(drugInventory);
      return baseResult.message("入库成功").success();
    } catch (IllegalArgumentException e) {
      log.warn("addDrugInventory error,param is invalid,drugInventory={}", drugInventory, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("addDrugInventory error, drugInventory={}", drugInventory, e);
      return baseResult.message("入库失败").error();
    }
  }

  /**
   * 出库
   */
  @RequestMapping(value = "remove", method = RequestMethod.POST)
  public BaseResult removeDrugInventory(@RequestBody DrugInventory drugInventory) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = drugInventoryService.removeDrugInventory(drugInventory);
      if (result > 0) {
        return baseResult.message("出库成功").success();
      } else {
        return baseResult.message("库存不足").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("removeDrugInventory error,param is invalid,drugInventory={}", drugInventory, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("removeDrugInventory error, drugInventory={}", drugInventory, e);
      return baseResult.message("出库失败").error();
    }
  }

  /**
   * 出入库历史
   */
  @RequestMapping(value = "history", method = RequestMethod.GET)
  public List<DrugInventory> drugInventoryHistory(@RequestParam String drugId) {
    try {
      return drugInventoryService.getDrugInventoryList(drugId);
    } catch (Exception e) {
      log.error("drugInventoryHistory error, drugId={}", drugId, e);
      return Lists.newArrayList();
    }
  }
}
