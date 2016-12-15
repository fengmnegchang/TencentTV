/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:19:24
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:19:24
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommonExpandableListAdapter<G, C> extends BaseExpandableListAdapter {
	public Context mContext;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	public LayoutInflater mInflater;
	public List<G> list;

	public CommonExpandableListAdapter(Context mContext, List<G> list) {
		this.mContext = mContext;
		this.list = list;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 */
	@Override
	public C getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean arg2, View arg3, ViewGroup arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 */
	@Override
	public G getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 */
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(int groupPosition, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 */
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	protected ImageLoadingListener getImageLoadingListener() {
		return animateFirstListener;
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
		public static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
