/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:42:00
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.bean.UserFollowBean;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:42:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserFollowGridViewAdapter extends CommonAdapter<UserFollowBean> {

	public UserFollowGridViewAdapter(Context mContext, List<UserFollowBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_follow_gridview, parent, false);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_c_timelong = (TextView) convertView.findViewById(R.id.text_c_timelong);
			viewHolder.text_c_type_name = (TextView) convertView.findViewById(R.id.text_c_type_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final UserFollowBean bean = (UserFollowBean) getItem(position);
		if (bean != null) {
			viewHolder.text_c_timelong.setText(bean.getC_timelong());
			viewHolder.text_c_type_name.setText("["+bean.getC_type_name()+"]"+bean.getC_title());
			if (bean.getPic() != null && bean.getPic().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
						.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getPic(), viewHolder.imageview, options, getImageLoadingListener());
			}
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_COVER+bean.getC_cover_id()+".html");
			}
		});
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;
		TextView text_c_timelong;
		TextView text_c_type_name;
	}
}
