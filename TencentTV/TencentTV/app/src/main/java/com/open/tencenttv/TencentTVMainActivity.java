package com.open.tencenttv;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.open.tencenttv.adapter.RecyclerViewPresenter;
import com.open.tencenttv.bean.CommonT;
import com.open.tencenttv.bean.NavPopPinDaoBean;
import com.open.tencenttv.fragment.LastHistoryStickYGridHeaderFragment;
import com.open.tencenttv.fragment.NewFeatureStickYGridHeaderFragment;
import com.open.tencenttv.utils.UrlUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

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
public class TencentTVMainActivity extends CommonFragmentActivity implements RecyclerViewTV.OnItemListener,NewFeatureStickYGridHeaderFragment.Callbacks  {
//    private List<PersonalCenterBean> data;
//    private ListViewTV listView;
    /**
     * Top视频类型列表 电视+电影+
     **/
    private RecyclerViewTV mRecyclerView;
    private RecyclerViewPresenter mRecyclerViewPresenter;
    private GeneralAdapter mGeneralAdapter;
    private ArrayList<NavPopPinDaoBean> navpoplist = new ArrayList<NavPopPinDaoBean>();
//    /**
//     * 推荐排行
//     **/
//    private RecyclerViewTV recycler_push;
//    private RecyclerViewPushPresenter mRecyclerPushPresenter;
//    private GeneralAdapter mRecyclerPushGeneralAdapter;


    //scrollview
    private SmoothHorizontalScrollView hscroll_view;
    private FrameMainLayout main_lay11;

    private EditText edit_search;
    private ReflectItemView item_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_tv_main);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        hscroll_view = (SmoothHorizontalScrollView) findViewById(R.id.hscroll_view);
        main_lay11 = (FrameMainLayout) findViewById(R.id.main_lay);
//        listView = (ListViewTV) findViewById(R.id.listview);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);


        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
//        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
//        mRecyclerViewBridge.setUpRectResource(R.drawable.video_cover_cursor);
//        float density = getResources().getDisplayMetrics().density;
//        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
//                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
//        mRecyclerViewBridge.setDrawUpRectPadding(receF);



//        //推荐排行
//        recycler_push = (RecyclerViewTV) findViewById(R.id.recycler_push);
//        recyclerPushLinerLayout(LinearLayoutManager.HORIZONTAL);

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
//        initData();
//        listView.setAdapter(new PersonalCenterAdapter(this,data));

        FragmentManager manager = getSupportFragmentManager();
        NewFeatureStickYGridHeaderFragment fragment = NewFeatureStickYGridHeaderFragment.newInstance(mainUpView1,mOldView,mRecyclerViewBridge);
        manager.beginTransaction().replace(R.id.lay_tuijian, fragment).commit();

        doAsync(this, this, this);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
//                if (view != null) {
//                    view.bringToFront();
//                    mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
//                    mOldView = view;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                System.out.println("listView item" + " ========onNothingSelected ");
//            }
//        });
//
//        listView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                //失去焦点时，将子view还原
//                System.out.println("listView item" + view.getId() + " ========onFocusChange " + b);
//                if (!b) {
//                    for (int i = 0; i < listView.getChildCount(); i++) {
//                        View mvView = listView.getChildAt(i);
//                        mRecyclerViewBridge.setUnFocusView(mvView);
//                    }
//                }
//
//            }
//        });
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (view != null) {
//                    view.bringToFront();
//                    mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
//                    mOldView = view;
//                }
//                System.out.println("listView item" + (int) id + " ========onItemClick ");
//                Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_LONG).show();
//                if(position==0) {
//                    //进入频道
//                    Intent intent = new Intent();
//                    intent.setClass(TencentTVMainActivity.this, LastHistoryActivity.class);
//                    startActivity(intent);
//                }else if(position==1){
//                    Intent intent = new Intent();
//                    intent.setClass(TencentTVMainActivity.this, SearchKeyBoardActivity.class);
//                    startActivity(intent);
//                }else if(position==2){
//                    Intent intent = new Intent();
//                    intent.setClass(TencentTVMainActivity.this,SettingsActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });


        mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                System.out.println("mRecyclerView item" + position + " ========onItemClick ");
                //进入频道
                Intent intent = new Intent();
                intent.setClass(TencentTVMainActivity.this,MediumRecyclerviewLeanBackActivity.class);
                startActivity(intent);
            }
        });

//
//        recycler_push.setOnItemListener(this);
//        // item 单击事件处理.
//        recycler_push.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//                System.out.println("recycler_push item" + position + " ========onItemClick ");
//                //进入频道
//                Intent intent = new Intent();
//                intent.setClass(TencentTVMainActivity.this,PinDaoActivity.class);
//                startActivity(intent);
//            }
//        });
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
                intent.setClass(TencentTVMainActivity.this, SearchKeyBoardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        item_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TencentTVMainActivity.this, SearchKeyBoardActivity.class);
                startActivity(intent);
            }
        });
    }

