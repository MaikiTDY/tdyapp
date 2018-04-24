package com.tdy.tdytravel.bean;

/*******************************
 * Created by tangdayi on 2018/4/23.
 * 作者:tangdayi
 * 日期:2018年04月23日22时09分
 * 文件:UserBean
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class UserBean {
    private String username;
    private String password;
    private String mobile;
    private String sex;
    private String date;
    private String status;
    private String imageUrl;

    public UserBean(String username, String password, String mobile, String sex, String date, String status) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.sex = sex;
        this.date = date;
        this.status = status;
    }

    public UserBean(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
