package bj.codesolution.rxbeauty.presenter;

import android.content.Context;

import bj.codesolution.rxbeauty.ui.view.BaseView;
import rx.Subscription;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public abstract class BasePresenter<T extends BaseView> {
    Subscription subscription;
    protected Context context;
    T iView;

    BasePresenter(Context context, T iView) {
        this.context = context;
        this.iView = iView;
    }

    public void init() {
        iView.initView();
    }

    public abstract void release();
}