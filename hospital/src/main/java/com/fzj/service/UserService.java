package com.fzj.service;

import com.fzj.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 根据邮箱来实现用户登录
     * @param email
     * @param password
     * @param httpServletRequest
     * @return
     */
    int login(String email, String password, HttpServletRequest httpServletRequest);

    /**
     * 用户的注册
     * @param user
     * @param httpServletRequest
     * @return
     */
    int sign(User user,HttpServletRequest httpServletRequest);

    /**
     * 通过邮箱来找回密码
     * @param email
     * @return
     */
    int findPassword(String email);

    /**
     * 发送邮件并返回发送结果
     *
     * @param user
     * @return
     */
     boolean sendEmailCheck(User user);

    /**
     * 根据身份证来查找用户
     * @param Idenf
     * @return
     */
     User findUserByIdenf(String Idenf);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 查询验证码发送时间
     * @param updateTime
     * @return
     */
    int findHeadWay(String updateTime);





    /**
     * 修改新密码
     * @param email
     * @param new_password
     * @return
     */
    void modifyPassword(String email,String new_password);

    /**
     * 修改性别
     * @param sex
     * @param id
     * @return
     */
    void modifySex(String sex,Integer id);

    /**
     * 通过邮箱修改手机号
     * @param phone
     * @param email
     * @return
     */
    int modifyPhone(String phone,String email);


    User findUserById(Integer id);

    int updateUserInfo(User user);
}
