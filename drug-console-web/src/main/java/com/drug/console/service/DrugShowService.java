package com.drug.console.service;

import com.drug.console.entity.DrugShow;
import com.drug.console.mapper.DrugShowMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DrugShowService {
  @Resource
  private DrugShowMapper drugShowMapper;

  public List<DrugShow> getDrugShowList() {
    return drugShowMapper.findAll();
  }

  public int updateDrugShow(@NonNull DrugShow drugShow) {
    drugShow.setUpdateTime(new Date());
    return drugShowMapper.update(drugShow);
  }
}
