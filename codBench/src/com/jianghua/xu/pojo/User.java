package com.jianghua.xu.pojo;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/1/18 11:23
 */
public class User {

    private String userName;
    private int userAge;
    private String userSex;
    private String userAddr;
    private String userPhone;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public String getUserPhone() {
        return userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
