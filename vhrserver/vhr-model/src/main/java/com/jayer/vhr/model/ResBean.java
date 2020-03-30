package com.jayer.vhr.model;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-09-20 8:39
 */
public class ResBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static ResBean build() {
        return new ResBean();
    }

    public static ResBean ok(String msg) {
        return new ResBean(200, msg, null);
    }

    public static ResBean ok(String msg, Object obj) {
        return new ResBean(200, msg, obj);
    }

    public static ResBean error(String msg) {
        return new ResBean(500, msg, null);
    }

    public static ResBean error(String msg, Object obj) {
        return new ResBean(500, msg, obj);
    }

    private ResBean() {
    }

    private ResBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public ResBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public ResBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}

