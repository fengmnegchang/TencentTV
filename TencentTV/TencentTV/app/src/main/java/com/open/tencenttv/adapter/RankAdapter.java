package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.RankBean;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RankAdapter extends CommonAdapter<RankBean> {
    

    public RankAdapter(Context mContext, List<RankBean> list) {
		super(mContext, list);
		// TODO Auto-generated constructor stub
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RankBean bean = (RankBean) getItem(position);
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_rank_type, null);
        TextView text_rank_name = (TextView) view.findViewById(R.id.text_rank_name);
        text_rank_name.setText(bean.getRankName());
        return view;
    }


}