package model;

import calculus.Classify;

public class Episode implements Classify {
    private int episodeId;
    private String title;
    private TvShow tvShow;
    private int viewCount;

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void play(){
        this.viewCount++;
    }

    @Override
    public int getFinalRate() {
        if(this.viewCount >= 500){
            return 5;
        }else if(this.viewCount >= 250){
            return 4;
        } else if (this.viewCount >= 150) {
            return 3;
        }else {
            return 2;
        }
    }
}
