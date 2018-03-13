package bj.codesolution.rxbeauty.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import bj.codesolution.rxbeauty.App;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public abstract class BaseFragment<Bind extends ViewDataBinding> extends Fragment {

    private Bind binding;

    @Nullable
    @Override
    public final View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId() == 0)
            return super.onCreateView(inflater, container, savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    public Bind getBinding() {
        return binding;
    }

    protected abstract int layoutId();

    protected abstract void initView();
}