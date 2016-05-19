package bj.codesolution.rxbeauty.presenter;

import android.content.Context;

import bj.codesolution.rxbeauty.BuildConfig;
import bj.codesolution.rxbeauty.api.BeautyClient;
import bj.codesolution.rxbeauty.model.BeautyData;
import bj.codesolution.rxbeauty.ui.view.BeautyView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyPresenter extends BasePresenter<BeautyView> {

    public BeautyPresenter(Context context, BeautyView view) {
        super(context, view);
    }

    @Override
    public void release() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    /**
     * 获取美女图片数据
     *
     * @param page 分页
     * @param num  每页数量（最大50）
     */
    public void getBeauty(int page, int num) {
        iView.showProgressBar();
        subscription = BeautyClient.getBeautyRetrofitInstance().getBeauty(BuildConfig.BAIDU_APISTORE_PUBLIC_KEY, page, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BeautyData>() {
                    @Override
                    public void call(BeautyData beautyData) {
                        iView.hideProgressBar();
                        if (beautyData.getNewslist().size() == 0) {
                            iView.showNoMoreData();
                        } else {
                            iView.loadData(beautyData.getNewslist());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        iView.hideProgressBar();
                        iView.showErrorView();
                    }
                });
    }
}