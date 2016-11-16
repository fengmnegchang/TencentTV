package com.open.tencenttv.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.open.tencenttv.R;

public class GridViewHolder extends OpenPresenter.ViewHolder {
	
	public ImageView iv; 
	public TextView tv;

	public GridViewHolder(View itemView) {
		super(itemView);
//		iv = (ImageView)itemView.findViewById(R.id.imageView);
		tv = (TextView)itemView.findViewById(R.id.textView);
	}

}
