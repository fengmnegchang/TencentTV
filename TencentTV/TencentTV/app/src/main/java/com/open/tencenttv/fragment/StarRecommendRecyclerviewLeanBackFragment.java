/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7上午10:34:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
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
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.json.ListRowJson;
import com.open.tencenttv.mode.MediumListPresenter;
import com.open.tencenttv.mode.Movie;
import com.open.tencenttv.mode.TestMoviceListPresenter;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 明星 推荐
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7上午10:34:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarRecommendRecyclerviewLeanBackFragment extends BaseV4Fragment<ListRowJson> implements RecyclerViewTV.OnItemListener {

	private RecyclerViewTV mRecyclerView;
	private GeneralAdapter.ViewHolder mSelectedViewHolder;
	private List<ListRow> mListRows = new ArrayList<ListRow>();
	private ListRowPresenter mListRowPresenter;
	private String url;

	public static StarRecommendRecyclerviewLeanBackFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		StarRecommendRecyclerviewLeanBackFragment fragment = new StarRecommendRecyclerviewLeanBackFragment();
		fragment.url = url;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		fragment.mainUpView1 = mainUpView1;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_star_recommend_recyclerview_lean_back, container, false);
		mRecyclerView = (RecyclerViewTV) view.findViewById(R.id.recyclerView);
		doAsync(this, this, this);
		return view;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
            list = parseListRow(url);
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
            Element masthead = doc.select("div.video_tab").first();
            Elements h2Elements = masthead.select("h2");
            Elements mod_figuresElements = masthead.select("div.mod_figures");
            
            if(h2Elements!=null && h2Elements.size()>0){
            	for (int i = 0; i < h2Elements.size(); i++) {
                    String rowtext = "";
                    ListRow listrow = null;
                    try {
                        //获取分类标题
                        Element rowElement = h2Elements.get(i).select("h2").first();
                        if (rowElement != null) {
                            rowtext = rowElement.select("h2").first().text();
                            Log.i(TAG,"rowtext ===" + rowtext);
                            listrow = new ListRow(rowtext);
                            listrow.setOpenPresenter(new MediumListPresenter()); // 设置列的item样式.
                        }  
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                  //添加数据
                    try {
                        Elements list_itemElements = mod_figuresElements.get(i).select("li.feed_item");
                        if (list_itemElements != null && list_itemElements.size() > 0) {
                            List<Movie> movies = new ArrayList<Movie>();
                            for (int y = 0; y < list_itemElements.size(); y++) {
                                Movie movie = new Movie();
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
            						String r_lazyload = imgElement.attr("r-lazyload");
            						String feed_item_title = imgElement.attr("alt");
            						if(feed_item_title!=null){
            							 movie.setmTitle(feed_item_title);
            						}
            						if(r_lazyload==null){
            							r_lazyload = imgElement.attr("src");
            						}
            						if(r_lazyload==null){
            							r_lazyload = imgElement.attr("data-src");
            						}
            						Log.i(TAG, "i==" + i + ";r_lazyload==" + r_lazyload);
                                     movie.setLz_srcurl(r_lazyload);
            					} catch (Exception e) {
            						e.printStackTrace();
            					}
                                
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
