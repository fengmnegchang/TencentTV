/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-22下午4:42:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.widget.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-22下午4:42:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ObservableScrollView extends ScrollView {  
  
    private ScrollViewListener scrollViewListener = null;  
  
    public ObservableScrollView(Context context) {  
        super(context);  
    }  
  
    public ObservableScrollView(Context context, AttributeSet attrs,  
            int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public ObservableScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {  
        this.scrollViewListener = scrollViewListener;  
    }  
  
    @Override  
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {  
        super.onScrollChanged(x, y, oldx, oldy);  
        if (scrollViewListener != null) {  
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);  
        }  
    }  
  
}  