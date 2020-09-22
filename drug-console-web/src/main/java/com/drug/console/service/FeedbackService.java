package com.drug.console.service;

import com.drug.console.entity.Feedback;
import com.drug.console.mapper.FeedbackMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FeedbackService {
  @Resource
  private FeedbackMapper feedbackMapper;

  public List<Feedback> getFeedbackList(@NonNull Integer type) {
    return feedbackMapper.getFeedbackByType(type);
  }

  public int addFeedback(@NonNull Feedback feedback) {
    feedback.setCreateTime(new Date());
    feedback.setUpdateTime(new Date());
    return feedbackMapper.insert(feedback);
  }

  public int updateFeedback(@NonNull Feedback feedback) {
    feedback.setUpdateTime(new Date());
    return feedbackMapper.update(feedback);
  }

  public int updateStatus(@NonNull Long feedbackId, Integer status) {
    return feedbackMapper.updateStatus(status, feedbackId);
  }
}
