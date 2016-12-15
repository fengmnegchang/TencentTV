package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.bean.SearchWordsBean;
import com.open.tencenttv.json.SearchWordsJson;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ******************
 * 
 * @author :fengguangjing
 * @createTime: 16/11/3
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: 
 *               ****************************************************************
 *               ***************************************************************
 *               *********************************************
 */
public class SearchWordsAdapter extends CommonAdapter<SearchWordsBean> {
	private final LayoutInflater mInflater;

	public SearchWordsAdapter(Context mContext, List<SearchWordsBean> list) {
		super(mContext, list);
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_search_words, parent, false);
			viewHolder.text_word_class = (TextView) convertView.findViewById(R.id.text_word_class);
			viewHolder.img_dc = (ImageView) convertView.findViewById(R.id.img_dc);
			viewHolder.text_ex = (TextView) convertView.findViewById(R.id.text_ex);
			viewHolder.text_pa = (TextView) convertView.findViewById(R.id.text_pa);
			viewHolder.text_play = (TextView) convertView.findViewById(R.id.text_play);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final SearchWordsBean bean = (SearchWordsBean) getItem(position);
		if (bean != null) {
			viewHolder.text_word_class.setText(bean.getWord());
			viewHolder.text_ex.setText(bean.getSn());
			viewHolder.text_pa.setText(bean.getPa());

			if (bean.getDc() != null && bean.getDc().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test).showImageForEmptyUri(R.drawable.grid_view_item_test)
						.showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getDc(), viewHolder.img_dc, options, getImageLoadingListener());
			}

			viewHolder.text_play.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TencentTVWebViewActivity.startTencentTVWebViewActivity(mContext, bean.getUrl());
				}
			});
		}
		return convertView;
	}

	public class ExBean {
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	class ViewHolder {
		TextView text_word_class;
		ImageView img_dc;
		TextView text_ex;
		TextView text_pa;
		TextView text_play;
	}

}