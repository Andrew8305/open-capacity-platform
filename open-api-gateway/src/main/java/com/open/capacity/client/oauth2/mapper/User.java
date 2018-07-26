package com.open.capacity.client.oauth2.mapper;

/**
 * Created by Meng Ruo on 2018/7/19  15:50.
 **/
public class User {
    private String user;
    private String token;
    private Long times;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}
