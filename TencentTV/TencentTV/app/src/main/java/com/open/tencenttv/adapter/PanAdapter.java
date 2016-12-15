package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.PanBean;


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
public class PanAdapter extends CommonAdapter<PanBean> {

    public PanAdapter(Context mContext, List<PanBean> list) {
		super(mContext, list);
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword_pan, null,false);
        PanBean bean = (PanBean) getItem(position);
        textView.setText(bean.getKeyValue());
        return textView;
    }
}
