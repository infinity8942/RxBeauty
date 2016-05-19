package bj.codesolution.rxbeauty.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.presenter.BasePresenter;
import butterknife.BindView;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public abstract class ToolbarActivity<T extends BasePresenter> extends BaseActivity {
    protected ActionBar actionBar;
    protected T presenter;
    protected boolean isToolBarHiding = false;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.app_bar)
    protected AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
    }

    /**
     * 设置 home icon 是否可见
     *
     * @return
     */
    protected boolean canBack() {
        return true;
    }

    protected void initToolBar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(canBack());

    }

    protected void hideOrShowToolBar() {
        appBar.animate()
                .translationY(isToolBarHiding ? 0 : -appBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isToolBarHiding = !isToolBarHiding;
    }
}