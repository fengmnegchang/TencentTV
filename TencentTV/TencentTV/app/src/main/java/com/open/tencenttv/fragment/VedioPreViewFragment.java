package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.R;
import com.open.tencenttv.widget.ExpandableTextView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ****************************************************************************************************************************************************************************
 * 频道内容 fragment
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class VedioPreViewFragment extends Fragment {
    public static final String TAG = VedioPreViewFragment.class.getSimpleName();
    private MainUpView mainUpView1;
    private View mOldView;
    private EffectNoDrawBridge mRecyclerViewBridge;
    private ExpandableTextView expand_text_view;
    public static VedioPreViewFragment newInstance( MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge){
        VedioPreViewFragment fragment = new VedioPreViewFragment();
        fragment.mainUpView1 = mainUpView1;
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vedio_preview,container,false);
        expand_text_view = (ExpandableTextView)view.findViewById(R.id.expand_text_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expand_text_view.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        expand_text_view.setText("可折叠的textview是一个很常见的功能，相信大家都在微信朋友圈体验过这种场景：朋友发的笑话都只有半截，下面是一片白色，你要展开全文之后才能知道最后结果。\n" +
                "其实这也不是什么高大上的东西，网上也有现成的例子，但是使用起来还是得稍微调整一下，最牛逼的应该就是Manabu-GT的ExpandableTextView。那么我们通过对它的学习，自己来撸一发\n" +
                "\n" +
                "项目已经发布在github上\n" +
                "\n" +
                "文／r17171709（简书作者）\n" +
                "原文链接：http://www.jianshu.com/p/317b118dd2d7\n" +
                "著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。");
    }

}
