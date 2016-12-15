package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.PersonalCenterBean;

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
public class PersonalCenterAdapter extends CommonAdapter<PersonalCenterBean> {

    public PersonalCenterAdapter(Context mContext, List<PersonalCenterBean> list) {
		super(mContext, list);
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonalCenterBean bean = (PersonalCenterBean) getItem(position);
        if (bean != null) {
            if (bean.getType() == 0) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_personal_center_type0, null);
                ImageView image_tpye_bg = (ImageView) view.findViewById(R.id.image_tpye_bg);
                TextView text_type_name = (TextView) view.findViewById(R.id.text_type_name);
                TextView text_type_content = (TextView) view.findViewById(R.id.text_type_content);

                text_type_name.setText(bean.getTypeName());
                text_type_content.setText(bean.getContent());
                return view;
            } else {
                View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_personal_center_type12, null);
                ImageView image_tpye_bg = (ImageView) view.findViewById(R.id.image_tpye_bg);
                TextView text_type_name = (TextView) view.findViewById(R.id.text_type_name);
                text_type_name.setText(bean.getTypeName());
                return view;
            }
        }
        return null;

    }


}