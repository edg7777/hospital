package com.fzj.service;

import com.fzj.pojo.Hospital;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService {
    Hospital findHospitalById(@Param("id") Integer id);

    Hospital findHospitalByName(String name);




    // 通过条件查询开通预约医院

    public List<Hospital> findHosByConditon(String area);


    List<Hospital> findAllHos();

}
