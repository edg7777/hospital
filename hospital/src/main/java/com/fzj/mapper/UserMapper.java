package com.fzj.mapper;

import com.fzj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 通过id来查找用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 通过身份证号来查找用户
     * @param Idenf
     * @return
     */
    User findUserbByIdef(String Idenf);

    /**
     * 通过姓名来查找用户，考虑到重名的情况返回一个集合
     * @param name
     * @return
     */
    List<User> findUserByName(String name);

    /**
     * 通过邮箱来查找用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 通过手机号来查找用户
     * @param phone
     * @return
     */
    User findUseByPhone(String phone);


    /**
     * 插入用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 通过邮箱来修改密码
     * @param email
     * @param new_password
     */
    void modifyPassByEmail(@Param("email") String email,@Param("new_password") String new_password);

    /**
     * 通过id来修改用户性别
     * @param id
     * @param sex
     */
    void modifySexById(@Param("id") Integer id,@Param("sex") String sex);

    /**
     *通过邮箱来更改手机号
     * @param phone
     * @param email
     */
    void modifyPhoneByEmail(@Param("phone") String phone,@Param("email") String email);

    /**
     * 通过身份证号来修改邮箱
     * @param email
     * @param idenf
     */
    void modifyEmailByIdef(@Param("email") String email,@Param("idenf") String idenf);


    int updateUserInfo(@Param("user") User user);


}