package com.open.tencenttv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.mode.DefualtListPresenter;
import com.open.androidtvwidget.leanback.mode.ItemHeaderPresenter;
import com.open.androidtvwidget.leanback.mode.ItemListPresenter;
import com.open.androidtvwidget.leanback.mode.ListRow;
import com.open.androidtvwidget.leanback.mode.ListRowPresenter;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.bean.NavPopPinDaoBean;
import com.open.tencenttv.fragment.MediumDirectionViewPagerFragment;
import com.open.tencenttv.json.ListRowJson;
import com.open.tencenttv.mode.MediumListPresenter;
import com.open.tencenttv.mode.Movie;
import com.open.tencenttv.mode.TestMoviceListPresenter;
import com.open.tencenttv.utils.UrlUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/17
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MediumRecyclerviewLeanBackActivity extends CommonFragmentActivity<ListRowJson> implements RecyclerViewTV.OnItemListener {
    private Context mContext;
    private RecyclerViewTV mRecyclerView;
    private RecyclerViewBridge mRecyclerViewBridge;
    private GeneralAdapter.ViewHolder mSelectedViewHolder;
    List<ListRow> mListRows = new ArrayList<ListRow>();
    ListRowPresenter mListRowPresenter;
    private NavPopPinDaoBean parentNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_recyclerview_lean_back);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        parentNav = (NavPopPinDaoBean) getIntent().getSerializableExtra("NAV_POP_PIN_DAO_KEY");
        if(parentNav==null){
        	parentNav = new NavPopPinDaoBean();
        	parentNav.setPindaoUrl(UrlUtils.TENCENT_TV_URL);
        	parentNav.setPindaoName("电视剧");
        }
        mContext = MediumRecyclerviewLeanBackActivity.this;

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.e(TAG, "width ===" + width + ";height==" + height);

        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);

        doAsync(this, this, this);
    }

    @Override
    protected void initValue() {
        super.initValue();
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.video_cover_cursor);
        float density = getResources().getDisplayMetrics().density;
        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
        mRecyclerViewBridge.setDrawUpRectPadding(receF);

        MediumDirectionViewPagerFragment fragment = MediumDirectionViewPagerFragment.newInstance(parentNav.getPindaoUrl(),mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.lay_view_pager, fragment).commit();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setSelectedItemAtCentered(true); // 设置item在中间移动.

//        // 添加测试数据。
//        for (int i = 0; i < LeanbackTestData.MOVIE_CATEGORY.length; i++) {
//            String txt = LeanbackTestData.MOVIE_CATEGORY[i];
//            // 添加一行的数据.
//            ListRow listRow = new ListRow(txt); // 标题头.
//            List<Movie> movies = LeanbackTestData.MOVIE_ITEMS;
//            listRow.addAll(movies); // 添加列的数据.
//            listRow.setOpenPresenter(new MediumListPresenter()); // 设置列的item样式.
//            // 添加一行的数据（标题头，列的数据)
//            mListRows.add(listRow);
//        }
        // 继承 Header 和 List 可以继承 OpenPresente来重写.
        //  而横向中的item 继承 DefualtListPresenter 来重写.
        mListRowPresenter = new ListRowPresenter(mListRows,
                new ItemHeaderPresenter(),
                new ItemListPresenter());
//        mListRowPresenter.setDefaultPos(0, 0); // 设置默认选中.
        GeneralAdapter generalAdapter = new GeneralAdapter(mListRowPresenter);
        mRecyclerView.setAdapter(generalAdapter);
    }


    // 更新数据测试(更新某条数据).
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            View view = mRecyclerView.getSelectView();
            // 请求更新数据.
            ListRow listRow = mListRows.get(0);