//    /**
//     * 横向布局的recyclerView
//     */
//    private void recyclerPushLinerLayout(int orientation) {
//        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(this);
//        layoutManager.setOrientation(orientation);
//        recycler_push.setLayoutManager(layoutManager);
//        recycler_push.setFocusable(false);
//        mRecyclerPushPresenter = new RecyclerViewPushPresenter(20);
//        mRecyclerPushGeneralAdapter = new GeneralAdapter(mRecyclerPushPresenter);
//        recycler_push.setAdapter(mRecyclerPushGeneralAdapter);
//        recycler_push.setSelectedItemOffset(111, 111); // 测试移动间距.
//        recycler_push.setPagingableListener(new RecyclerViewTV.PagingableListener() {
//            @Override
//            public void onLoadMoreItems() {
////                // 加载更多测试.
//////                moreHandler.removeCallbacksAndMessages(null);
////                Message msg = moreHandler.obtainMessage();
////                msg.arg1 = 10;
////                moreHandler.sendMessageDelayed(msg, 3000);
////                load_more_pb.setVisibility(View.VISIBLE);
//            }
//        });
//    }


    /**
     * 横向布局的recyclerView
     */
    private void recyclerViewLinerLayout(int orientation) {
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(this);
        layoutManager.setOrientation(orientation);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
        mRecyclerViewPresenter = new RecyclerViewPresenter(navpoplist);
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
//        if (parent.getId() == R.id.recycler_push) {
//            System.out.println("recycler_push item" + position + " ========onItemPreSelected ");
//        } else
//
        if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onItemPreSelected ");
        }
        mRecyclerViewBridge.setUnFocusView(mOldView);

    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
//        if (parent.getId() == R.id.recycler_push) {
//            System.out.println("recycler_push item" + position + " ========onItemSelected ");
//        } else

        if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onItemSelected ");
        }
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        mOldView = itemView;

    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
//        if (parent.getId() == R.id.recycler_push) {
//            System.out.println("recycler_push item" + position + " ========onReviseFocusFollow ");
//        } else

        if (parent.getId() == R.id.recyclerView) {
            System.out.println("mRecyclerView item" + position + " ========onReviseFocusFollow ");
        }
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        mOldView = itemView;
    }

