package com.open.tencenttv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/3
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class LastHistoryAdapter extends CommonAdapter<String> {
	private final LayoutInflater mInflater;
    public LastHistoryAdapter(Context mContext, List<String> list) {
		super(mContext, list);
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//
//            convertView = mInflater.inflate(R.layout.adapter_pindao_fragment, parent, false);
//            convertView.setTag(new ViewHolder(convertView));
////        }
//        viewHolder = (ViewHolder) convertView.getTag();
//        bindViewData(position, viewHolder);
//        return convertView;
        View view;
        view = mInflater.inflate(R.layout.adapter_recyclerview_list_history, parent, false);
        TextView titleTv = (TextView) view.findViewById(R.id.text_type_name);
        titleTv.setText((String)getItem(position));
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2) {
            return 0;
        } else {
            return 1;
        }

    }

    //    private void bindViewData(int position, ViewHolder viewHolder) {
//        String title = mDatas.get(position);
//        viewHolder.titleTv.setText(title);
//    }

//    class ViewHolder {
//        View itemView;
//        TextView titleTv;
//
//        public ViewHolder(View view) {
//            this.itemView = view;
//            this.titleTv = (TextView) view.findViewById(R.id.textView);
//        }
//    }
}