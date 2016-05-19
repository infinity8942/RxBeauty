package bj.codesolution.rxbeauty.ui.view;

import java.util.List;

import bj.codesolution.rxbeauty.model.entity.Beauty;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public interface BeautyView extends BaseView {

    void showProgressBar();

    void hideProgressBar();

    void showErrorView();

    void showNoMoreData();

    void loadData(List<Beauty> beautyList);
}
