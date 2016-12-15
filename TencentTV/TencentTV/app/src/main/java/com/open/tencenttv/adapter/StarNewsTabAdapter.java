/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:37:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.bean.StarFeedBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:37:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarNewsTabAdapter extends CommonAdapter<StarFeedBean> {

	public StarNewsTabAdapter(Context mContext, List<StarFeedBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final StarFeedBean bean = (StarFeedBean) getItem(position);
		ViewHolder mViewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_star_news_tab, null);
			mViewHolder = new ViewHolder();
			mViewHolder.txt_info_figure_date = (TextView) convertView.findViewById(R.id.txt_info_figure_date);
			mViewHolder.txt_feed_title = (TextView) convertView.findViewById(R.id.txt_feed_title);
			mViewHolder.txt_feed_desc_title = (TextView) convertView.findViewById(R.id.txt_feed_desc_title);
			mViewHolder.txt_feed_item_time = (TextView) convertView.findViewById(R.id.txt_feed_item_time);
			mViewHolder.lay_figures_scroll_data_boss = (LinearLayout) convertView.findViewById(R.id.lay_figures_scroll_data_boss);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		mViewHolder.txt_info_figure_date.setText(bean.getInfo_figure_date());
		mViewHolder.txt_feed_title.setText(bean.getFeed_title());
		mViewHolder.txt_feed_desc_title.setText(bean.getFeed_desc_title());
		mViewHolder.txt_feed_item_time.setText(bean.getFeed_item_time());
		mViewHolder.lay_figures_scroll_data_boss.removeAllViews();
		if (bean.getFigures_scroll_data_boss() != null && bean.getFigures_scroll_data_boss().size() > 0) {
			for (int i = 0; i < bean.getFigures_scroll_data_boss().size(); i++) {
				ImageView imageview = new ImageView(mContext);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.MATCH_PARENT);
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test).showImageForEmptyUri(R.drawable.grid_view_item_test)
						.showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getFigures_scroll_data_boss().get(i), imageview, options, getImageLoadingListener());

				params.leftMargin = 5;
				params.rightMargin = 5;
				mViewHolder.lay_figures_scroll_data_boss.addView(imageview, params);
			}
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, bean.getFeed_title_href());
			}
		});
		return convertView;
	}

	public class ViewHolder {
		TextView txt_info_figure_date;
		TextView txt_feed_title;
		LinearLayout lay_figures_scroll_data_boss;
		TextView txt_feed_desc_title;
		TextView txt_feed_item_time;

	}
}
