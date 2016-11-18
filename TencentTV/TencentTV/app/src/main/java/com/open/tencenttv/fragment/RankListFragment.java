package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4ListFragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.RankAdapter;
import com.open.tencenttv.bean.CommonT;
import com.open.tencenttv.bean.RankBean;
import com.open.tencenttv.utils.UrlUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RankListFragment extends BaseV4ListFragment {
    private List<RankBean> data = new ArrayList<RankBean>();
    private RankAdapter mRankAdapter;

    public static RankListFragment newInstance(MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView){
        RankListFragment fragment = new RankListFragment();
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        fragment.mainUpView1 = mainUpView1;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_rank, container, false);
        doAsync(this,this,this);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 设置ListFragment默认的ListView，即@id/android:list
//        initData();
        mRankAdapter = new RankAdapter(getActivity(), data);
        this.setListAdapter(mRankAdapter);
        getListView().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                System.out.println("listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
//                for (Fragment fragment : (getActivity()).getSupportFragmentManager().getFragments()) {
//                    if (fragment instanceof PinDaoFragment) {
//                        ((PinDaoFragment) fragment).setPindaoName(data.get((int) l).getTypeName());
//                    }
//                }
                setSelectedFragment((int)id);
                if (view != null) {
                    view.bringToFront();
                    mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
                    mOldView = view;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public CommonT call() throws Exception {
        CommonT mCommonT = new CommonT();
        List<RankBean> list = parseRankList(UrlUtils.TENCENT_RANK_URL);
        mCommonT.setRanklist(list);
        return mCommonT;
    }

    @Override
    public void onCallback(CommonT result) {
        super.onCallback(result);
        data.clear();
        data.addAll(result.getRanklist());
        mRankAdapter.notifyDataSetChanged();
//        // 延时请求其它位置的item.
//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                getListView().requestFocusFromTouch();
//                getListView(). setSelection(0);
//            }
//        };
//        handler.sendMessageDelayed(handler.obtainMessage(), 6088);
    }

    public ArrayList<RankBean> parseRankList(String href) {
        ArrayList<RankBean> list = new ArrayList<RankBean>();
        try {
            href = makeURL(href, new HashMap<String, Object>() {
                {
                }
            });
            Log.i("url", "url = " + href);

            Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
            Element masthead = doc.select("div.rank_side").first();
            Elements liElements = masthead.select("li");

            /**
             * <div class="rank_side">
             <ul class="mod_rank_side_list">
             <li><a href="http://v.qq.com/rank/index/-1_-1_-1.html"><i class="rank_side_tri"></i>全部</a></li>
             <li class="current"><a href="http://v.qq.com/rank/detail/1_-1_-1_-1_2_-1.html" title="电影排行榜"><i class="rank_side_tri"></i>电影</a></li>
             <li><a href="http://v.qq.com/rank/detail/2_-1_-1_-1_2_-1.html" title="电视剧排行榜"><i class="rank_side_tri"></i>电视剧</a></li>
             <li><a href="http://v.qq.com/rank/detail/10_-1_-1_-1_2_-1.html" title="综艺排行榜"><i class="rank_side_tri"></i>综艺</a></li>
             <li><a href="http://v.qq.com/rank/detail/3_-1_-1_-1_2_-1.html" title="动漫排行榜"><i class="rank_side_tri"></i>动漫</a></li>
             <li><a href="http://v.qq.com/rank/detail/9_-1_-1_-1_2_-1.html" title="纪录片排行榜"><i class="rank_side_tri"></i>纪录片</a></li>
             <li><a href="http://v.qq.com/rank/detail/22_-1_-1_-1_2_-1.html" title="MV排行榜"><i class="rank_side_tri"></i>MV</a></li>
             <li><a href="http://v.qq.com/rank/detail/41_-1_-1_-1_2_-1.html" title="微讲堂排行榜"><i class="rank_side_tri"></i>微讲堂</a></li>
             <li><a href="http://v.qq.com/rank/detail/100_-1_-1_-1_2_-1.html" title="教育排行榜"><i class="rank_side_tri"></i>教育</a></li>
             </ul>
             </div>
             */
            // 解析文件
            for (int i = 0; i < liElements.size(); i++) {
                RankBean bean = new RankBean();
                try {
                    //<a href="http://v.qq.com/travel/" class="link_nav" target="_blank" _stat="new_vs_header:pop_旅游">旅游</a>
                    Element aElement = liElements.get(i).select("a").first();
                    String rankName = aElement.text();
                    String rankUrl = aElement.attr("href");
                    String title = aElement.attr("title");
                    System.out.println("i==="+i+";rankName ==="+rankName+";rankUrl=="+rankUrl+";title=="+title);
                    bean.setTitle(title);
                    bean.setRankName(rankName);
                    bean.setRankurl(rankUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void onListItemClick(ListView parent, View view,
                                int position, long id) {
        Log.d(TAG, "onListItemClick");
        Toast.makeText(getActivity(), "You have selected " + position, Toast.LENGTH_SHORT).show();
        if (view != null) {
            view.bringToFront();
            mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
            mOldView = view;
        }

        setSelectedFragment((int)id);
//        for (Fragment fragment : (getActivity()).getSupportFragmentManager().getFragments()) {
//            if (fragment instanceof PinDaoFragment) {
//                ((PinDaoFragment) fragment).setPindaoName(data.get((int) id).getTypeName());
//            }
//        }

    }

    /**
     * 选择fragment
     * @param position
     */
    private void setSelectedFragment(int position){
        RankTabHorizontalViewPagerFragment rightFragment = RankTabHorizontalViewPagerFragment.newInstance(mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_rank, rightFragment).commit();
    }

//
//    public void initData() {
//        data.clear();
//        RankBean bean = new RankBean();
//        bean.setType(0);
//        bean.setTypeName("筛选");
//        data.add(bean);
//
//        bean = new RankBean();
//        bean.setType(1);
//        bean.setTypeName("搜索");
//        data.add(bean);
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("大剧精选");
//        data.add(bean);
//
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("卫视同步");
//        data.add(bean);
//
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("抗战风云");
//        data.add(bean);
//
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("会员专享");
//        data.add(bean);
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("热门话题");
//        data.add(bean);
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("剧星专场");
//        data.add(bean);
//
//        bean = new RankBean();
//        bean.setType(2);
//        bean.setTypeName("乡里乡亲");
//        data.add(bean);
//    }

}
