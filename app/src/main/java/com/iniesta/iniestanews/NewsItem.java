package com.iniesta.iniestanews;

public class NewsItem {
    private String title;
    private String url;
    private String urlToImage;

    public NewsItem(String title, String url, String urlToImage) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
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
}
