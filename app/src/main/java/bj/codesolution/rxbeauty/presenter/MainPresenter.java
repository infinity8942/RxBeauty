package bj.codesolution.rxbeauty.presenter;

import android.content.Context;

import bj.codesolution.rxbeauty.ui.view.BaseView;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class MainPresenter extends BasePresenter<BaseView> {

    public MainPresenter(Context context, BaseView view) {
        super(context, view);
    }

    @Override
    public void release() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

}