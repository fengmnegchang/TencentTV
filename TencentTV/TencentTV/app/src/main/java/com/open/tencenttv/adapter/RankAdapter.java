package com.open.tencenttv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.RankBean;

import java.util.List;

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
public class RankAdapter extends BaseAdapter {
    private List<RankBean> data;
    private Context mContext;
    public RankAdapter(Context mContext, List<RankBean> data){
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public RankBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RankBean bean = getItem(position);
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_rank_type, null);
        TextView text_rank_name = (TextView) view.findViewById(R.id.text_rank_name);
        text_rank_name.setText(bean.getRankName());
        return view;
    }


}