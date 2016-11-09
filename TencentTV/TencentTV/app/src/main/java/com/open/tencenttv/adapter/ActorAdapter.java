package com.open.tencenttv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.ActorBean;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *  演员信息
 *
 * @author :fengguangjing
 * @createTime: 2016/11/6
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ActorAdapter extends BaseAdapter {
    private List<ActorBean> data;
    private Context mContext;
    public ActorAdapter(Context mContext, List<ActorBean> data){
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ActorBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActorBean bean = getItem(position);
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_actor, null);
        ImageView image_actor_bg = (ImageView) view.findViewById(R.id.image_actor_bg);
        TextView text_actor_name = (TextView) view.findViewById(R.id.text_actor_name);
        text_actor_name.setText(bean.getActorName()+"("+bean.getRole()+")");
        return view;
    }


}