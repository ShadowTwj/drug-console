package com.drug.console.service;

import com.drug.console.entity.Drug;
import com.drug.console.entity.SellHistory;
import com.drug.console.mapper.DrugMapper;
import com.drug.console.mapper.SellHistoryMapper;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/3/22
 */
@Service
@Slf4j
public class SellHistoryService {
  @Resource
  private SellHistoryMapper sellHistoryMapper;
  @Resource
  private DrugMapper drugMapper;

  @Transactional(rollbackFor = Exception.class)
  public int add(@NonNull SellHistory sellHistory) {
    if (!ObjectUtils.allNotNull(sellHistory.getDrugId(), sellHistory.getDrugName(), sellHistory.getRetailPrice(), sellHistory.getNumber())) {
      throw new IllegalArgumentException("参数错误");
    }
    sellHistory.setTotalPrice(sellHistory.getRetailPrice().multiply(new BigDecimal(sellHistory.getNumber())));
    sellHistory.setCreateTime(new Date());
    drugMapper.removeDrugNumber(sellHistory.getDrugId(), sellHistory.getNumber());
    return sellHistoryMapper.insert(sellHistory);
  }

  public int batchAdd(@NonNull List<Drug> drugList) {
    for (Drug drug : drugList) {
      this.add(SellHistory
                 .builder()
                 .drugId(drug.getId())
                 .drugName(drug.getName())
                 .retailPrice(drug.getRetailPrice())
                 .number(drug.getSellNumber())
                 .totalPrice(drug.getTotalPrice())
                 .creator(drug.getCreator())
                 .createTime(new Date())
                 .build());
    }
    return drugList.size();
  }

  public List<SellHistory> findAll() {
    return sellHistoryMapper.findAll();
  }

  public List<SellHistory> findByTime(@NonNull Date start, @NonNull Date end) {
    return sellHistoryMapper.getSellHistoryList(start, end);
  }
}
