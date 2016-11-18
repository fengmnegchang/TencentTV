package com.open.tencenttv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.SliderNavBean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/17
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MediumPagerAdapter extends PagerAdapter {
    private List<SliderNavBean> list;// view数组
    Context context;

    public MediumPagerAdapter(Context context, List<SliderNavBean> list) {
        this.list = list;
        this.context = context;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_medium_pager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);

        SliderNavBean sliderNavBean = list.get(position);
        textView.setText(sliderNavBean.getTitle());
        if (sliderNavBean.getImageUrl() != null && sliderNavBean.getImageUrl().length() > 0) {
//            mImageLoader.DisplayImage(sliderNavBean.getImageUrl(), imageView);
            DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test)
                    .showImageForEmptyUri(R.drawable.grid_view_item_test)
                    .showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc()
                    .build();
            ImageLoader.getInstance().displayImage(sliderNavBean.getImageUrl(), imageView,options,animateFirstListener);
        }
        container.addView(view);
        return view;
    }
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

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
