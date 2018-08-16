package com.proflow.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 本地会话
 * Created by Leonid on 2018/7/3.
 */
@TableName("local_session")
public class LocalSession extends Model<LocalSession> {

    private Long id;

    private String token;

    private Long userId;

    private Date loginTime;

    private Date expiryTime;

    private String contextJson;

    private Integer status;

    public static final Integer INVALID = 0;
    public static final Integer VALID = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getContextJson() {
        return contextJson;
    }

    public void setContextJson(String contextJson) {
        this.contextJson = contextJson;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static LocalSession create(long timeout) {
        LocalSession session = new LocalSession();
        session.setToken(RandomUtil.randomUUID());
        session.setLoginTime(new Date());
        long curr = System.currentTimeMillis();
        session.setExpiryTime(DateUtil.date((curr + timeout)));
        session.setStatus(LocalSession.VALID);
        return session;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }


}
