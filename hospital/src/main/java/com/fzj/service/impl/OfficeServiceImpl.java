package com.fzj.service.impl;

import com.fzj.mapper.OfficeMapper;
import com.fzj.pojo.Office;
import com.fzj.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    OfficeMapper officeMapper;
    @Override
    public List<Office> findOfficeByHosName(String name) {
        return officeMapper.findOfficeByHosName(name);
    }

    @Override
    public Office findOfficeById(Integer id) {
        return officeMapper.findOfficeById(id);
    }

    @Override
    public Office findOfficeByHosNameAndOfficeName(String hospitalName, String officeName) {
        return officeMapper.findOfficeByHosNameAndOfficeName(hospitalName,officeName);
    }
}
