/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-23下午1:01:32
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-23下午1:01:32
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class WeakActivityReferenceHandler extends Handler {
	WeakReference<BaseFragmentActivity> weakReferenceHandler;

	public WeakActivityReferenceHandler(BaseFragmentActivity activity) {
		weakReferenceHandler = new WeakReference<BaseFragmentActivity>(activity);
	}

	@SuppressLint("NewApi") @Override
	public void handleMessage(Message msg) {
		BaseFragmentActivity activity = weakReferenceHandler.get();
		if (activity != null && !activity.isFinishing()) {
			if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN_MR1){
				if(!activity.isDestroyed() ){
					activity.handlerMessage(msg);
					super.handleMessage(msg);
				}
			}else{
				activity.handlerMessage(msg);
				super.handleMessage(msg);
			}
			
		}
	}

}
