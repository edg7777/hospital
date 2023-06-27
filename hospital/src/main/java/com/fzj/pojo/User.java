package com.fzj.pojo;

public class User {

    private Integer id;
    //身份证
    private String userIdef;
    private String name;
    private String sex;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    private String password;

    private String email;

    private String phone;
    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userIdef='" + userIdef + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", verificationcode=" + verificationcode +
                '}';
    }


    private Integer verificationcode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIdef() {
        return userIdef;
    }

    public void setUserIdef(String userIdef) {
        this.userIdef = userIdef == null ? null : userIdef.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(Integer verificationcode) {
        this.verificationcode = verificationcode;
    }

}