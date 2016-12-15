/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午2:44:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.open.tencenttv.bean.UserStarTeamBean;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午2:44:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserStarTeamListViewAdapter extends CommonAdapter<UserStarTeamBean>{
	private int isRmd;
	public UserStarTeamListViewAdapter(Context mContext, List<UserStarTeamBean> list) {
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
		final UserStarTeamBean bean = (UserStarTeamBean) getItem(position);
		if (bean != null) {
			viewHolder.text_nick.setText(bean.getName());
			if (bean.getPic() != null && bean.getPic().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
						.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getPic(), viewHolder.imageview, options, getImageLoadingListener());
			}
			viewHolder.text_more.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 查看更多
					//http://v.qq.com/search.html?ms_key=%E5%9F%83%E5%B0%94%E5%85%8B%E6%A3%AE-%E5%BE%B7%E5%A5%A5%E5%88%A9%E7%BB%B4%E6%8B%89
					try {
						TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_SEARCH_MS_KEY + URLEncoder.encode(bean.getName(),"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			});
			if(isRmd==0){
				viewHolder.text_inner.setText("取消订阅");
			}else{
				viewHolder.text_inner.setText("订阅");
			}
			viewHolder.text_inner.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 订阅
					follow(bean.getFollowID(),bean.getSrctype());
				}
			});
			if (bean.getItemlist() != null && bean.getItemlist().size() > 0) {
				for (int i = 0; i < bean.getItemlist().size(); i++) {
					View view = mInflater.inflate(R.layout.layout_user_subscribe_item, null);
					ImageView imageview = (ImageView) view.findViewById(R.id.imageview_pic);
					TextView text_duration = (TextView) view.findViewById(R.id.text_duration);
					TextView text_title = (TextView) view.findViewById(R.id.text_title);
					text_duration.setText("");
					text_title.setText(bean.getItemlist().get(i).getC_title());
					if (bean.getItemlist().get(i).getPic() != null && bean.getItemlist().get(i).getPic().length() > 0) {
						DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist).showImageForEmptyUri(R.drawable.mainview_cloudlist)
								.showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc().build();
						ImageLoader.getInstance().displayImage(bean.getItemlist().get(i).getPic(), imageview, options, getImageLoadingListener());
					}

					final int urli = i;
					view.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							//https://v.qq.com/x/cover/dixpsp0erh10v1n/r0016gkcftd.html?
							if(bean.getItemlist().get(urli).getC_vid()!=null && bean.getItemlist().get(urli).getC_vid().length()>0){
								TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_COVER+"dixpsp0erh10v1n/"+bean.getItemlist().get(urli).getC_vid()+".html");
							}else{
								TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, UrlUtils.TENCENT_COVER+bean.getItemlist().get(urli).getC_cover_id()+".html");
							}
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

	private void follow(String id,String t) {
		//订阅http://sns.video.qq.com/fcgi-bin/liveportal/follow?otype=json&f=2&id=199737&t=36&op=2&g_tk=1605338694
		//取消订阅http://sns.video.qq.com/fcgi-bin/liveportal/follow?otype=json&f=2&id=199737&t=36&op=3&g_tk=1605338694
		String href = "";
		if(isRmd==0){
			 href = "http://sns.video.qq.com/fcgi-bin/liveportal/follow?otype=json&f=2&id="+id+"&t="+t+"&op=3&g_tk=1605338694";
		}else{
			  href = "http://sns.video.qq.com/fcgi-bin/liveportal/follow?otype=json&f=2&id="+id+"&t="+t+"&op=2&g_tk=1605338694";
		}
		Map<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("Cookie",UrlUtils.getCookie());
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, href, headerMap, new Response.Listener<String>() {
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

	public void setIsRmd(int isRmd) {
		this.isRmd = isRmd;
	}
	
	
}
