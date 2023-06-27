package com.fzj.service.impl;

import com.fzj.mapper.HospitalMapper;
import com.fzj.pojo.Hospital;
import com.fzj.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;
    @Override
    public Hospital findHospitalById(Integer id) {
        return hospitalMapper.findHospitalById(id);
    }

    @Override
    public Hospital findHospitalByName(String name) {
        return hospitalMapper.findHospitalByName(name);
    }


    @Override
    public List<Hospital> findHosByConditon(String area) {
        return hospitalMapper.findHosByArea(area);
    }


    @Override
    public List<Hospital> findAllHos() {
        return hospitalMapper.findAllHos();
    }

}
