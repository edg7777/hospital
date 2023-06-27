package com.fzj.service.impl;
import com.fzj.mapper.UserMapper;
import com.fzj.pojo.User;
import com.fzj.service.UserService;
import com.fzj.utils.DateUtil;
import com.fzj.utils.MD5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DateUtil dateUtil;
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户登录
     * @param email
     * @param password
     * @param httpServletRequest
     * @return 0表示用户不存在，1表示密码错误，2表示密码正确
     */
    @Override
    public int login(String email, String password, HttpServletRequest httpServletRequest) {
        User user = userMapper.findUserByEmail(email);
        if(user==null){
            return 0;
        }
        else{
            if(MD5.getMD5(password).equals(user.getPassword())){
                user.setLastLogTime(dateUtil.getCurrentTime(DateUtil.DateFormat.YYYY_MM_DD_HH_mm_ss));
                return 2;
            }
            else{
                return 1;
            }
        }
    }

    /**
     * 用户的注册
     * @param user
     * @param httpServletRequest
     * @return 0表示身份证号被占用，1表示邮箱被占用，2表示手机号被占用,3表示注册成功
     */
    @Override
    public int sign(User user, HttpServletRequest httpServletRequest) {
        String userIdef = user.getUserIdef();
        if(userMapper.findUserbByIdef(userIdef)!=null){
            return 0;
        }
        String email = user.getEmail();
        if(userMapper.findUserByEmail(email)!=null){
            return 1;
        }
        String phone = user.getPhone();
        if(userMapper.findUseByPhone(phone)!=null){
            return 2;
        }
        //将密码转换成MD5格式
        String password = user.getPassword();
        user.setPassword(MD5.getMD5(password));
        user.setEmail(user.getEmail().trim());
        userMapper.insertUser(user);
        return 3;
    }

    /**
     * 找回密码
     * @param email
     * @return
     */
    @Override
    public int findPassword(String email) {
        User userByEmail = userMapper.findUserByEmail(email);
        if(userByEmail!=null){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * 发送邮箱验证码
     * @param user
     * @return
     */
    @Override
    public boolean sendEmailCheck(User user) {
        return true;
    }

    @Override
    public User findUserByIdenf(String Idenf) {
        return userMapper.findUserbByIdef(Idenf);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public int findHeadWay(String updateTime) {
        return dateUtil.timeSubtractionSecond(updateTime, dateUtil.getCurrentTime(DateUtil.DateFormat.YYYY_MM_DD_HH_mm_ss));
    }



    @Override
    public void modifyPassword(String email, String new_password) {
        //将密码转换为MD5格式
        new_password = MD5.getMD5(new_password);
        userMapper.modifyPassByEmail(email,new_password);
    }

    @Override
    public void modifySex(String sex, Integer id) {
        userMapper.modifySexById(id,sex);
    }

    /**
     *
     * @param phone
     * @param email
     * @return手机号被占用了返回2，正常返回1
     */
    @Override
    public int modifyPhone(String phone, String email) {
        if(userMapper.findUseByPhone(phone)!=null){
            return 2;
        }
        userMapper.modifyPhoneByEmail(phone,email);
        return 1;
    }



    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }
}
