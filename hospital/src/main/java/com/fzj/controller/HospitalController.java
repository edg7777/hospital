package com.fzj.controller;


import com.fzj.pojo.Hospital;

import com.fzj.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;


@Controller
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    /**
     * 医院详情
     */
    @GetMapping("/hosInfoShow/{id}")
    public String hosInfoShow(Model model, @PathVariable(value = "id") int id, HttpSession session){
        Hospital hos = hospitalService.findHospitalById(id);
        String base64Image = Base64.getEncoder().encodeToString(hos.getHospitalImg());
        model.addAttribute("hospital",hos);
        model.addAttribute("base64Image", base64Image);
        return "DetailHosInfo";
    }

    /**
     * 根据医院地区来查找医院
     * @param area
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String search(@RequestParam(name = "area", required = false) String area, Model model) {
        String error="";
        List<Hospital> hospitalList;
        if (area == null || area.equals("全部")) {
            hospitalList = hospitalService.findAllHos();
        } else {
            hospitalList = hospitalService.findHosByConditon(area);
        }
        model.addAttribute("hospitalList", hospitalList);
        return "allHospital";
    }


    /**
     * 医院主界面(全部医院)
     */
    @GetMapping ("/allHospital")
    public String allHospital(Model model){
        List<Hospital> hospitalList = hospitalService.findAllHos();
        model.addAttribute("hospitalList",hospitalList);
        return "allHospital";
    }
}
