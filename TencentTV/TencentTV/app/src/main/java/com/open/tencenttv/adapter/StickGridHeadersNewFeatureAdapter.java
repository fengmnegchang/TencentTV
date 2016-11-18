package com.open.tencenttv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.open.tencenttv.bean.NewFeatureBean;
import com.open.tencenttv.utils.UrlUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleArrayAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/11
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class StickGridHeadersNewFeatureAdapter extends StickyGridHeadersSimpleArrayAdapter<NewFeatureBean> {
    Context context;

    public StickGridHeadersNewFeatureAdapter(Context context, List<NewFeatureBean> items, int headerResId, int itemResId) {
        super(context, items, headerResId, itemResId);
        init(context, items, headerResId, itemResId);
    }

    public StickGridHeadersNewFeatureAdapter(Context context, NewFeatureBean[] items, int headerResId, int itemResId) {
        super(context, items, headerResId, itemResId);
        init(context, Arrays.asList(items), headerResId, itemResId);
    }

    @Override
    public long getHeaderId(int position) {
        NewFeatureBean item = getItem(position);
        return item.getItemType();
    }

    public void init(Context context, List<NewFeatureBean> items, int headerResId, int itemResId) {
        this.mItems = items;
        this.mHeaderResId = headerResId;
        this.mItemResId = itemResId;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    @SuppressWarnings("unchecked")
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
//        HeaderViewHolder holder;
//        if (convertView == null) {
//            convertView = mInflater.inflate(mHeaderResId, parent, false);
//            holder = new HeaderViewHolder();
//            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
//            convertView.setTag(holder);
//        } else {
//            holder = (HeaderViewHolder) convertView.getTag();
//        }
//
//        NewFeatureBean item = getItem(position);
//        // set header text as first char in string
//        holder.textView.setText(item.getItemTypeName());
//        return convertView;

        View view = mInflater.inflate(mHeaderResId, parent, false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        NewFeatureBean item = getItem(position);
        textView.setText(item.getItemTypeName());
        return view;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemResId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewFeatureBean item = getItem(position);
        holder.textView.setText(item.getTitle());
        if(item.getImageUrl()!=null && item.getImageUrl().length()>0){
//            mImageLoader.DisplayImage(UrlUtils.TENCENT_IMAGE_URL+item.getImageUrl(), holder.imageView);
            DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.grid_view_item_test)
                    .showImageForEmptyUri(R.drawable.grid_view_item_test)
                    .showImageOnFail(R.drawable.grid_view_item_test).cacheInMemory().cacheOnDisc()
                    .build();
            ImageLoader.getInstance().displayImage(UrlUtils.TENCENT_IMAGE_URL+item.getImageUrl(),holder.imageView,options,animateFirstListener);
        }
        return convertView;

//        View view = mInflater.inflate(mItemResId, parent, false);
//        TextView textView = (TextView) view.findViewById(android.R.id.text1);
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
//        NewFeatureBean item = getItem(position);
//        textView.setText(item.getTitle());
//        if (item.getImageUrl() != null && item.getImageUrl().length() > 0) {
//            mImageLoader.DisplayImage(UrlUtils.TENCENT_IMAGE_URL + item.getImageUrl(), imageView);
//        }
//        return view;
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
    protected class HeaderViewHolder {
        public TextView textView;
    }

    protected class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
