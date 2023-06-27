package com.fzj.controller;

import com.fzj.pojo.Doctor;
import com.fzj.pojo.OrderRecords;
import com.fzj.pojo.User;
import com.fzj.service.DoctorService;
import com.fzj.service.OrderRecordsService;
import com.fzj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class OrderRecordsController {
    @Autowired
    OrderRecordsService orderRecordsService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;
    private static Logger log = LoggerFactory.getLogger(OrderRecordsController.class);

    /**
     * 查看个人预约记录
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/orderRecords/{userId}")
    public String order(Model model, @PathVariable("userId") Integer userId){
        String error="";
        List<OrderRecords> orderRecordsList = orderRecordsService.findOrderByUserId(userId);
        if(orderRecordsList.size()==0){
            error="您暂时还没有预约医生";
            log.info(error);
            model.addAttribute("error",error);
        }
        else{
            model.addAttribute("orderRecordsList",orderRecordsList);
        }
        return "allOrder";
    }

    /**
     * 预约界面
     * @param hospitalName
     * @param officeName
     * @param doctorName
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/order/{hospitalName}/{officeName}/{doctorName}")
    public String orderDoctor(
            @PathVariable String hospitalName,
            @PathVariable String officeName,
            @PathVariable String doctorName,
            Model model,
            HttpServletRequest request) {
        Doctor doctor = doctorService.findDoctorByName(hospitalName, officeName, doctorName);
        HttpSession session = request.getSession();
        session.setAttribute("doctor",doctor);
        return "orders";
    }

    /**
     * 提交预约记录
     * @param userIdef
     * @param transacDate
     * @param transacTime
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/submitOrder")
    public String appointment(@RequestParam("userIdef")String userIdef,
                              @RequestParam("transacDate")String transacDate,
                              @RequestParam("transacTime")String transacTime,
                              Model model,
                              HttpServletRequest request){
        int flag=0;
        String error="";
        User user = userService.findUserByIdenf(userIdef);//通过输入身份证来查找用户id
        OrderRecords orderRecords=new OrderRecords();
        orderRecords.setUserID(user.getId());
        orderRecords.setTransactDate(transacDate);
        orderRecords.setTransactTime(transacTime);
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        if(doctor==null){
            error="医生对象为空";
            log.info(error);
            model.addAttribute("error",error);
        }
        orderRecords.setDoctorName(doctor.getName());
        orderRecords.setOfficesName(doctor.getOfficeName());
        orderRecords.setHospitalName(doctor.getHospitalName());
        List<OrderRecords> allOrder = orderRecordsService.findAllOrder();
        for(OrderRecords o:allOrder){
            if(o.getTransactTime().equals(transacTime)&&o.getTransactDate().equals(transacDate)){
                error="与自己预约时间冲突不能继续预约，请重新选择时间";
                model.addAttribute("error",error);
                flag=1;
                break;
            }
        }
        if(flag==0){//预约成功就将数据保存到数据库并且重定向到个人预约记录页面
            orderRecordsService.saveOrderRecord(orderRecords);
            return "redirect:/orderRecords/"+user.getId();
        }
        else{//预约失败就返回预约页面
            return "orders";
        }

    }

    /**
     * 删除预约记录
     * @param id
     * @return
     */
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        OrderRecords order = orderRecordsService.findOrderById(id);
        int userid = order.getUserID();
        orderRecordsService.deleteOrder(id);
        return "redirect:/orderRecords/"+userid;
    }

    /**
     * 查看自己一个订单的信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/order/{id}")
    public String updateOrder(@PathVariable("id")Integer id,Model model){
        OrderRecords order = orderRecordsService.findOrderById(id);
        model.addAttribute("order",order);
        return "anOrder";
    }

    /**
     * 修改订单信息
     * @param id
     * @param transactDate
     * @param transactTime
     * @param model
     * @return
     */
    @PostMapping("/updateOrder/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestParam("transactDate")String transactDate,
                         @RequestParam("transactTime")String transactTime,
                         Model model){
        int flag=0;
        String error="";
        OrderRecords order = orderRecordsService.findOrderById(id);
        int userID = order.getUserID();
        order.setTransactDate(transactDate);
        order.setTransactTime(transactTime);
        List<OrderRecords> allOrder = orderRecordsService.findAllOrder();
        for(OrderRecords o:allOrder){
            if(o.getTransactTime().equals(transactTime)&&o.getTransactDate().equals(transactDate)){
                error="与自己预约时间冲突不能继续预约，请重新选择时间";
                model.addAttribute("error",error);
                flag=1;
                break;
            }
        }
        if(flag==0){
            orderRecordsService.updateOrderRecords(order);
            return "redirect:/orderRecords/"+userID;
        }
        else{
            return "error";
        }
    }
}
