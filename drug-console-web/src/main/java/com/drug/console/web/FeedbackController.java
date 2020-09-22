package com.drug.console.web;

import com.alibaba.fastjson.JSONObject;
import com.drug.console.bean.BaseResult;
import com.drug.console.entity.Feedback;
import com.drug.console.service.FeedbackService;
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
@RequestMapping(value = "/feedback/")
public class FeedbackController {
  @Resource
  private FeedbackService feedbackService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public List<Feedback> getFeedbackList(@RequestParam Integer type) {
    try {
      return feedbackService.getFeedbackList(type);
    } catch (Exception e) {
      log.error("getDrugList error", e);
      return Lists.newArrayList();
    }
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public BaseResult addFeedback(@RequestBody Feedback feedback) {
    BaseResult baseResult = new BaseResult();
    try {
      feedbackService.addFeedback(feedback);
      return baseResult.message("新增成功").success();
    } catch (IllegalArgumentException e) {
      log.warn("addFeedback error,param is invalid,feedback={}", feedback, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("addFeedback error, feedback={}", feedback, e);
      return baseResult.message("新增失败").error();
    }
  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public BaseResult editFeedback(@RequestBody Feedback feedback) {
    BaseResult baseResult = new BaseResult();
    try {
      int result = feedbackService.updateFeedback(feedback);
      if (result > 0) {
        return baseResult.message("更新成功").success();
      } else {
        log.warn("editFeedback error, feedback={}", feedback);
        return baseResult.message("反馈不存在").warning();
      }
    } catch (IllegalArgumentException e) {
      log.warn("editFeedback error,param is invalid,feedback={}", feedback, e);
      return baseResult.message("参数不合法").warning();
    } catch (Exception e) {
      log.error("editFeedback error, feedback={}", feedback, e);
      return baseResult.message("更新失败").error();
    }
  }

  @RequestMapping(value = "edit-status", method = RequestMethod.POST)
  public BaseResult editStatus(@RequestBody String data) {
    BaseResult baseResult = new BaseResult();
    try {
      JSONObject jsonObject = JSONObject.parseObject(data);
      Long feedbackId = jsonObject.getLong("feedbackId");
      Integer status = jsonObject.getInteger("status");
      feedbackService.updateStatus(feedbackId, status);
      return baseResult.message("更新成功").success();
    } catch (Exception e) {
      log.error("edit-status error, data={}", data, e);
      return baseResult.message("更新失败").error();
    }
  }
}
