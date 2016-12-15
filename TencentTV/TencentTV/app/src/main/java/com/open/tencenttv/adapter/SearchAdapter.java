package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.SearchBean;
import com.open.tencenttv.bean.StarInfo;

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
public class SearchAdapter extends CommonAdapter<SearchBean> {
	private final LayoutInflater mInflater;

	public SearchAdapter(Context mContext, List<SearchBean> list) {
		super(mContext, list);
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_search, parent, false);
			viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imagesrc);

			viewHolder.text_figure_info = (TextView) convertView.findViewById(R.id.text_figure_info);
			viewHolder.text_result_figure_alt = (TextView) convertView.findViewById(R.id.text_result_figure_alt);
			viewHolder.text_result_info = (TextView) convertView.findViewById(R.id.text_result_info);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SearchBean bean = (SearchBean) getItem(position);
		if (bean != null) {
			viewHolder.text_figure_info.setText(bean.getFigure_info());
			viewHolder.text_result_figure_alt.setText(bean.getResult_figure_alt());
			if (bean.getResult_info() != null && bean.getResult_info().size() > 0) {
				viewHolder.text_result_info.setText("");
				for (StarInfo info : bean.getResult_info()) {
					viewHolder.text_result_info.append(Html.fromHtml(info.getListLeft()  + info.getListRight() + "<br/>"));
				}
			}
			if (bean.getResult_figure_src() != null && bean.getResult_figure_src().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test).showImageForEmptyUri(R.drawable.grid_view_item_test)
						.showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage("http:"+bean.getResult_figure_src(), viewHolder.imagesrc, options, getImageLoadingListener());
			}
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imagesrc;
		TextView text_figure_info;
		TextView text_result_figure_alt;
		TextView text_result_info;
	}

}