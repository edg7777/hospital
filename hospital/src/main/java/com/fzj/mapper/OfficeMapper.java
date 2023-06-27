package com.fzj.mapper;

import com.fzj.pojo.Office;

import java.util.List;

public interface OfficeMapper {
    List<Office> findOfficeByHosName(String name);

    Office findOfficeById(Integer id);

    Office findOfficeByHosNameAndOfficeName(String hospitalName, String officeName);
}
