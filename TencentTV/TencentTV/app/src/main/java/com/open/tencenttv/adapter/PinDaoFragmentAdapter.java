package com.open.tencenttv.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.PinDaoBean;
import com.open.tencenttv.utils.UrlUtils;

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
public class PinDaoFragmentAdapter extends CommonAdapter<PinDaoBean> {
//	private List<PinDaoBean> list = new ArrayList<PinDaoBean>();
	private final LayoutInflater mInflater;

	public PinDaoFragmentAdapter(Context mContext, List<PinDaoBean> list) {
		super(mContext, list);
//		this.list = list;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

//	@Override
//	public int getCount() {
//		return list.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return list.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_pindao_fragment, parent, false);
			viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imagesrc);
			viewHolder.textalt = (TextView) convertView.findViewById(R.id.textalt);
			viewHolder.textmask_txt = (TextView) convertView.findViewById(R.id.textmask_txt);
			viewHolder.textmod_score = (TextView) convertView.findViewById(R.id.textmod_score);
			viewHolder.textfigure_desc = (TextView) convertView.findViewById(R.id.textfigure_desc);
			viewHolder.textfigure_info = (TextView) convertView.findViewById(R.id.textfigure_info);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		PinDaoBean bean = (PinDaoBean) getItem(position);
		if(bean!=null){
			viewHolder.textalt.setText(bean.getAlt());
			viewHolder.textmask_txt.setText(bean.getMask_txt());
			viewHolder.textmod_score.setText(bean.getMod_score());
			viewHolder.textfigure_desc.setText(bean.getFigure_desc());
			viewHolder.textfigure_info.setText(bean.getFigure_info());
			
			 if(bean.getImagesrc()!=null && bean.getImagesrc().length()>0){
	            DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test)
	                    .showImageForEmptyUri(R.drawable.grid_view_item_test)
	                    .showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc()
	                    .build();
	            ImageLoader.getInstance().displayImage(UrlUtils.TENCENT_IMAGE_URL+bean.getImagesrc(),viewHolder.imagesrc,options,getImageLoadingListener());
	        }
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imagesrc;
		TextView textalt;
		TextView textmask_txt;
		TextView textmod_score;
		TextView textfigure_desc;
		TextView textfigure_info;
	}

}