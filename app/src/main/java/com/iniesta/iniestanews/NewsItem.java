package com.iniesta.iniestanews;

public class NewsItem {
    private String title;
    private String url;
    private String urlToImage;
    private String description;

    public NewsItem(String title, String url, String urlToImage,String description) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDescription(){
        return description;
    }
}
