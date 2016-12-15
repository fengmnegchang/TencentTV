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
import com.open.tencenttv.bean.StarRelateBean;
import com.open.tencenttv.widget.CircleImageView;

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
public class StarHeadGridViewAdapter extends CommonAdapter<StarRelateBean> {
	LayoutInflater mInflater;

	public StarHeadGridViewAdapter(Context mContext, List<StarRelateBean> list) {
		super(mContext, list);
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_star_head_gridview, parent, false);
			viewHolder.imageview = (CircleImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
			viewHolder.text_relation = (TextView) convertView.findViewById(R.id.text_relation);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		StarRelateBean bean = (StarRelateBean) getItem(position);
		if(bean!=null){
			viewHolder.text_relation.setText(bean.getRelation());
			viewHolder.text_name.setText(bean.getStar_name());
			if(bean.getStar_relatedPic()!=null && bean.getStar_relatedPic().length()>0){
		          DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist)
		                  .showImageForEmptyUri(R.drawable.mainview_cloudlist)
		                  .showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc()
		                  .build();
		          ImageLoader.getInstance().displayImage(bean.getStar_relatedPic(),viewHolder.imageview,options,getImageLoadingListener());
		      }
		}
		return convertView;
	}

	class ViewHolder {
		CircleImageView imageview;
		TextView text_name;
		TextView text_relation;
	}
}
