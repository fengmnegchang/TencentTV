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
    List<String> titleList;

    public RankPagerAdapter(FragmentManager fm, List<Fragment> listRankFragment, List<String> titleList ) {
        super(fm);
        this.listRankFragment = listRankFragment;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return listRankFragment.size();
    }


    @Override
    public Fragment getItem(int position) {
        return listRankFragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }
}
