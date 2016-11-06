package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.DramaAdapter;
import com.open.tencenttv.adapter.DramaCountAdapter;
import com.open.tencenttv.bean.DramaBean;
import com.open.tencenttv.bean.DramaCountBean;
import com.open.tencenttv.widget.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 * 频道内容 fragment
 *
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
    private ExpandableTextView expand_text_view;//可折叠textview


    //剧集分页处理
    private ListViewTV listview_drama_pager;
    private List<DramaBean> drama_pager_list;
    private DramaAdapter pagerDramaAdapter;

    private ListViewTV listview_drama_count;
    private DramaCountAdapter countDramaAdapter;
    private List<DramaCountBean> drama_count_list;

    public static  final int PAGER_COUNT = 20;

    public static VedioPreViewFragment newInstance(MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
        VedioPreViewFragment fragment = new VedioPreViewFragment();
        fragment.mainUpView1 = mainUpView1;
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vedio_preview, container, false);
        expand_text_view = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
        listview_drama_pager = (ListViewTV) view.findViewById(R.id.listview_drama_pager);
        listview_drama_count = (ListViewTV) view.findViewById(R.id.listview_drama_count);

        initData();
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

        pagerDramaAdapter = new DramaAdapter(getActivity(), drama_pager_list);
        countDramaAdapter = new DramaCountAdapter(getActivity(), drama_count_list);

        listview_drama_pager.setAdapter(pagerDramaAdapter);
        listview_drama_count.setAdapter(countDramaAdapter);


        //第几页
        listview_drama_pager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drama_count_list.clear();;
                drama_count_list.addAll(drama_pager_list.get((int)id).getCountBeanList());
                countDramaAdapter.notifyDataSetChanged();
                System.out.println("listview_drama_pager item" + view.getId() + " ========setOnItemSelectedListener " + id);
                if (view != null) {
                    mainUpView1.setFocusView(view, mOldView, 1.2f);
                }
                mOldView = view;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listview_drama_pager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drama_count_list.clear();;
                drama_count_list.addAll(drama_pager_list.get((int)id).getCountBeanList());
                countDramaAdapter.notifyDataSetChanged();
                System.out.println("listview_drama_pager item" + view.getId() + " ========setOnItemClickListener " + id);
            }
        });


        //第几集
        listview_drama_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("listview_drama_count item" + view.getId() + " ========setOnItemSelectedListener " + id);
                if (view != null) {
                    mainUpView1.setFocusView(view, mOldView, 1.2f);
                }
                mOldView = view;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listview_drama_count.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("listview_drama_count item" + view.getId() + " ========setOnItemClickListener " + id);
            }
        });

        FragmentManager manager = getActivity().getSupportFragmentManager();
        LikeDramaFragment likeDramaFragment = LikeDramaFragment.newInstance(mainUpView1,mOldView,mRecyclerViewBridge);
        manager.beginTransaction().replace(R.id.layout_you_like_drama, likeDramaFragment).commit();

    }


    protected void initData() {
        drama_pager_list = new ArrayList<DramaBean>();
        drama_count_list = new ArrayList<DramaCountBean>();
        DramaBean bean;
        DramaCountBean countBean;
        for (int i = 0; i < 5; i++) {
            bean = new DramaBean();
            bean.setDramaType(0);
            bean.setPagerCount(PAGER_COUNT);
            bean.setDramaName("" + (PAGER_COUNT * i + 1) + "-" + PAGER_COUNT * (i + 1));

            if (i == 0) {
                for (int y = 0; y < PAGER_COUNT; y++) {
                    countBean = new DramaCountBean();
                    countBean.setDramaType(1);
                    countBean.setDramaName("第" + (PAGER_COUNT * i + y) + "集");
                    drama_count_list.add(countBean);
                }
            }
            List<DramaCountBean> drama_count_temp_list = new ArrayList<DramaCountBean>();
            for (int y = 0; y < PAGER_COUNT; y++) {
                countBean = new DramaCountBean();
                countBean.setDramaType(1);
                countBean.setDramaName("第" + (PAGER_COUNT * i + y) + "集");
                drama_count_temp_list.add(countBean);
            }
            bean.setCountBeanList(drama_count_temp_list);
            drama_pager_list.add(bean);
        }

    }
}
