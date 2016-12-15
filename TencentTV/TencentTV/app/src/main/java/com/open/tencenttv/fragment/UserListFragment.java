/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:26:36
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
import android.support.v4.app.Fragment;
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
import com.open.tencenttv.adapter.UserNaviAdapter;
import com.open.tencenttv.bean.UserNaviBean;
import com.open.tencenttv.json.UserNaviJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:26:36
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserListFragment  extends BaseV4ListFragment<UserNaviJson> {
	private List<UserNaviBean> data = new ArrayList<UserNaviBean>();
    private UserNaviAdapter mUserNaviAdapter;
    public static UserListFragment newInstance(MainUpView mainUpView1,EffectNoDrawBridge mRecyclerViewBridge, View mOldView){
    	UserListFragment fragment = new UserListFragment();
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        fragment.mainUpView1 = mainUpView1;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        // 设置ListFragment默认的ListView，即@id/android:list
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserNaviAdapter = new   UserNaviAdapter(getActivity(), data);
        this.setListAdapter(mUserNaviAdapter);
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
 
    }

    /**
     * 选择fragment
     * @param position
     */
    private void setSelectedFragment(int position){
    	//看单
    	//http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2562&pidx=0&size=30&dtype=0&type=0&callback=jQuery19106935243788063994_1481188648206&g_tk=629796729&_=1481188648207
    	//观看历史http://ncgi.video.qq.com/tvideo/fcgi-bin/myview?callback=jQuery19102949009773172202_1481270020442&pn=25&otype=json&t=1&plat=2&pver=4&cur=1&g_tk=1605338694&_=1481270020443
    	//订阅
    	//推荐订阅http://c.v.qq.com/vfollowlst?otype=json&pagenum=1&pagesize=5&callback=jQuery19105151766284214294_1481251654251&g_tk=1605338694&_=1481251654252
    	//球星球队http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2563&pidx=0&size=5&dtype=1&type=0&callback=jQuery19105069239718699954_1481264321368&g_tk=1605338694&_=1481264321379
    	//vip
    	//http://pay.video.qq.com/fcgi-bin/price-month?otype=json&hlw_params=pf%3D1%26app%3Dbrowser&callback=jQuery191033791200020116796_1481188958294&low_login=1&_=1481188958306
    	
    	FragmentManager manager = getActivity().getSupportFragmentManager();
    	Fragment fragment = RankV4Fragment.newInstance();
        if(data.get(position).getNavi_text().equals("观看历史")){
        	fragment = UserMyViewExpandableListFragment.newInstance("", mainUpView1, mRecyclerViewBridge, mOldView);
        }else if(data.get(position).getNavi_text().equals("看单")){
        	fragment = UserFollowGridFragment.newInstance("", mainUpView1, mRecyclerViewBridge, mOldView);
        }else if(data.get(position).getNavi_text().equals("订阅")){
        	fragment = UserSubscribeTabHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_U_SUBSCRIBE,"ul.mod_tab_list","li","a",mainUpView1, mOldView, mRecyclerViewBridge);
        } else if(data.get(position).getNavi_text().equals("我的评论")){
        	fragment = UserCommentHeadTabHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_U_COMMENT,"div.mycenter-nav","li","a",mainUpView1, mOldView, mRecyclerViewBridge);
        } else if(data.get(position).getNavi_text().equals("我的钱包")){
        	fragment = UserWalletTabHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_U_WALLET,"div.mod_nav_related","li","a",mainUpView1, mOldView, mRecyclerViewBridge);
        } else if(data.get(position).getNavi_text().equals("VIP会员")){
        	fragment = UserVipTabHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_U_VIP,"ul.mod_tab_list","li","span",mainUpView1, mOldView, mRecyclerViewBridge);
        } 
        manager.beginTransaction().replace(R.id.frame_user, fragment).commit();
    }



	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4ListFragment#call()
	 */
	@Override
	public UserNaviJson call() throws Exception {
		// TODO Auto-generated method stub
		UserNaviJson mCommonT = new UserNaviJson();
		List<UserNaviBean> list = parseSidenavi(UrlUtils.TENCENT_USER);
		mCommonT.setList(list);
		return mCommonT;
	}



	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4ListFragment#onCallback(com.open.tencenttv.bean.CommonT)
	 */
	@Override
	public void onCallback(UserNaviJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		data.clear();
		data.addAll(result.getList());
		mUserNaviAdapter.notifyDataSetChanged();
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

	public ArrayList<UserNaviBean> parseSidenavi(String href) {
		ArrayList<UserNaviBean> list = new ArrayList<UserNaviBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("ul.navigate_list").first();
			Elements liElements = masthead.select("li.item");

			// 解析文件
			for (int i = 0; i < liElements.size(); i++) {
				UserNaviBean bean = new UserNaviBean();
				try {
					try {
						Element aElement = liElements.get(i).select("a").first();
						String typeName = aElement.text();
						String hrefurl = aElement.attr("href");
						Log.i(TAG,"i===" + i + ";typeName ===" + typeName + ";hrefurl===" + hrefurl);
						bean.setNavi_href(hrefurl);
						bean.setNavi_text(typeName);
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
