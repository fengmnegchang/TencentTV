package com.open.tencenttv;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.open.tencenttv.adapter.PersonalCenterAdapter;
import com.open.tencenttv.adapter.RecyclerViewPresenter;
import com.open.tencenttv.adapter.RecyclerViewPushPresenter;
import com.open.tencenttv.bean.PersonalCenterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 * 视频主页
 *
 * @author :fengguangjing
 * @createTime: 16/11/1
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class TVMainActivity extends CommonFragmentActivity implements RecyclerViewTV.OnItemListener {
    private List<PersonalCenterBean> data;
    private ListViewTV listView;
    /**
     * Top视频类型列表 电视+电影+
     **/
    private RecyclerViewTV mRecyclerView;
    private RecyclerViewPresenter mRecyclerViewPresenter;
    private GeneralAdapter mGeneralAdapter;

    /**
     * 推荐排行
     **/
    private RecyclerViewTV recycler_push;
    private RecyclerViewPushPresenter mRecyclerPushPresenter;
    private GeneralAdapter mRecyclerPushGeneralAdapter;

    //scrollview
    private SmoothHorizontalScrollView hscroll_view;
    private FrameMainLayout main_lay11;

    private EditText edit_search;
    private ReflectItemView item_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_main);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        hscroll_view = (SmoothHorizontalScrollView) findViewById(R.id.hscroll_view);
        main_lay11 = (FrameMainLayout) findViewById(R.id.main_lay);
        listView = (ListViewTV) findViewById(R.id.listview);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);


        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
//        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
//        mRecyclerViewBridge.setUpRectResource(R.drawable.video_cover_cursor);
//        float density = getResources().getDisplayMetrics().density;
//        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
//                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
//        mRecyclerViewBridge.setDrawUpRectPadding(receF);
        recyclerViewLinerLayout(LinearLayoutManager.HORIZONTAL);


        //推荐排行
        recycler_push = (RecyclerViewTV) findViewById(R.id.recycler_push);
        recyclerPushLinerLayout(LinearLayoutManager.HORIZONTAL);

        edit_search = (EditText) findViewById(R.id.edit_search);
        item_edit = (ReflectItemView) findViewById(R.id.item_edit);
    }

    @Override
    protected void initValue() {
        super.initValue();
        // 默认是 OpenEff...，建议使用 NoDraw... ...
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
        mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setTranDurAnimTime(200);
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距.
        initData();
        listView.setAdapter(new PersonalCenterAdapter(this,data));
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
                if (view != null) {
                    view.bringToFront();
                    mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
                    mOldView = view;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("listView item" + " ========onNothingSelected ");
            }
        });

        listView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                //失去焦点时，将子view还原
                System.out.println("listView item" + view.getId() + " ========onFocusChange " + b);
                if (!b) {
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        View mvView = listView.getChildAt(i);
                        mRecyclerViewBridge.setUnFocusView(mvView);
                    }
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    view.bringToFront();
                    mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
                    mOldView = view;
                }
                System.out.println("listView item" + (int) id + " ========onItemClick ");
                Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_LONG).show();
                if(position==0) {
                    //进入频道
                    Intent intent = new Intent();
                    intent.setClass(TVMainActivity.this, LastHistoryActivity.class);
                    startActivity(intent);
                }else if(position==1){
                    Intent intent = new Intent();
                    intent.setClass(TVMainActivity.this, SearchKeyBoardActivity.class);
                    startActivity(intent);
                }else if(position==2){
                    Intent intent = new Intent();
                    intent.setClass(TVMainActivity.this,SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });


        mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                System.out.println("mRecyclerView item" + position + " ========onItemClick ");
                //进入频道
                Intent intent = new Intent();
                intent.setClass(TVMainActivity.this,PinDaoActivity.class);
                startActivity(intent);
            }
        });


        recycler_push.setOnItemListener(this);
        // item 单击事件处理.
        recycler_push.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                System.out.println("recycler_push item" + position + " ========onItemClick ");
                //进入频道
                Intent intent = new Intent();
                intent.setClass(TVMainActivity.this,PinDaoActivity.class);
                startActivity(intent);
            }
        });
        hscroll_view.setFadingEdge((int) getDimension(R.dimen.w_100)); // 滚动窗口也需要适配.
        main_lay11.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.1f;
                mainUpView1.setFocusView(newFocus, mOldView, scale);
                mOldView = newFocus; // 4.3以下需要自己保存.
            }
        });
        for (int i = 0; i < main_lay11.getChildCount(); i++) {
            main_lay11.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
//						v.performClick();
                        v.requestFocus();
                    }
                    return false;
                }
            });
        }

        edit_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Intent intent = new Intent();
                intent.setClass(TVMainActivity.this, SearchKeyBoardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        item_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TVMainActivity.this, SearchKeyBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 横向布局的recyclerView
     */
    private void recyclerPushLinerLayout(int orientation) {
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(this);
        layoutManager.setOrientation(orientation);
        recycler_push.setLayoutManager(layoutManager);
        recycler_push.setFocusable(false);
        mRecyclerPushPresenter = new RecyclerViewPushPresenter(20);
        mRecyclerPushGeneralAdapter = new GeneralAdapter(mRecyclerPushPresenter);
        recycler_push.setAdapter(mRecyclerPushGeneralAdapter);
        recycler_push.setSelectedItemOffset(111, 111); // 测试移动间距.
        recycler_push.setPagingableListener(new RecyclerViewTV.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
//                // 加载更多测试.
////                moreHandler.removeCallbacksAndMessages(null);
//                Message msg = moreHandler.obtainMessage();
//                msg.arg1 = 10;
//                moreHandler.sendMessageDelayed(msg, 3000);
//                load_more_pb.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 横向布局的recyclerView
     */
    private void recyclerViewLinerLayout(int orientation) {
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(this);
        layoutManager.setOrientation(orientation);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
        mRecyclerViewPresenter = new RecyclerViewPresenter(20);
        mGeneralAdapter = new GeneralAdapter(mRecyclerViewPresenter);
        mRecyclerView.setAdapter(mGeneralAdapter);
        mRecyclerView.setSelectedItemOffset(111, 111); // 测试移动间距.
        mRecyclerView.setPagingableListener(new RecyclerViewTV.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
//                // 加载更多测试.
////                moreHandler.removeCallbacksAndMessages(null);
//                Message msg = moreHandler.obtainMessage();
//                msg.arg1 = 10;
//                moreHandler.sendMessageDelayed(msg, 3000);
//                load_more_pb.setVisibility(View.VISIBLE);
            }
        });
        mFocusHandler.sendEmptyMessageDelayed(10, 1000);
    }

    //    private int mSavePos = 0;
//
    Handler mFocusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mRecyclerView.setDefaultSelect(0);
        }
    };


