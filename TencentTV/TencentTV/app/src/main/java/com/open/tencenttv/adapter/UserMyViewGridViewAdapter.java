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
import com.open.tencenttv.bean.UserMyViewGridBean;
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
public class UserMyViewGridViewAdapter extends CommonAdapter<UserMyViewGridBean> {

	public UserMyViewGridViewAdapter(Context mContext, List<UserMyViewGridBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_my_view_gridview, parent, false);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_tl = (TextView) convertView.findViewById(R.id.text_tl);
			viewHolder.text_vtit = (TextView) convertView.findViewById(R.id.text_vtit);
			viewHolder.text_time = (TextView) convertView.findViewById(R.id.text_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final UserMyViewGridBean bean = (UserMyViewGridBean) getItem(position);
		if (bean != null) {
			viewHolder.text_tl.setText(bean.getTl());
			viewHolder.text_vtit.setText(bean.getVtit());
			if (bean.getPic() != null && bean.getPic().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
						.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getPic(), viewHolder.imageview, options, getImageLoadingListener());
			}
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//http://v.qq.com/cover/o/om1ljjrn220pdp2.html?vid=o0018rneamt
				if(bean.getCoverid()!=null && bean.getCoverid().length()>0){
					TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_COVER+bean.getCoverid()+".html?vid="+bean.getVid());
				}else{
					TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_COVER+"pager/"+bean.getVid()+".html");
				}
			}
		});
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;
		TextView text_tl;
		TextView text_vtit;
		TextView text_time;
	}
}
