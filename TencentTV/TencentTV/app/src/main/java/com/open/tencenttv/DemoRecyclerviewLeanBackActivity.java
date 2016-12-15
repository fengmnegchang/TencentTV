package com.open.tencenttv;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.mode.LeanbackTestData;
import com.open.tencenttv.mode.Movie;
import com.open.tencenttv.mode.TestMoviceListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerview Demo.
 * setSelectedItemAtCentered 设置一直在中间. (如果设置 false，那么请使用setSelectedItemOffset来设置相差的边距)
 *
 * @author hailongqiu
 */
public class DemoRecyclerviewLeanBackActivity extends Activity implements RecyclerViewTV.OnItemListener {
    public static final String TAG = DemoRecyclerviewLeanBackActivity.class.getSimpleName();
    private Context mContext;
    private RecyclerViewTV mRecyclerView;
    private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;
    private View load_more_pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_recyclerview_lean_back_activity);
        mContext = DemoRecyclerviewLeanBackActivity.this;
        load_more_pb = findViewById(R.id.load_more_pb);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.e(TAG,"width ==="+width+";height=="+height);

        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.video_cover_cursor);
        float density = getResources().getDisplayMetrics().density;
        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
        mRecyclerViewBridge.setDrawUpRectPadding(receF);
        //  初始化带标题头的demo.
//        testHeaderGridLayout();
        testLeanbackDemo();
//        testRecyclerViewLinerLayout(RecyclerView.HORIZONTAL);
        //
        mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
            }
        });
    }


    ///////////////////////////////////////////////////////////////
    ////   Leanback 测试 Demo start ---->
    ///////////////////////////////////////////////////////////////

    private final RecyclerViewTV.OnChildViewHolderSelectedListener mRowSelectedListener =
            new RecyclerViewTV.OnChildViewHolderSelectedListener() {
                @Override
                public void onChildViewHolderSelected(RecyclerView parent,
                                                      RecyclerView.ViewHolder viewHolder, int position) {
                    onRowSelected(parent, viewHolder, position, -1);
                }
            };

    private GeneralAdapter.ViewHolder mSelectedViewHolder;
    List<ListRow> mListRows = new ArrayList<ListRow>();
    ListRowPresenter mListRowPresenter;

    /**
     * 一行选中.
     */
    private void onRowSelected(RecyclerView parent, RecyclerView.ViewHolder viewHolder,
                               int position, int subposition) {
        if (mSelectedViewHolder != viewHolder) {
//            OPENLOG.D("pos:" + position + " vh:" + viewHolder);
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
                    //
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

    /**
     * Leanback Demo.
     */
    private void testLeanbackDemo() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setSelectedItemAtCentered(true); // 设置item在中间移动.
        // 添加测试数据。
        for (int i = 0; i < LeanbackTestData.MOVIE_CATEGORY.length; i++) {
            String txt = LeanbackTestData.MOVIE_CATEGORY[i];
            // 添加一行的数据.
            ListRow listRow = new ListRow(txt); // 标题头.
            List<Movie> movies = LeanbackTestData.MOVIE_ITEMS;
            if (i % 2 == 1)
                movies = LeanbackTestData.MOVIE_ITEMS2;
            listRow.addAll(movies); // 添加列的数据.
            listRow.setOpenPresenter(new TestMoviceListPresenter()); // 设置列的item样式.
            // 添加一行的数据（标题头，列的数据)
            mListRows.add(listRow);
        }
        // 添加最后一行数据.
        ListRow lastListRow = new ListRow("设置");
        lastListRow.add("网络wifi");
        lastListRow.add("声音");
        lastListRow.add("声音");
        lastListRow.setOpenPresenter(new DefualtListPresenter());
        mListRows.add(lastListRow);
        // 测试demo, 一般你想要自己的效果，
        // 继承 Header 和 List 可以继承 OpenPresente来重写.
        //  而横向中的item 继承 DefualtListPresenter 来重写.
        mListRowPresenter = new ListRowPresenter(mListRows,
                new ItemHeaderPresenter(),
                new ItemListPresenter());
        mListRowPresenter.setDefaultPos(0, 2); // 设置默认选中.
        GeneralAdapter generalAdapter = new GeneralAdapter(mListRowPresenter);
        mRecyclerView.setAdapter(generalAdapter);
        // 行选中的事件.
//        mRecyclerView.setOnChildViewHolderSelectedListener(mRowSelectedListener);
        // 更新数据测试.
        handler.sendEmptyMessageDelayed(10, 6666);
    }

    // 更新数据测试(更新某条数据).
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            View view = mRecyclerView.getSelectView();
            // 请求更新数据.
            ListRow listRow = mListRows.get(0);
            listRow.setHeaderItem("改变标题头数据");
            mListRowPresenter.setDefaultPos(0, 5); // 随便设置默认，也可以用于还原位置.
            mListRowPresenter.setItems(mListRows, 0);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    };

    ///////////////////////////////////////////////////////////////
    ////   Leanback 测试 Demo end ---->
    ///////////////////////////////////////////////////////////////

    public float getDimension(int id) {
        return getResources().getDimension(id);
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
            mRecyclerViewBridge.setUnFocusView(oldView);
        }
    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        if (!isListRowPresenter()) {
            mRecyclerViewBridge.setFocusView(itemView, 1.2f);
            oldView = itemView;
        }
    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        if (!isListRowPresenter()) {
            mRecyclerViewBridge.setFocusView(itemView, 1.2f);
            oldView = itemView;
        }
    }

}
