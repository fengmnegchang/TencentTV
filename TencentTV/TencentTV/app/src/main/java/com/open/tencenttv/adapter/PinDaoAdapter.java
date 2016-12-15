package com.open.tencenttv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.PinDaoBean;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PinDaoAdapter extends CommonAdapter<PinDaoBean> {
    public PinDaoAdapter(Context mContext, List<PinDaoBean> list) {
		super(mContext, list);
	}

//	private List<PinDaoBean> data;
//    private Context mContext;
    
//    public PinDaoAdapter(Context mContext,List<PinDaoBean> data){
//        this.mContext = mContext;
//        this.data = data;
//    }

//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public PinDaoBean getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PinDaoBean bean = (PinDaoBean) getItem(position);
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_pindao_type, null);
        ImageView image_tpye_bg = (ImageView) view.findViewById(R.id.image_tpye_bg);
        TextView text_type_name = (TextView) view.findViewById(R.id.text_type_name);
        text_type_name.setText(bean.getTypeName());
//        if (bean != null) {
//            if (bean.getType() ==2 ) {
//                image_tpye_bg.setVisibility(View.GONE);
//            }else{
//                image_tpye_bg.setVisibility(View.VISIBLE);
//            }
//        }
        return view;
    }


}