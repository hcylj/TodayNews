package com.itheima.todaynews.bean;

/**
 * Created by Lou on 2018/6/21.
 */

public class NewBean {

    /**
     * id : 10007
     * title : 北京
     * type : 1
     * url : 10007/list_1.json
     */

    private int id;
    private String title;
    private int type;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
