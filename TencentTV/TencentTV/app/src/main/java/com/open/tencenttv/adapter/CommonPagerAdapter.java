package com.open.tencenttv.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.verticalview.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/12/07
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class CommonPagerAdapter<T> extends PagerAdapter {
	private List<T> list;
	public Context mContext;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public CommonPagerAdapter(Context mContext, List<T> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public T getItem(int position){
		return list.get(position);
	}
 
	@Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public Object instantiateItem(ViewGroup container, int position) {
        return null;
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
