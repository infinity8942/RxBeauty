package bj.codesolution.rxbeauty.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.presenter.MainPresenter;
import bj.codesolution.rxbeauty.ui.adapter.BeautyPagerAdapter;
import bj.codesolution.rxbeauty.ui.view.BaseView;
import butterknife.BindView;

public class MainActivity extends ToolbarActivity<MainPresenter> implements BaseView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter(Bundle savedInstanceState) {
        presenter = new MainPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        BeautyPagerAdapter pagerAdapter = new BeautyPagerAdapter(getSupportFragmentManager());
        container.setAdapter(pagerAdapter);
        container.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
    }

    protected boolean canBack() {
        return false;
    }
}