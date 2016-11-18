package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.RankFragmentAdapter;
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
 * 播放排行榜
 *
 * @author :fengguangjing
 * @createTime: 16/11/18
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RankFragment extends BaseV4Fragment {
    private String rankname;
    private List<RankBean> data = new ArrayList<RankBean>();
    private ListViewTV mListViewTV;
    private RankFragmentAdapter mAdapter;

    public static RankFragment newInstance(String rankname, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
        RankFragment fragment = new RankFragment();
        fragment.rankname = rankname;
        fragment.mainUpView1 = mainUpView1;
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        mListViewTV = (ListViewTV) view.findViewById(R.id.listview);
        doAsync(this, this, this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new RankFragmentAdapter(getActivity(), data);
        mListViewTV.setAdapter(mAdapter);
    }

    @Override
    public CommonT call() throws Exception {
        CommonT mCommonT = new CommonT();
        List<RankBean> list = parseRankList(UrlUtils.TENCENT_RANK_URL);
        mCommonT.setPlaylist(list);
        return mCommonT;
    }

    @Override
    public void onCallback(CommonT result) {
        super.onCallback(result);
        data.clear();
        data.addAll(result.getPlaylist());
        mAdapter.notifyDataSetChanged();
//        // 延时请求其它位置的item.
//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                mListViewTV.requestFocusFromTouch();
//                mListViewTV.setSelection(0);
//            }
//        };
//        handler.sendMessageDelayed(handler.obtainMessage(), 188);
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
            Element masthead = doc.select("ul.mod_rankbox_con_list").first();
            Elements liElements = masthead.select("li");

            /**
             * <li>
             <span class="mod_rankbox_con_list_index top_3">01</span>
             <span class="mod_rankbox_con_item_title"><a href="http://v.qq.com/cover/b/bnc0cvuskzxnrcl.html" target="_blank" title="可可小爱公益动画">可可小爱公益动画</a></span>
             <span class="mod_rankbox_con_item_actor">
             儿童国学 儿童益智 </span>
             <span class="mod_rankbox_con_item_impor">&nbsp;</span>
             <!--<span class="mod_rankbox_con_list_click"><strong class="num">780.9万</strong></span> -->
             <span class="mod_rankbox_con_list_click"><strong class="num">780.8万</strong></span>
             </li>
             */
            // 解析文件
            for (int i = 0; i < liElements.size(); i++) {
                RankBean bean = new RankBean();
                try {

                    try {
                        Element spanindexElement = liElements.get(i).select("span.mod_rankbox_con_list_index").first();
                        String count = spanindexElement.text();
                        System.out.print("i===" + i + ";count ===" + count);
                        bean.setCount(count);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        Element spantitleElement = liElements.get(i).select("span.mod_rankbox_con_item_title").first();
                        String title = spantitleElement.text();
                        System.out.print(";title ===" + title);
                        bean.setActorName(title);

                        Element aElement = spantitleElement.select("a").first();
                        String hrefurl = aElement.attr("href");
                        System.out.print(";hrefurl ===" + hrefurl);
                        bean.setRankurl(hrefurl);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Element spanactorElement = liElements.get(i).select("span.mod_rankbox_con_item_actor").first();
                        String actortype = spanactorElement.text();
                        System.out.print(";actortype ===" + actortype);
                        bean.setActorType(actortype);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Element spanclickElement = liElements.get(i).select("span.mod_rankbox_con_list_click").first();
                        String playtimes = spanclickElement.text();
                        System.out.print(";playtimes ===" + playtimes);
                        bean.setPlayTimes(playtimes);
                        System.out.println("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

}