//
//    Handler moreHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            mRecyclerViewPresenter.addDatas(msg.arg1);
//            mSavePos = mRecyclerView.getSelectPostion();
//            mRecyclerView.setOnLoadMoreComplete(); // 加载更多完毕.
//            mFocusHandler.sendEmptyMessageDelayed(10, 10); // 延时请求焦点.
//            load_more_pb.setVisibility(View.GONE);
//        }
//    };




    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        if (parent.getId() == R.id.recycler_push) {
            System.out.println("recycler_push item" + position + " ========onItemPreSelected ");
        } else if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onItemPreSelected ");
        }
        mRecyclerViewBridge.setUnFocusView(mOldView);

    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        if (parent.getId() == R.id.recycler_push) {
            System.out.println("recycler_push item" + position + " ========onItemSelected ");
        } else if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onItemSelected ");
        }
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        mOldView = itemView;

    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        if (parent.getId() == R.id.recycler_push) {
            System.out.println("recycler_push item" + position + " ========onReviseFocusFollow ");
        } else if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onReviseFocusFollow ");
        }
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        mOldView = itemView;
    }

    public void initData() {
        data = new ArrayList<PersonalCenterBean>();
        //上次观看
        PersonalCenterBean mPersonalCenterBean = new PersonalCenterBean();
        mPersonalCenterBean.setType(0);
        mPersonalCenterBean.setTypeName("上次观看");
        mPersonalCenterBean.setContent("杨澜访谈录2015");
        data.add(mPersonalCenterBean);

        //最近观看
        mPersonalCenterBean = new PersonalCenterBean();
        mPersonalCenterBean.setType(1);
        mPersonalCenterBean.setTypeName("最近观看");
        data.add(mPersonalCenterBean);

        //我的应用
        mPersonalCenterBean = new PersonalCenterBean();
        mPersonalCenterBean.setType(2);
        mPersonalCenterBean.setTypeName("我的应用");
        data.add(mPersonalCenterBean);
    }

}
