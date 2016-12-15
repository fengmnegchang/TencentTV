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
import com.open.tencenttv.adapter.PinDaoAdapter;
import com.open.tencenttv.bean.PinDaoBean;
import com.open.tencenttv.json.PinDaoBeanJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 * ****************************************************************************************************************************************************************************
 * 频道内容 ListFragment
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PinDaoListFragment extends BaseV4ListFragment<PinDaoBeanJson> {
    private List<PinDaoBean> data = new ArrayList<PinDaoBean>();
    private PinDaoAdapter mPinDaoAdapter;
    public static PinDaoListFragment newInstance(MainUpView mainUpView1,EffectNoDrawBridge mRecyclerViewBridge, View mOldView){
        PinDaoListFragment fragment = new PinDaoListFragment();
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        fragment.mainUpView1 = mainUpView1;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pindao, container, false);
        // 设置ListFragment默认的ListView，即@id/android:list
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPinDaoAdapter = new   PinDaoAdapter(getActivity(), data);
        this.setListAdapter(mPinDaoAdapter);
        doAsync(this, this, this);
        
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
    	PinDaoTabHorizontalViewPagerFragment rightFragment = PinDaoTabHorizontalViewPagerFragment.newInstance(data.get(position).getHrefurl(),"ul.filter_tabs","li","a", data.get(position).getTypeName(),mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_pindao, rightFragment).commit();
    }



	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4ListFragment#call()
	 */
	@Override
	public PinDaoBeanJson call() throws Exception {
		// TODO Auto-generated method stub
		PinDaoBeanJson mCommonT = new PinDaoBeanJson();
		List<PinDaoBean> list = parseSidenavi(UrlUtils.TENCENT_X_MOVIE_LIST);
		mCommonT.setList(list);
		return mCommonT;
	}



	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4ListFragment#onCallback(com.open.tencenttv.bean.CommonT)
	 */
	@Override
	public void onCallback(PinDaoBeanJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		data.clear();
		data.addAll(result.getList());
		mPinDaoAdapter.notifyDataSetChanged();
		// 延时请求其它位置的item.
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                getListView().requestFocusFromTouch();
                getListView(). setSelection(0);
            }
        };
        handler.sendMessageDelayed(handler.obtainMessage(), 5000);
        getListView().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                Log.i(TAG,"listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
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

	public ArrayList<PinDaoBean> parseSidenavi(String href) {
		ArrayList<PinDaoBean> list = new ArrayList<PinDaoBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("ul.side_navi").first();
			Elements liElements = masthead.select("li");

			/**
			 * <li class="item current"><a _boss="movie" href=
			 * "http://v.qq.com/x/movielist/?cate=10001&#38;offset=0&#38;sort=4"
			 * >电影</a></li>
			 */
			// 解析文件
			for (int i = 0; i < liElements.size(); i++) {
				PinDaoBean bean = new PinDaoBean();
				try {
					try {
						Element aElement = liElements.get(i).select("a").first();
						String typeName = aElement.text();
						String hrefurl = aElement.attr("href");
						Log.i(TAG,"i===" + i + ";typeName ===" + typeName + ";hrefurl===" + hrefurl);
						bean.setTypeName(typeName);
						bean.setHrefurl(hrefurl);
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
