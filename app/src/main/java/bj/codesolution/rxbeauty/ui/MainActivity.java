package bj.codesolution.rxbeauty.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Menu;
import android.view.MenuItem;

import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.databinding.ActivityMainBinding;
import bj.codesolution.rxbeauty.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(getBinding().toolbar);

        BeautyPagerAdapter pagerAdapter = new BeautyPagerAdapter(getSupportFragmentManager());
        getBinding().container.setAdapter(pagerAdapter);
        getBinding().container.setOffscreenPageLimit(3);
        getBinding().tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        getBinding().tabLayout.setupWithViewPager(getBinding().container);
    }

    private class BeautyPagerAdapter extends FragmentStatePagerAdapter {
        private String[] title = {"最新", "推荐"};

        BeautyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BeautyFragment.newInstance(title[position]);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
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

    protected boolean canBack() {
        return false;
    }
}