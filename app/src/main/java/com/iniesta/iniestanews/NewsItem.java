package com.iniesta.iniestanews;

public class NewsItem {
    private String cid;
    private String nid;
    private String imageUrl;
    private String heading;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String date;
    private String shareUrl;

    public NewsItem(String cid, String nid, String imageUrl, String heading, String content1,
                    String content2,String content3,String content4, String date, String shareUrl) {
        this.cid = cid;
        this.nid = nid;
        this.imageUrl = imageUrl;
        this.heading = heading;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        this.date = date;
        this.shareUrl = shareUrl;
    }


    public String getCid() {
        return cid;
    }

    public String getNid() {
        return nid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHeading() {
        return heading;
    }

    public String getContent1() {
        return content1;
    }

    public String getContent2() {
        return content2;
    }

    public String getContent3() {
        return content3;
    }

    public String getContent4() {
        return content4;
    }

    public String getDate() {
        return date;
    }

    public String getShareUrl() {
        return shareUrl;
    }
}
