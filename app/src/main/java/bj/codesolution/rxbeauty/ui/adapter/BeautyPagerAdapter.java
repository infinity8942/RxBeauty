package bj.codesolution.rxbeauty.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bj.codesolution.rxbeauty.ui.fragment.BeautyFragment;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyPagerAdapter extends FragmentStatePagerAdapter {
    String[] title = {"最新", "瞎推荐", "拓展资源"};

    public BeautyPagerAdapter(FragmentManager fm) {
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
