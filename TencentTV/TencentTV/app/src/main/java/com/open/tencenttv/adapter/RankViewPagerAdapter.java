package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.RankBean;

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
public class RankViewPagerAdapter extends CommonPagerAdapter<RankBean> {
    

    public RankViewPagerAdapter(Context mContext, List<RankBean> list) {
		super(mContext, list);
		// TODO Auto-generated constructor stub
	}

	public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_medium_pager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);

        RankBean sliderNavBean =  getItem(position);
//        textView.setText(sliderNavBean.getTitle());

        container.addView(view);
        return view;
    }

}
