/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:13:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.UserBillBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:13:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserBillAdapter extends CommonAdapter<UserBillBean> {

	public UserBillAdapter(Context mContext, List<UserBillBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_bill, parent, false);
			viewHolder.text_date = (TextView) convertView.findViewById(R.id.text_date);
			viewHolder.text_consumeEvent = (TextView) convertView.findViewById(R.id.text_consumeEvent);
			viewHolder.text_consumeDesc = (TextView) convertView.findViewById(R.id.text_consumeDesc);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		UserBillBean bean = (UserBillBean) getItem(position);
		if(bean!=null){
			viewHolder.text_date.setText(bean.getDate());
			viewHolder.text_consumeEvent.setText(bean.getConsumeEvent());
			viewHolder.text_consumeDesc.setText(bean.getConsumeDesc());
		}
		 
		return convertView;
	}

	class ViewHolder {
		TextView text_date;
		TextView text_consumeEvent;
		TextView text_consumeDesc;
	}
}
