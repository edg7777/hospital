package com.fzj.service.impl;

import com.fzj.mapper.DoctorMapper;
import com.fzj.pojo.Doctor;
import com.fzj.service.DoctorService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public List<Doctor> findDoctorByOfficeAndHosName(String officeName, String hospitalName) {
        return doctorMapper.findDoctorByOfficeAndHosName(officeName, hospitalName);
    }

    @Override
    public Doctor findDoctorByName(String hospitalName,String officeName,String doctorName) {
        return doctorMapper.findDoctorByName(hospitalName,officeName,doctorName);
    }
}
