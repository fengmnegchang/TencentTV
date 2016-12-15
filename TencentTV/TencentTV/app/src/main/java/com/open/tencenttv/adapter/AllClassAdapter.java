/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午4:31:25
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
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.AllBean;
import com.open.tencenttv.bean.PinDaoBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午4:31:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class AllClassAdapter extends CommonAdapter<AllBean> {

	public AllClassAdapter(Context mContext, List<AllBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AllBean bean = (AllBean) getItem(position);
		View view;
		view = LayoutInflater.from(mContext).inflate(R.layout.adapter_all_class, null);
		TextView text_name = (TextView) view.findViewById(R.id.text_name);
		text_name.setText(bean.getFunctionDesp());
		return view;
	}

}
