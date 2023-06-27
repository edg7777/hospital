package com.fzj.service;

import com.fzj.pojo.Office;

import java.util.List;

public interface OfficeService {

    List<Office> findOfficeByHosName(String name);

    Office findOfficeById(Integer id);


    Office findOfficeByHosNameAndOfficeName(String hospitalName, String officeName);
}
