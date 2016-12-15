/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25上午10:54:53
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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.UserGiftBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25上午10:54:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserGiftGridViewAdapter extends CommonAdapter<UserGiftBean> {
	LayoutInflater mInflater;

	public UserGiftGridViewAdapter(Context mContext, List<UserGiftBean> list) {
		super(mContext, list);
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_gift_gridview, parent, false);
			viewHolder.imageview_pic = (ImageView) convertView.findViewById(R.id.imageview_pic);
			viewHolder.text_my_discount_txt = (TextView) convertView.findViewById(R.id.text_my_discount_txt);
			viewHolder.text_left_num = (TextView) convertView.findViewById(R.id.text_left_num);
			viewHolder.text_exchange = (TextView) convertView.findViewById(R.id.text_exchange);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		UserGiftBean bean = (UserGiftBean) getItem(position);
		if(bean!=null){
			viewHolder.text_my_discount_txt.setText(bean.getMy_discount_txt()+":"+bean.getMy_vip_vcoin());
			viewHolder.text_left_num.setText("剩余数量："+bean.getLeft_num());
			if(bean.getUrl_pic()!=null && bean.getUrl_pic().length()>0){
		          DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist)
		                  .showImageForEmptyUri(R.drawable.mainview_cloudlist)
		                  .showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc()
		                  .build();
		          ImageLoader.getInstance().displayImage(bean.getUrl_pic(),viewHolder.imageview_pic,options,getImageLoadingListener());
		      }
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imageview_pic;
		TextView text_my_discount_txt;
		TextView text_left_num;
		TextView text_exchange;
	}
}
