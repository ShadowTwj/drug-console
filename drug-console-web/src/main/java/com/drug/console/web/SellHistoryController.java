package com.drug.console.web;

import com.drug.console.bean.BaseResult;
import com.drug.console.entity.Drug;
import com.drug.console.entity.SellHistory;
import com.drug.console.service.SellHistoryService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/3/22
 */
@RestController
@Slf4j
@RequestMapping(value = "/sell-history/")
public class SellHistoryController {
  @Resource
  private SellHistoryService sellHistoryService;

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addSellHistory(@RequestBody SellHistory sellHistory) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = sellHistoryService.add(sellHistory);
      if (result > 0) {
        return baseResult.message("出售成功").success();
      } else {
        return baseResult.message("库存不足").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("addSellHistory error,param is invalid,sellHistory={}", sellHistory, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("addSellHistory error, sellHistory={}", sellHistory, e);
      return baseResult.message("出售失败").error();
    }
  }

  @RequestMapping(value = "add-batch", method = RequestMethod.POST)
  public BaseResult batchAddSellHistory(@RequestBody List<Drug> drugList) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = sellHistoryService.batchAdd(drugList);
      if (result == drugList.size()) {
        return baseResult.message("出售成功").success();
      } else {
        return baseResult.message("库存不足").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("batchAddSellHistory error,param is invalid,sellHistory={}", drugList, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("batchAddSellHistory error, sellHistory={}", drugList, e);
      return baseResult.message("出售失败").error();
    }
  }

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<SellHistory> findAll() {
    try {
      return sellHistoryService.findAll();
    } catch (Exception e) {
      log.error("findAll error:", e);
      return Lists.newArrayList();
    }
  }

  @RequestMapping(value = "list-by-time", method = RequestMethod.POST)
  public List<SellHistory> findByTime(@RequestBody String data) {
    try {
      List<String> list = Splitter.on("至").splitToList(data);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date start = simpleDateFormat.parse(list.get(0));
      Date end = simpleDateFormat.parse(list.get(1));
      return sellHistoryService.findByTime(start, end);
    } catch (Exception e) {
      log.error("findAll error:", e);
      return Lists.newArrayList();
    }
  }
}
