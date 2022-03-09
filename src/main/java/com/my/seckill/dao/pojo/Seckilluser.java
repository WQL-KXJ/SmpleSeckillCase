package com.my.seckill.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author WQL
 * @since 2021-12-18
 */
public class Seckilluser extends Model<Seckilluser>{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * MD5加密后的password
     */
    private String password;

    /**
     * slat为密码的安全拼接
     */
    private String passwordslat;

    /**
     * 用户头像
     */
    private String headportrait;

    /**
     * 用户注册时间
     */
    private LocalDateTime registerDate;

    /**
     * 用户最后一次登录时间
     */
    private LocalDateTime lastLoginDate;

    /**
     * 用户登录的次数
     */
    private Integer loginCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPasswordslat() {
        return passwordslat;
    }

    public void setPasswordslat(String passwordslat) {
        this.passwordslat = passwordslat;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Seckilluser{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", passwordslat=" + passwordslat +
        ", headportrait=" + headportrait +
        ", registerDate=" + registerDate +
        ", lastLoginDate=" + lastLoginDate +
        ", loginCount=" + loginCount +
        "}";
    }
}
