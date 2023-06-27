package com.fzj.service;

import com.fzj.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findDoctorByOfficeAndHosName(String officeName, String hospitalName);

    Doctor findDoctorByName(String hospitalName,String officeName,String doctorName);
}
