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
 * @createTime: 16/11/3
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RankFragmentAdapter extends BaseAdapter {
    private List<RankBean> mDatas;
    private final LayoutInflater mInflater;

    public RankFragmentAdapter(Context context, List<RankBean> data) {
        mDatas = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = mInflater.inflate(R.layout.adapter_rank_fragment, parent, false);
        TextView txt_count = (TextView) view.findViewById(R.id.txt_count);
        TextView txt_actor_name = (TextView) view.findViewById(R.id.txt_actor_name);
        TextView txt_actor_type = (TextView) view.findViewById(R.id.txt_actor_type);
        TextView txt_play_times = (TextView) view.findViewById(R.id.txt_play_times);

        RankBean bean = (RankBean) getItem(position);
        txt_count.setText(bean.getCount());
        txt_actor_name.setText(bean.getActorName());
        txt_actor_type.setText(bean.getActorType());
        txt_play_times.setText(bean.getPlayTimes());

        return view;
    }

}