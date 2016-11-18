package com.open.tencenttv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/17
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RankPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listRankFragment;


    public RankPagerAdapter(FragmentManager fm, List<Fragment> listRankFragment) {
        super(fm);
        this.listRankFragment = listRankFragment;
    }

    @Override
    public int getCount() {
        return listRankFragment.size();
    }


    @Override
    public Fragment getItem(int position) {
        return listRankFragment.get(position);
    }

}