//            listRow.setHeaderItem("改变标题头数据");
            mListRowPresenter.setDefaultPos(0, 0); // 随便设置默认，也可以用于还原位置.
            mListRowPresenter.setItems(mListRows, 0);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    };

    @Override
    public ListRowJson call() throws Exception {
    	ListRowJson mCommonT = new ListRowJson();
        ArrayList<ListRow> list = new ArrayList<ListRow>();
        try {
            // 解析网络标签
            list = parseListRow(parentNav.getPindaoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCommonT.setList(list);
        return mCommonT;
    }

    @Override
    public void onCallback(ListRowJson result) {
        super.onCallback(result);
        mListRows.clear();
        mListRows.addAll(result.getList());
        mRecyclerView.getAdapter().notifyDataSetChanged();
        // 行选中的事件.
//        mRecyclerView.setOnChildViewHolderSelectedListener(mRowSelectedListener);
        // 更新数据测试.
        handler.sendEmptyMessageDelayed(10, 666);

    }


    /**
     * //        // 添加测试数据。
     * //        for (int i = 0; i < LeanbackTestData.MOVIE_CATEGORY.length; i++) {
     * //            String txt = LeanbackTestData.MOVIE_CATEGORY[i];
     * //            // 添加一行的数据.
     * //            ListRow listRow = new ListRow(txt); // 标题头.
     * //            List<Movie> movies = LeanbackTestData.MOVIE_ITEMS;
     * //            listRow.addAll(movies); // 添加列的数据.
     * //            listRow.setOpenPresenter(new MediumListPresenter()); // 设置列的item样式.
     * //            // 添加一行的数据（标题头，列的数据)
     * //            mListRows.add(listRow);
     * //        }
     *
     * @param href
     * @return
     */
    public ArrayList<ListRow> parseListRow(String href) {
        ArrayList<ListRow> list = new ArrayList<ListRow>();
        try {
            href = makeURL(href, new HashMap<String, Object>() {
                {
                }
            });
            Log.i(TAG, "url = " + href);

            Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
            Element masthead = doc.select("div.container_inner").first();
            Elements wrapperElements = masthead.select("div.wrapper");

            /**
             */
            // 解析文件
            if (wrapperElements != null && wrapperElements.size() > 1) {
                for (int i = 1; i < wrapperElements.size(); i++) {
                    String rowtext = "";
                    ListRow listrow = null;
                    try {
                        //获取分类标题
                        Element rowElement = wrapperElements.get(i).select("div.mod_title").first();
                        if (rowElement != null) {
                            rowtext = rowElement.select("h2").first().text();
                            Log.i(TAG,"rowtext ===" + rowtext);
                            listrow = new ListRow(rowtext);
                            listrow.setOpenPresenter(new MediumListPresenter()); // 设置列的item样式.
                        } else {
                            listrow = new ListRow("广告");
                            listrow.setOpenPresenter(new MediumListPresenter()); // 设置列的item样式.
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //添加数据
                    try {
                        Elements list_itemElements = wrapperElements.get(i).select("li.list_item");
                        if (list_itemElements != null && list_itemElements.size() > 0) {
                            List<Movie> movies = new ArrayList<Movie>();
                            for (int y = 0; y < list_itemElements.size(); y++) {
                                Movie movie = new Movie();
                                /***
                                 * <li class="list_item">
                                 <a href="http://v.qq.com/cover/8/8479ahinkqm7czh.html" target="_blank" class="figure" tabindex="-1" title="锦绣未央" _hot="tv.global.small_img1">
                                 <img lz_src="http://puui.qpic.cn/tv/0/5793858_175100/0" alt="锦绣未央" onerror="picerr(this,16)">
                                 <span class="figure_mask"><em class="mask_txt">更新至11集</em></span>
                                 </a>
                                 <strong class="figure_title"><a href="http://v.qq.com/cover/8/8479ahinkqm7czh.html" target="_blank" title="锦绣未央" _hot="tv.global.small_img1">锦绣未央</a></strong>
                                 <p class="figure_desc">唐嫣KO李氏兄妹绝处逢生</p>
                                 </li>
                                 */

                                //标题，链接
                                try {
                                    Element aElement = list_itemElements.get(y).select("a").first();
                                    String hrefurl = aElement.attr("href");
                                    String title = aElement.attr("title");
                                    Log.i(TAG,"hrefurl ===" + hrefurl + ";title==" + title);
                                    movie.setmTitle(title);
                                    movie.setHrefurl(hrefurl);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //图片
                                try {
                                    Element imgElement = list_itemElements.get(y).select("img").first();
                                    String lz_srcurl = "";
                                    lz_srcurl = imgElement.attr("lz_src");
                                    if(lz_srcurl==null || lz_srcurl.length()==0){
                                        lz_srcurl = imgElement.attr("src");
                                    }
                                    Log.i(TAG,"lz_srcurl ===" + lz_srcurl);
                                    movie.setLz_srcurl(lz_srcurl);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //更新至11集
                                try {
                                    Element spanElement = list_itemElements.get(y).select("span.figure_mask").first();
                                    String figure_mask = spanElement.text();
                                    Log.i(TAG,"figure_mask ===" + figure_mask);
                                    movie.setFigure_mask(figure_mask);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //唐嫣KO李氏兄妹绝处逢生
                                try {
                                    Element pElement = list_itemElements.get(y).select("p.figure_desc").first();
                                    String figure_desc = pElement.text();
                                    Log.i(TAG,"figure_desc ===" + figure_desc);
                                    movie.setFigure_desc(figure_desc);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.i(TAG,"\n");
                                movies.add(movie);
                            }
                            listrow.addAll(movies); // 添加列的数据.
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    list.add(listrow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 排除 Leanback demo的RecyclerView.
     */
    private boolean isListRowPresenter() {
        GeneralAdapter generalAdapter = (GeneralAdapter) mRecyclerView.getAdapter();
        OpenPresenter openPresenter = generalAdapter.getPresenter();
        return (openPresenter instanceof ListRowPresenter);
    }

    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        if (!isListRowPresenter()) {
            mRecyclerViewBridge.setUnFocusView(mOldView);
        }
    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        if (!isListRowPresenter()) {
            mRecyclerViewBridge.setFocusView(itemView, 1.2f);
            mOldView = itemView;
        }
    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        if (!isListRowPresenter()) {
            mRecyclerViewBridge.setFocusView(itemView, 1.2f);
            mOldView = itemView;
        }
    }

    private final RecyclerViewTV.OnChildViewHolderSelectedListener mRowSelectedListener =
            new RecyclerViewTV.OnChildViewHolderSelectedListener() {
                @Override
                public void onChildViewHolderSelected(RecyclerView parent,
                                                      RecyclerView.ViewHolder viewHolder, int position) {
                    onRowSelected(parent, viewHolder, position, -1);
                }
            };


    /**
     * 一行选中.
     */
    private void onRowSelected(RecyclerView parent, RecyclerView.ViewHolder viewHolder,
                               int position, int subposition) {
        if (mSelectedViewHolder != viewHolder) {
            OPENLOG.D("pos:" + position + " vh:" + viewHolder);
            // 先清除 MselectedViewHolder 的一行选中颜色.
            if (mSelectedViewHolder != null) {
                setRowViewSelected(mSelectedViewHolder, false);
            }
            // 设置当前选中的 一行的选中颜色.
            mSelectedViewHolder = (GeneralAdapter.ViewHolder) viewHolder;
            if (mSelectedViewHolder != null) {
                setRowViewSelected(mSelectedViewHolder, true);
            }
        }
    }

    /**
     * 改变一行的颜色.这里只是DEMO，你可以改变一行的图片哈，或者背景颜色哈.
     * 具体可以看Leanback的android demo.
     */
    private void setRowViewSelected(GeneralAdapter.ViewHolder viewHolder, boolean selected) {
        if (isListRowPresenter()) {
            try {
                ListRowPresenter.ListRowViewHolder listRowPresenter = (ListRowPresenter.ListRowViewHolder) viewHolder.getViewHolder();
                ItemListPresenter.ItemListViewHolder itemListViewHolder = (ItemListPresenter.ItemListViewHolder) listRowPresenter.getListViewHolder();
                DefualtListPresenter defualtListPresenter = itemListViewHolder.getDefualtListPresenter();
                if (defualtListPresenter instanceof TestMoviceListPresenter) {
                    TestMoviceListPresenter testMoviceListPresenter = (TestMoviceListPresenter) defualtListPresenter;
                    testMoviceListPresenter.setSelect(selected);
                    RecyclerViewTV recyclerViewTV = itemListViewHolder.getRecyclerViewTV();
                    int count = recyclerViewTV.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View view = recyclerViewTV.getChildAt(i);
                        if (selected) {
                            view.setAlpha(0.5f);
                        } else {
                            view.setAlpha(1.0f);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
