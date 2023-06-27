package com.fzj.controller;

import com.fzj.pojo.Office;
import com.fzj.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OfficeController {
    @Autowired
    OfficeService officeService;

    /**
     * 查看医院中的所有科室
     * @param hospital_name
     * @param model
     * @return
     */
    @GetMapping("/office/{hospital_name}")
    public String allOffice(@PathVariable("hospital_name") String hospital_name, Model model){
        List<Office> office = officeService.findOfficeByHosName(hospital_name);
        model.addAttribute("office",office);
        return "hospital_office";
    }

    /**
     * 查看医院中科室的具体信息
     * @param office_name
     * @param model
     * @param hospital_name
     * @return
     */
    @GetMapping("/office/{hospital_name}/{office_name}")
    public String OfficeDetail(@PathVariable("office_name") String office_name,Model model,@PathVariable("hospital_name") String hospital_name){
        Office office = officeService.findOfficeByHosNameAndOfficeName(hospital_name, office_name);
        model.addAttribute("office",office);
        return "office_detail";
    }
}
