package com.fzj.controller;

import com.fzj.pojo.Doctor;
import com.fzj.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    /**
     * 一个科室所有医生页面
     * @param model
     * @param office_name
     * @param hospital_name
     * @return
     */
    @GetMapping("/doctor/{hospital_name}/{office_name}")
    public String allDocOfAOffice(Model model,@PathVariable("office_name") String office_name, @PathVariable("hospital_name") String hospital_name) {
        List<Doctor> doctorList = doctorService.findDoctorByOfficeAndHosName(office_name, hospital_name);
        model.addAttribute("doctorList",doctorList);
        return "allDocOfAnOffice";
    }

    /**
     * 医生具体信息页面
     * @param model
     * @param hospital_name
     * @param office_name
     * @param doctor_name
     * @param session
     * @return
     */
    @GetMapping("/doctor/{hospital_name}/{office_name}/{doctor_name}")
    public String DetailDoc(Model model, @PathVariable("hospital_name") String hospital_name, @PathVariable("office_name") String office_name,
                            @PathVariable("doctor_name") String doctor_name, HttpSession session){
        Doctor doc = doctorService.findDoctorByName(hospital_name,office_name,doctor_name);
        String base64Image = Base64.getEncoder().encodeToString(doc.getPicture());
        model.addAttribute("doctor",doc);
        session.setAttribute("doctor",doc);
        model.addAttribute("base64Image",base64Image);
        return "DetailDocInfo";
    }

}
