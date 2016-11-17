package com.open.tencenttv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.NewFeatureBean;
import com.open.tencenttv.imageloader.ImageLoader;
import com.open.tencenttv.utils.UrlUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleArrayAdapter;

import java.util.Arrays;
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
    ImageLoader mImageLoader;
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

        mImageLoader = new ImageLoader(context);
        mImageLoader.setRequiredSize(5 * (int) context.getResources().getDimension(R.dimen.litpic_width));
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
            mImageLoader.DisplayImage(UrlUtils.TENCENT_IMAGE_URL+item.getImageUrl(), holder.imageView);
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

    protected class HeaderViewHolder {
        public TextView textView;
    }

    protected class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
