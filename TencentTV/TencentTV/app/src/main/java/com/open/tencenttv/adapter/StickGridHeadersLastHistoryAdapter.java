package com.open.tencenttv.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.bean.LastHistoryBean;
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
public class StickGridHeadersLastHistoryAdapter extends StickyGridHeadersSimpleArrayAdapter<LastHistoryBean> {

    public StickGridHeadersLastHistoryAdapter(Context context, List<LastHistoryBean> items, int headerResId, int itemResId) {
        super(context, items, headerResId, itemResId);
        init(context, items, headerResId, itemResId);
    }

    public StickGridHeadersLastHistoryAdapter(Context context, LastHistoryBean[] items, int headerResId, int itemResId) {
        super(context, items, headerResId, itemResId);
        init(context, Arrays.asList(items), headerResId, itemResId);
    }

    @Override
    public long getHeaderId(int position) {
        LastHistoryBean item = getItem(position);
        return item.getItemType();
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mHeaderResId, parent, false);
            holder = new HeaderViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        LastHistoryBean item = getItem(position);
        // set header text as first char in string
        holder.textView.setText(item.getItemTypeName());
        return convertView;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemResId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LastHistoryBean item = getItem(position);
        holder.textView.setText(item.getDramaName()+position);
        return convertView;
    }

    protected class HeaderViewHolder {
        public TextView textView;
    }

    protected class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
