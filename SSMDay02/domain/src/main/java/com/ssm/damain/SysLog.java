package com.ssm.damain;

import java.util.Date;

public class SysLog {
    private Long id;
    private Date visitTime;
    private String username;
    private String ip;
    private String method;

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", visitTime=" + visitTime +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
