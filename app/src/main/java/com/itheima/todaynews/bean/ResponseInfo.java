package com.itheima.todaynews.bean;

/**
 * Created by Lou on 2018/6/21.
 */

public class ResponseInfo {
    private int retcode;
    private String data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ResponseInfo(int retcode, String data) {
        this.retcode = retcode;
        this.data = data;
    }
}
