package com.open.tencenttv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.PanBean;

import java.util.List;


/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/14
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PanAdapter extends BaseAdapter {
    List<PanBean> list;
    public PanAdapter(List<PanBean> list){
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword_pan, null,false);
        PanBean bean = (PanBean) getItem(position);
        textView.setText(bean.getKeyValue());
        return textView;
    }
}
