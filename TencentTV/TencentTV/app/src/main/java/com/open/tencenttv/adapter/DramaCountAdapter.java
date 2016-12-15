package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.DramaCountBean;

/**
 * ****************************************************************************************************************************************************************************
 * 剧集分页处理
 *
 * @author :fengguangjing
 * @createTime: 2016/11/6
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class DramaCountAdapter extends CommonAdapter<DramaCountBean> {
    public DramaCountAdapter(Context mContext, List<DramaCountBean> list) {
		super(mContext, list);
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DramaCountBean bean = (DramaCountBean) getItem(position);
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_drama, null);
        ImageView image_drama_bg = (ImageView) view.findViewById(R.id.image_drama_bg);
        TextView text_drama_name = (TextView) view.findViewById(R.id.text_drama_name);
        if(bean.getDramaType()==0){
            image_drama_bg.setVisibility(View.GONE);
        }else{
            image_drama_bg.setVisibility(View.VISIBLE);
        }
        text_drama_name.setText(bean.getDramaName());
        return view;
    }


}