//    public void initData() {
//        data = new ArrayList<PersonalCenterBean>();
//        //上次观看
//        PersonalCenterBean mPersonalCenterBean = new PersonalCenterBean();
//        mPersonalCenterBean.setType(0);
//        mPersonalCenterBean.setTypeName("上次观看");
//        mPersonalCenterBean.setContent("杨澜访谈录2015");
//        data.add(mPersonalCenterBean);
//
//        //最近观看
//        mPersonalCenterBean = new PersonalCenterBean();
//        mPersonalCenterBean.setType(1);
//        mPersonalCenterBean.setTypeName("最近观看");
//        data.add(mPersonalCenterBean);
//
//        //我的应用
//        mPersonalCenterBean = new PersonalCenterBean();
//        mPersonalCenterBean.setType(2);
//        mPersonalCenterBean.setTypeName("我的应用");
//        data.add(mPersonalCenterBean);
//    }

    @Override
	public CommonT call() throws Exception {
		// TODO Auto-generated method stub
		CommonT mCommonT = new CommonT();
		ArrayList<NavPopPinDaoBean> list = new ArrayList<NavPopPinDaoBean>();
		try {
			// 解析网络标签
			list = parseNavPopList(UrlUtils.TENCENT_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCommonT.setNavpoplist(list);
		return mCommonT;
	}

	@Override
	public void onCallback(CommonT result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
        navpoplist.addAll(result.getNavpoplist());
        recyclerViewLinerLayout(LinearLayoutManager.HORIZONTAL);
        mGeneralAdapter.notifyDataSetChanged();
	}


    public ArrayList<NavPopPinDaoBean> parseNavPopList(String href) {
		ArrayList<NavPopPinDaoBean> list = new ArrayList<NavPopPinDaoBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i("url", "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("div.nav_pop_content").first();
			Elements beanElements = masthead.select("a");

			/**
             *
             *  <div class="nav_more_pop">
             <div class="nav_pop_inner">
             <i class="triangle_up"><i class="triangle_inner"></i></i>
             <div class="nav_pop_content">

             <a href="http://v.qq.com/tv/" class="link_nav" target="_blank" _stat="new_vs_header:pop_电视剧">电视剧</a>

             <a href="http://v.qq.com/variety/" class="link_nav" target="_blank" _stat="new_vs_header:pop_综艺">综艺</a>

             <a href="http://v.qq.com/movie/" class="link_nav" target="_blank" _stat="new_vs_header:pop_电影">电影</a>

             <a href="http://film.qq.com/" class="link_nav" target="_blank" _stat="new_vs_header:pop_VIP影院">VIP影院</a>

             <a href="http://v.qq.com/cartoon/" class="link_nav" target="_blank" _stat="new_vs_header:pop_动漫">动漫</a>

             <a href="http://v.qq.com/child/" class="link_nav" target="_blank" _stat="new_vs_header:pop_少儿">少儿</a>

             <a href="http://v.qq.com/ent/" class="link_nav" target="_blank" _stat="new_vs_header:pop_娱乐">娱乐</a>

             <a href="http://v.qq.com/music/" class="link_nav" target="_blank" _stat="new_vs_header:pop_音乐">音乐</a>

             <a href="http://v.qq.com/livemusic/" class="link_nav" target="_blank" _stat="new_vs_header:pop_演唱会">演唱会</a>

             <a href="http://v.qq.com/tv/yingmei/" class="link_nav" target="_blank" _stat="new_vs_header:pop_美剧">美剧</a>

             <a href="http://v.qq.com/tv/korea/" class="link_nav" target="_blank" _stat="new_vs_header:pop_韩剧">韩剧</a>

             <a href="http://v.qq.com/doco/" class="link_nav" target="_blank" _stat="new_vs_header:pop_纪录片">纪录片</a>

             <a href="http://v.qq.com/dv/" class="link_nav" target="_blank" _stat="new_vs_header:pop_微电影">微电影</a>

             <a href="http://v.qq.com/news/" class="link_nav" target="_blank" _stat="new_vs_header:pop_新闻">新闻</a>

             <a href="http://v.qq.com/sports/" class="link_nav" target="_blank" _stat="new_vs_header:pop_体育">体育</a>

             <a href="http://sports.qq.com/nbavideo/" class="link_nav" target="_blank" _stat="new_vs_header:pop_NBA">NBA</a>

             <a href="http://v.qq.com/fun/" class="link_nav" target="_blank" _stat="new_vs_header:pop_搞笑">搞笑</a>

             <a href="http://v.qq.com/games/" class="link_nav" target="_blank" _stat="new_vs_header:pop_游戏">游戏</a>

             <a href="http://v.qq.com/videoplus/" class="link_nav" target="_blank" _stat="new_vs_header:pop_原创">原创</a>

             <a href="http://v.qq.com/fashion/" class="link_nav" target="_blank" _stat="new_vs_header:pop_时尚">时尚</a>

             <a href="http://v.qq.com/life/" class="link_nav" target="_blank" _stat="new_vs_header:pop_生活">生活</a>

             <a href="http://paike.qq.com/" class="link_nav" target="_blank" _stat="new_vs_header:pop_拍客">拍客</a>

             <a href="http://v.qq.com/auto/" class="link_nav" target="_blank" _stat="new_vs_header:pop_汽车">汽车</a>

             <a href="http://v.qq.com/tech/" class="link_nav" target="_blank" _stat="new_vs_header:pop_科技">科技</a>

             <a href="http://v.qq.com/finance/" class="link_nav" target="_blank" _stat="new_vs_header:pop_财经">财经</a>

             <a href="http://v.qq.com/program/" class="link_nav" target="_blank" _stat="new_vs_header:pop_腾讯出品">腾讯出品</a>

             <a href="http://v.qq.com/house/" class="link_nav" target="_blank" _stat="new_vs_header:pop_房产">房产</a>

             <a href="http://v.qq.com/x/live/index.html" class="link_nav" target="_blank" _stat="new_vs_header:pop_直播">直播</a>

             <a href="http://yingping.qq.com/?source=1" class="link_nav" target="_blank" _stat="new_vs_header:pop_影评">影评</a>

             <a href="http://class.qq.com/" class="link_nav" target="_blank" _stat="new_vs_header:pop_精品课">精品课</a>

             <a href="http://v.qq.com/baby/" class="link_nav" target="_blank" _stat="new_vs_header:pop_母婴">母婴</a>

             <a href="http://v.qq.com/travel/" class="link_nav" target="_blank" _stat="new_vs_header:pop_旅游">旅游</a>

             </div>
             </div>
             </div>
             </div>
             </div>
			 */
			// 解析文件
			for (int i = 0; i < beanElements.size(); i++) {
                NavPopPinDaoBean bean = new NavPopPinDaoBean();
				try {
                    //<a href="http://v.qq.com/travel/" class="link_nav" target="_blank" _stat="new_vs_header:pop_旅游">旅游</a>
					Element aElement = beanElements.get(i).select("a").first();
                    String pindaoName = aElement.text();
                    String pindaoUrl = aElement.attr("href");
                    System.out.println("i==="+i+";pindaoName ==="+pindaoName+";pindaoUrl=="+pindaoUrl);
                    bean.setPindaoName(pindaoName);
                    bean.setPindaoUrl(pindaoUrl);
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

    /**
     * Callback method from {@link LastHistoryStickYGridHeaderFragment.Callbacks} indicating that
     * the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int id) {
        System.out.println("onItemSelected == "+id);
    }

}
