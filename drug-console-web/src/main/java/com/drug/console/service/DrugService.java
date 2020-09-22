package com.drug.console.service;

import com.drug.console.entity.Drug;
import com.drug.console.entity.DrugShow;
import com.drug.console.entity.User;
import com.drug.console.exception.BaseException;
import com.drug.console.exception.LogInException;
import com.drug.console.mapper.DrugMapper;
import com.drug.console.mapper.DrugShowMapper;
import com.drug.console.mapper.UserMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DrugService {
  @Resource
  private DrugMapper drugMapper;
  @Resource
  private DrugShowMapper drugShowMapper;

  public List<Drug> getDrugList() {
    return drugMapper.findAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public int addDrug(@NonNull Drug drug) throws IllegalArgumentException {
    checkParam(drug);
    drug.setCreateTime(new Date());
    drug.setUpdateTime(new Date());
    //添加药品时需要同步往drugShow中插入对应的数据
    int drugId = drugMapper.insertAndGetId(drug);
    DrugShow drugShow = DrugShow.builder().drugId(drugId).drugName(drug.getName()).createTime(new Date()).creator(drug.getCreator()).build();
    return drugShowMapper.insert(drugShow);
  }

  public int updateDrug(@NonNull Drug drug) throws IllegalArgumentException {
    checkParam(drug);
    drug.setUpdateTime(new Date());
    return drugMapper.update(drug);
  }

  public int editRetailPrice(@NonNull Long drugId, @NonNull BigDecimal retailPrice) {
    return drugMapper.editRetailPrice(drugId, retailPrice);
  }

  @Transactional(rollbackFor = Exception.class)
  public int deleteDrug(@NonNull String id) {
    //删除药品信息时需要同步把drugShow删除
    drugShowMapper.delDrugShowByDrugId(Long.parseLong(id));
    return drugMapper.deleteById(Long.parseLong(id));
  }

  public List<Drug> getDrugIdAndName() {
    return drugMapper.getDrugIdAndName();
  }

  private void checkParam(Drug drug) throws IllegalArgumentException {
    if (StringUtils.isAnyBlank(drug.getName(), drug.getManufacturers(), drug.getDealers())) {
      throw new IllegalArgumentException("参数错误");
    }
    if (drug.getNumber() == null) {
      drug.setNumber(0);
    }
    if (drug.getRetailPrice() == null) {
      drug.setRetailPrice(new BigDecimal(0));
    }
  }
}
