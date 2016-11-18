package com.open.tencenttv.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.open.tencenttv.fragment.RankFragment;

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
    private List<RankFragment> listRankFragment;

    public RankPagerAdapter(FragmentManager fm, List<RankFragment> listRankFragment) {
        super(fm);
        this.listRankFragment = listRankFragment;
    }

    @Override
    public int getCount() {
        return listRankFragment.size();
    }

    @Override
    public RankFragment getItem(int position) {
        return listRankFragment.get(position);
    }

}
