package com.senon.mvpretrofitrx.mvp.entity;

/**
 * 作者：senon on 2017/12/27 10:31
 * 邮箱：a1083911695@163.com
 */

public class Login {

    /**
     * time : 2017-12-18 17:17:58
     * ftime : 2017-12-18 17:17:58
     * context : 感恩广场生活区9区2号圆通快递妈妈驿站已发出自提短信,请上门自提,如有疑问请联系15340979298
     * location : null
     */

    private String time;
    private String ftime;
    private String context;
    private Object location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Login{" +
                "time='" + time + '\n' +
                ", ftime='" + ftime + '\n' +
                ", context='" + context + '\n' +
                ", location=" + location +
                '}';
    }
}
