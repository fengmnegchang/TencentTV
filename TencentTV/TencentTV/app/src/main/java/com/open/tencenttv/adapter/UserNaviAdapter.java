/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:32:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.UserNaviBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:32:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserNaviAdapter extends CommonAdapter<UserNaviBean> {

	public UserNaviAdapter(Context mContext, List<UserNaviBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UserNaviBean bean = (UserNaviBean) getItem(position);
		View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_user_navi, null);
		TextView text_type_name = (TextView) view.findViewById(R.id.text_type_name);
		text_type_name.setText(bean.getNavi_text());
		return view;
	}
}
