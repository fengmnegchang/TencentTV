/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午3:18:16
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.open.androidtvwidget.view.ListViewTV;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午3:18:16
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ScrollListViewTV extends ListViewTV {

	public ScrollListViewTV(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ScrollListViewTV(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//		super.onMeasure(widthMeasureSpec, expandSpec);
//	}
}
