package com.fzj.mapper;

import com.fzj.pojo.Hospital;

import java.util.List;

public interface HospitalMapper {
    /**
     * 通过id查找医院
     * @param id
     * @return
     */
    Hospital findHospitalById(Integer id);

    /**
     * 通过名字查找医院
     * @param name
     * @return
     */
    Hospital findHospitalByName(String name);


    List<Hospital> findHosByArea(String area);


    List<Hospital> findAllHos();
}
