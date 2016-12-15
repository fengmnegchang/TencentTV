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
public class WeakReferenceHandler extends Handler {
	WeakReference<BaseV4Fragment> weakReferenceHandler;

	public WeakReferenceHandler(BaseV4Fragment fragment) {
		weakReferenceHandler = new WeakReference<BaseV4Fragment>(fragment);
	}

	@Override
	public void handleMessage(Message msg) {
		BaseV4Fragment fragment = weakReferenceHandler.get();
		if (fragment != null && fragment.isVisible() && fragment.getUserVisibleHint()) {
			fragment.handlerMessage(msg);
			super.handleMessage(msg);
		}
	}

}
