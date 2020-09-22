package com.drug.console.service;

import com.drug.console.entity.DrugInventory;
import com.drug.console.mapper.DrugInventoryMapper;
import com.drug.console.mapper.DrugMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/3/21
 */
@Service
@Slf4j
public class DrugInventoryService {
  @Resource
  private DrugInventoryMapper drugInventoryMapper;
  @Resource
  private DrugMapper drugMapper;

  @Transactional(rollbackFor = Exception.class)
  public int addDrugInventory(@NonNull DrugInventory drugInventory) {
    if (!ObjectUtils.allNotNull(drugInventory.getDrugId(), drugInventory.getDrugName(), drugInventory.getProductionTime(), drugInventory.getDeadline(), drugInventory
      .getPrice(), drugInventory.getNumber())) {
      throw new IllegalArgumentException("参数错误");
    }
    if (drugInventory.getType() != 0) {
      throw new IllegalArgumentException("参数错误");
    }
    drugMapper.addDrugNumber(drugInventory.getDrugId(), drugInventory.getNumber());
    drugInventory.setCreateTime(new Date());
    return drugInventoryMapper.insert(drugInventory);
  }

  @Transactional(rollbackFor = Exception.class)
  public int removeDrugInventory(@NonNull DrugInventory drugInventory) {
    if (!ObjectUtils.allNotNull(drugInventory.getDrugId(), drugInventory.getDrugName(), drugInventory.getNumber())) {
      throw new IllegalArgumentException("参数错误");
    }
    if (drugInventory.getType() != 1) {
      throw new IllegalArgumentException("参数错误");
    }
    drugMapper.removeDrugNumber(drugInventory.getDrugId(), drugInventory.getNumber());
    drugInventory.setCreateTime(new Date());
    return drugInventoryMapper.insert(drugInventory);
  }

  public List<DrugInventory> getDrugInventoryList(@NonNull String drugId) {
    return drugInventoryMapper.getDrugInventoryList(Long.parseLong(drugId));
  }
}
