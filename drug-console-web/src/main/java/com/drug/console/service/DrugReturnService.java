package com.drug.console.service;

import com.drug.console.entity.DrugReturn;
import com.drug.console.mapper.DrugMapper;
import com.drug.console.mapper.DrugReturnMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DrugReturnService {
  @Resource
  private DrugReturnMapper drugReturnMapper;
  @Resource
  private DrugMapper drugMapper;

  public List<DrugReturn> getDrugReturnList() {
    return drugReturnMapper.findAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public int addDrugReturn(@NonNull DrugReturn drugReturn) {
    drugReturn.setCreateTime(new Date());
    drugReturn.setUpdateTime(new Date());
    drugMapper.addDrugNumber(drugReturn.getDrugId(), drugReturn.getNumber());
    drugReturn.setDrugName(drugMapper.findById(drugReturn.getDrugId()).getName());
    return drugReturnMapper.insert(drugReturn);
  }

}
