/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午11:02:58
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.bean.UserVFollowlstBean;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 订阅
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午11:02:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserSubscribeListViewAdapter extends CommonAdapter<UserVFollowlstBean> {
	private int hasfollow = 0;
	public UserSubscribeListViewAdapter(Context mContext, List<UserVFollowlstBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_subscribe_listview, parent, false);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_nick = (TextView) convertView.findViewById(R.id.text_nick);
			viewHolder.text_more = (TextView) convertView.findViewById(R.id.text_more);
			viewHolder.text_inner = (TextView) convertView.findViewById(R.id.text_inner);
			viewHolder.layout_vs = (LinearLayout) convertView.findViewById(R.id.layout_vs);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final UserVFollowlstBean bean = (UserVFollowlstBean) getItem(position);
		if (bean != null) {
			viewHolder.text_nick.setText(bean.getNick());
			if (bean.getAvatar() != null && bean.getAvatar().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
						.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getAvatar(), viewHolder.imageview, options, getImageLoadingListener());
			}
			viewHolder.text_more.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 查看更多
					TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_V_PLUS + bean.getUrl());
				}
			});
			if(hasfollow>0){
				viewHolder.text_inner.setText("取消订阅");
			}else{
				viewHolder.text_inner.setText("订阅");
			}
			viewHolder.text_inner.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 订阅
					// http://c.v.qq.com/follow?otype=json&callback=jQuery19105151766284214294_1481251654247&op=set&fuin=356350317&g_tk=1605338694&_=1481251654265
					// http://c.v.qq.com/follow?otype=json&op=set&fuin=1124978816&g_tk=1605338694
					follow(bean.getUin());
				}
			});
			if (bean.getVs() != null && bean.getVs().size() > 0) {
				for (int i = 0; i < bean.getVs().size(); i++) {
					View view = mInflater.inflate(R.layout.layout_user_subscribe_item, null);
					ImageView imageview = (ImageView) view.findViewById(R.id.imageview_pic);
					TextView text_duration = (TextView) view.findViewById(R.id.text_duration);
					TextView text_title = (TextView) view.findViewById(R.id.text_title);
					text_duration.setText(bean.getVs().get(i).getDuration());
					text_title.setText(bean.getVs().get(i).getTitle());
					if (bean.getVs().get(i).getPic() != null && bean.getVs().get(i).getPic().length() > 0) {
						DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
								.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
						ImageLoader.getInstance().displayImage(bean.getVs().get(i).getPic(), imageview, options, getImageLoadingListener());
					}

					final int urli = i;
					view.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, bean.getVs().get(urli).getUrl());
						}
					});
					viewHolder.layout_vs.addView(view);
				}
			}

		}
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;
		TextView text_nick;
		TextView text_more;
		TextView text_inner;
		LinearLayout layout_vs;
	}

	private void follow(String fuin) {
		//http://c.v.qq.com/follow?otype=json&op=set&fuin=1124978816&g_tk=1605338694
		//http://c.v.qq.com/follow?otype=json&op=del&fuin=1124978816&g_tk=1605338694
		String href = "";
		if(hasfollow>0){
			  href = "http://c.v.qq.com/follow?otype=json&op=del&g_tk=1605338694&fuin="+fuin;
		}else{
			  href = "http://c.v.qq.com/follow?otype=json&op=set&g_tk=1605338694&fuin="+fuin;
		}
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, href, UrlUtils.getHeaders(), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println("response=" + response);
			}
		}, new ErrorListener() {
			/* (non-Javadoc)
			 * @see com.android.volley.Response.ErrorListener#onErrorResponse(com.android.volley.VolleyError)
			 */
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				System.out.println("error=" + error);
			}
		});
		requestQueue.add(jsonObjectRequest);
	}

	public void setHasfollow(int hasfollow) {
		this.hasfollow = hasfollow;
	}
	
}
