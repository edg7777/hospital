package com.fzj.mapper;

import com.fzj.pojo.Doctor;
import java.util.List;

public interface DoctorMapper {

    List<Doctor> findDoctorByOfficeAndHosName(String officeName,String hospitalName);

    Doctor findDoctorByName(String hospitalName,String officeName,String doctorName);



}