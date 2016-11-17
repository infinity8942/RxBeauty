package bj.codesolution.rxbeauty.model;

import java.util.List;

import bj.codesolution.rxbeauty.model.entity.Beauty;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyData extends BaseData {
    private List<Beauty> newslist;

    public List<Beauty> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Beauty> newslist) {
        this.newslist = newslist;
    }
}
