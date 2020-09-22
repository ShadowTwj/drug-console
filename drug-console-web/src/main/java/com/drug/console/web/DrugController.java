package com.drug.console.web;

import com.alibaba.fastjson.JSONObject;
import com.drug.console.bean.BaseResult;
import com.drug.console.entity.Drug;
import com.drug.console.service.DrugService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @date 2020/3/21
 */
@RestController
@Slf4j
@RequestMapping(value = "/drug/")
public class DrugController {
  @Resource
  private DrugService drugService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<Drug> getUserList() {
    try {
      return drugService.getDrugList();
    } catch (Exception e) {
      log.error("getDrugList error", e);
      return Lists.newArrayList();
    }
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addDrug(@RequestBody Drug drug) {
    BaseResult baseResult = new BaseResult();
    try {
      drugService.addDrug(drug);
      return baseResult.message("新增成功").success();
    } catch (IllegalArgumentException e) {
      log.warn("addDrug error,param is invalid,drug={}", drug, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("addDrug error, drug={}", drug, e);
      return baseResult.message("新增失败").error();
    }
  }

  @RequestMapping(value = "remove", method = RequestMethod.POST)
  public BaseResult deleteDrug(@RequestBody String data) {
    BaseResult baseResult = new BaseResult();
    try {
      String id = JSONObject.parseObject(data).getString("id");
      int result = drugService.deleteDrug(id);
      if (result > 0) {
        return baseResult.message("删除成功").success();
      } else {
        log.warn("deleteDrug error, userId={}", id);
        return baseResult.message("药品不存在").warning();
      }
    } catch (Exception e) {
      log.error("deleteDrug error, {}", data, e);
      return baseResult.message("删除失败").error();
    }
  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public BaseResult edit(@RequestBody Drug drug) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = drugService.updateDrug(drug);
      if (result > 0) {
        return baseResult.message("更新成功").success();
      } else {
        log.warn("editDrug error, drug={}", drug);
        return baseResult.message("药品不存在").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("editDrug error,param is invalid,drug={}", drug, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("editDrug error, drug={}", drug, e);
      return baseResult.message("更新失败").error();
    }
  }

  @RequestMapping(value = "edit-retailPrice", method = RequestMethod.POST)
  public BaseResult editRetailPrice(@RequestBody String data) {
    BaseResult baseResult = new BaseResult();
    try {
      JSONObject jsonObject = JSONObject.parseObject(data);
      Long drugId = jsonObject.getLong("drugId");
      BigDecimal retailPrice = jsonObject.getBigDecimal("retailPrice");
      drugService.editRetailPrice(drugId, retailPrice);
      return baseResult.message("更新成功").success();
    } catch (Exception e) {
      log.error("edit-retailPrice error, data={}", data, e);
      return baseResult.message("更新失败").error();
    }
  }

  @RequestMapping(value = "find-id-name", method = RequestMethod.GET)
  public List<Drug> getDrugIdAndName() {
    try {
      return drugService.getDrugIdAndName();
    } catch (Exception e) {
      log.error("getDrugIdAndName error", e);
      return Lists.newArrayList();
    }
  }
}
