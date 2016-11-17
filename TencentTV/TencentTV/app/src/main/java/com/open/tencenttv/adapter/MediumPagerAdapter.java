package com.open.tencenttv.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.SliderNavBean;
import com.open.tencenttv.imageloader.ImageLoader;

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
    ImageLoader mImageLoader;
    Context context;

    public MediumPagerAdapter(Context context, List<SliderNavBean> list) {
        this.list = list;
        this.context = context;
        mImageLoader = new ImageLoader(context);
        mImageLoader.setRequiredSize(5 * (int) context.getResources().getDimension(R.dimen.litpic_width));
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
            mImageLoader.DisplayImage(sliderNavBean.getImageUrl(), imageView);
        }
        container.addView(view);
        return view;
    }

}
