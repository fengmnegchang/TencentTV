package com.open.tencenttv.mode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.open.androidtvwidget.leanback.mode.DefualtListPresenter;
import com.open.tencenttv.andenginetask.AsyncTaskUtils;
import com.open.tencenttv.andenginetask.CallEarliest;
import com.open.tencenttv.andenginetask.Callable;
import com.open.tencenttv.andenginetask.Callback;
import com.open.tencenttv.andenginetask.IProgressListener;
import com.open.tencenttv.andenginetask.ProgressCallable;

/**
 * ****************************************************************************************************************************************************************************
 * 视频主页
 *
 * @author :fengguangjing
 * @createTime: 2016/11/17
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class AsyncTaskListPresenter<T> extends DefualtListPresenter implements CallEarliest<T>, Callback<T>, Callable<T>, ProgressCallable<T>,
        Response.Listener<JSONObject>, Response.ErrorListener {


    public AsyncTaskListPresenter() {

    }


    public AsyncTaskListPresenter(Object items) {
        mItems = (List<Object>) items;
    }

    /**
     * 封装的asynctask方法，此方法没有进度框.
     *
     * @param pCallEarliest 运行于主线程，最先执行此方法.
     * @param mCallable 运行于异步线程,第二执行此方法.
     * @param mCallback 运行于主线程,最后执行此方法.
     */
    public <T> void doAsync(final CallEarliest<T> pCallEarliest,
                            final Callable<T> mCallable, final Callback<T> mCallback) {
        AsyncTaskUtils.doAsync(pCallEarliest, mCallable, mCallback);
    }

    /**
     * 封装的asynctask方法，此方法拥有进度对话框，并支持定义样式.
     * @param pContext  上下文
     * @param styleID   对话框样式 ProgressDialog.STYLE_HORIZONTAL|ProgressDialog.STYLE_SPINNER
     * @param pTitle    标题
     * @param pMessage  内容
     * @param pCallEarliest  运行于主线程，最先执行此方法.
     * @param progressCallable 运行于异步线程,用于传递对话框进度.
     * @param pCallback  运行于主线程,最后执行此方法.
     */
    public <T> void doProgressAsync(final Context pContext, final int styleID,
                                    final String pTitleResID, final String pMessageResID,
                                    final CallEarliest<T> pCallEarliest, final ProgressCallable<T> pCallable,
                                    final Callback<T> pCallback) {

        AsyncTaskUtils.doProgressAsync(pContext, styleID, pTitleResID,
                pMessageResID, pCallEarliest, pCallable, pCallback);
    }


    /**
     * 封装的asynctask方法，此方法拥有进度对话框，并支持定义样式.
     * @param pContext  上下文
     * @param styleID   对话框样式 ProgressDialog.STYLE_HORIZONTAL|ProgressDialog.STYLE_SPINNER
     * @param pTitle    标题,资源id
     * @param pMessage  内容,资源id
     * @param pCallEarliest  运行于主线程，最先执行此方法.
     * @param progressCallable 运行于异步线程,用于传递对话框进度.
     * @param pCallback  运行于主线程,最后执行此方法.
     */
    public <T> void doProgressAsync(final Context pContext, final int styleID,
                                    final int pTitleResID, final int pMessageResID,
                                    final CallEarliest<T> pCallEarliest, final ProgressCallable<T> pCallable,
                                    final Callback<T> pCallback) {

        AsyncTaskUtils.doProgressAsync(pContext, styleID, pTitleResID,
                pMessageResID, pCallEarliest, pCallable, pCallback);
    }

    /*
     * 请求预加载 同于AsyncTask的doInBackground
     * (non-Javadoc)
     *
     * @see com.example.andenginetask.Callable#call()
     */
    @Override
    public T call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 请求返回 同于AsyncTask的onPostExecute
     * (non-Javadoc)
     *
     * @see com.example.andenginetask.Callback#onCallback(java.lang.Object)
     */
    @Override
    public void onCallback(T result) {
        // TODO Auto-generated method stub

    }

    /*
     *
     * 请求预加载 同于AsyncTask的onPreExecute
     * (non-Javadoc)
     *
     * @see com.example.andenginetask.CallEarliest#onCallEarliest()
     */
    @Override
    public void onCallEarliest() throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * 请求预加载 同于AsyncTask的doInBackground
     * (non-Javadoc)
     *
     * @see
     * com.example.andenginetask.ProgressCallable#call(com.example.andenginetask
     * .IProgressListener)
     */
    @Override
    public T call(IProgressListener pProgressListener) throws Exception {
        return null;
    }


    /*
     * json 请求 Error
     * (non-Javadoc)
     * @see com.android.volley.Response.ErrorListener#onErrorResponse(com.android.volley.VolleyError)
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        // TODO Auto-generated method stub

    }

    /*
     * json 请求返回
     * (non-Javadoc)
     * @see com.android.volley.Response.Listener#onResponse(java.lang.Object)
     */
    @Override
    public void onResponse(JSONObject response) {
        // TODO Auto-generated method stub

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
}
