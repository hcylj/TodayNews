package com.itheima.todaynews.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

    /**
     * Created by Lou on 2018/6/22.
     */

    public class NewsTabBean {

        /**
         * countcommenturl : http://jrtt.qianlong.com/client/content/countComment/
         * more : 10007/list_2.json
         * news : [{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/jrtt/10007/1.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/jrtt/10007/2.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/jrtt/10007/3.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/4.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/5.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/jrtt/10007/6.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/jrtt/10007/7.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/8.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/9.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/jrtt/10007/10.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/jrtt/10007/11.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/12.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/13.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/jrtt/10007/14.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/jrtt/10007/15.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/jrtt/10007/16.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/17.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/18.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"}]
         * title : 北京
         * topic : [{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/jrtt/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/jrtt/10007/list_1.json"}]
         * topnews : [{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"}]
         */

        private String countcommenturl;
        private String more;
        private String title;
        private List<NewsBean> news;
        private List<TopicBean> topic;
        private List<TopnewsBean> topnews;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        @DatabaseTable(tableName = "t_news")
        public static class NewsBean implements Serializable{
            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/jrtt/10007/comment_1.json
             * commenturl : http://jrtt.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : http://10.0.2.2:8080/jrtt/10007/1.jpg
             * pubdate : 2014-10-1113:18
             * title : 网上大讲堂第368期预告：义务环保人人有责
             * type : 0
             * url : http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html
             * listimage1 : http://10.0.2.2:8080/jrtt/10007/4.jpg
             * listimage2 : http://10.0.2.2:8080/jrtt/10007/5.jpg
             */
            @DatabaseField()
            private boolean comment;
            @DatabaseField()
            private String commentlist;
            @DatabaseField()
            private String commenturl;
            @DatabaseField(id=true)
            private int id;
            @DatabaseField()
            private String listimage;
            @DatabaseField()
            private String pubdate;
            @DatabaseField()
            private String title;
            @DatabaseField()
            private int type;
            @DatabaseField()
            private String url;
            @DatabaseField()
            private String listimage1;
            @DatabaseField()
            private String listimage2;
            @DatabaseField()
            private boolean isRead;

            public boolean isRead(){
                return isRead;
            }

            public void setRead(boolean read){
                isRead = read;
            }

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
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

            public String getListimage1() {
                return listimage1;
            }

            public void setListimage1(String listimage1) {
                this.listimage1 = listimage1;
            }

            public String getListimage2() {
                return listimage2;
            }

            public void setListimage2(String listimage2) {
                this.listimage2 = listimage2;
            }
        }

    public static class TopicBean {
        /**
         * description : 11111111
         * id : 10101
         * listimage : http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg
         * sort : 1
         * title : 北京
         * url : http://10.0.2.2:8080/jrtt/10007/list_1.json
         */

        private String description;
        private int id;
        private String listimage;
        private int sort;
        private String title;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getListimage() {
            return listimage;
        }

        public void setListimage(String listimage) {
            this.listimage = listimage;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TopnewsBean {
        /**
         * comment : true
         * commentlist : http://10.0.2.2:8080/jrtt/10007/comment_1.json
         * commenturl : http://jrtt.qianlong.com/client/user/newComment/35301
         * id : 35301
         * pubdate : 2014-04-0814:24
         * title : 蜗居生活
         * topimage : http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg
         * type : news
         * url : http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html
         */

        private boolean comment;
        private String commentlist;
        private String commenturl;
        private int id;
        private String pubdate;
        private String title;
        private String topimage;
        private String type;
        private String url;

        public boolean isComment() {
            return comment;
        }

        public void setComment(boolean comment) {
            this.comment = comment;
        }

        public String getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(String commentlist) {
            this.commentlist = commentlist;
        }

        public String getCommenturl() {
            return commenturl;
        }

        public void setCommenturl(String commenturl) {
            this.commenturl = commenturl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTopimage() {
            return topimage;
        }

        public void setTopimage(String topimage) {
            this.topimage = topimage;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
