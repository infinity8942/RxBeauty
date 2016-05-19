package bj.codesolution.rxbeauty.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bj.codesolution.rxbeauty.presenter.BasePresenter;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected View mRootView;
    private Unbinder unbinder;
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initPresenter(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter(Bundle savedInstanceState);
}