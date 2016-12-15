package com.open.tencenttv.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.PanAdapter;
import com.open.tencenttv.bean.CircularBean;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/9
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class CircularPopupWindow extends PopupWindow {
    public static final String TAG = CircularPopupWindow.class.getSimpleName();
    View conentView;
    CircularBean circularBean;

    public CircularPopupWindow(final Activity context, final CircularBean circularBean,final TextView input_tv,MainUpView mainUpView1,  View mOldView, final  EffectNoDrawBridge mRecyclerViewBridge) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_circular, null);
        this.circularBean = circularBean;

        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);


        CircularMenu wheelMenuView;
        PanAdapter adapter;

        wheelMenuView = (CircularMenu) conentView.findViewById(R.id.panview);
        adapter = new PanAdapter(context,circularBean.getPanList());
        wheelMenuView.setAdapter(adapter);
        wheelMenuView.setItemCount(circularBean.getPanList().size());
        wheelMenuView.setOnItemClickListener(new CircularMenu.OnItemClickListener() {
            @Override
            public void onItemPreSelected(View itemView, int position) {
                mRecyclerViewBridge.setUnFocusView(itemView);
                Log.i(TAG,"item ========onItemPreSelected "+position);
            }

            @Override
            public void onItemSelected(View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 2f);
                Log.i(TAG,"item  ========onItemSelected "+position);
            }

            @Override
            public void onItemClick(int position) {
                input_tv.append(circularBean.getPanList().get(position).getKeyValue());
                dismiss();
            }

            @Override
            public void onCenterClick() {
                input_tv.append(circularBean.getCenterValue());
                dismiss();
            }
        });
        ((TextView)wheelMenuView.findViewById(R.id.button)).setText(circularBean.getCenterValue());
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, circularBean.getXoff(), circularBean.getYoff());
        } else {
            this.dismiss();
        }
    }
}
