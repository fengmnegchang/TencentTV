/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-22下午5:21:08
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

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.DropItemBean;
import com.jayfang.dropdownmenu.MenuBean;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.WeakReferenceHandler;
import com.open.tencenttv.json.DropItemJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-22下午5:21:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PinDaoSearchHeadFragment extends BaseV4Fragment<DropItemJson> {
	public static final String TAG = PinDaoSearchHeadFragment.class.getSimpleName();
	private String pindaoName;
	private DropDownMenu mMenu;
	private String url;
	private List<DropItemBean> mMenuItems = new ArrayList<DropItemBean>();
	private WeakReferenceHandler weakReferenceHandler;

	public static PinDaoSearchHeadFragment newInstance(WeakReferenceHandler weakReferenceHandler, String url, String pindaoName, MainUpView mainUpView1, View mOldView,
			EffectNoDrawBridge mRecyclerViewBridge) {
		PinDaoSearchHeadFragment fragment = new PinDaoSearchHeadFragment();
		fragment.url = url;
		fragment.weakReferenceHandler = weakReferenceHandler;
		fragment.pindaoName = pindaoName;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pindao_search_head, container, false);
		mMenu = (DropDownMenu) view.findViewById(R.id.dropmenu);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		doAsync(this, this, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.BaseV4Fragment#call()
	 */
	@Override
	public DropItemJson call() throws Exception {
		// TODO Auto-generated method stub
		DropItemJson mCommonT = new DropItemJson();
		List<DropItemBean> mMenuItems = parseDropItemList(url);// 影视列表搜索头部
		mCommonT.setList(mMenuItems);
		return mCommonT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.BaseV4Fragment#onCallback(com.open.tencenttv.bean.
	 * CommonT)
	 */
	@Override
	public void onCallback(DropItemJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMenuItems.clear();
		mMenuItems.addAll(result.getList());
		mMenu.setmMenuCount(mMenuItems.size());
		mMenu.setmShowCount(6);
		mMenu.setShowCheck(true);
		mMenu.setmMenuTitleTextSize(16);
		mMenu.setmMenuTitleTextColor(Color.parseColor("#777777"));
		mMenu.setmMenuListTextSize(16);
		mMenu.setmMenuListTextColor(Color.BLACK);
		mMenu.setmMenuBackColor(Color.parseColor("#eeeeee"));
		mMenu.setmMenuPressedBackColor(Color.WHITE);
		mMenu.setmMenuPressedTitleTextColor(Color.BLACK);

		mMenu.setmCheckIcon(R.drawable.ico_make);

		mMenu.setmUpArrow(R.drawable.arrow_up);
		mMenu.setmDownArrow(R.drawable.arrow_down);

		mMenu.setShowDivider(false);
		mMenu.setmMenuListBackColor(Color.WHITE);
		// mMenu.setmMenuListSelectorRes(Color.WHITE);
		mMenu.setmArrowMarginTitle(20);
		// mMenu.setDefaultMenuTitle(strings);
		mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
			@Override
			public void onSelected(View listview, int RowIndex, int ColumnIndex) {
				if (RowIndex == 0) {
					RowIndex = 1;
				}
				Log.i(TAG, "select " + ColumnIndex + " column and " + RowIndex + " row");
				// 过滤筛选
				MenuBean mMenuBean = mMenuItems.get(ColumnIndex).getMenulist().get(RowIndex);
				// Log.i(TAG, mMenuBean.getHref() + ";" +
				// mMenuBean.getMenuname());
				Message msg = weakReferenceHandler.obtainMessage();
				msg.what = 110;
				msg.obj = mMenuBean.getHref();
				Log.i(TAG, "send msg ==href==" + mMenuBean.getHref() + ";what==" + 110);
				weakReferenceHandler.sendMessage(msg);
			}
		});
		mMenu.setmMenuItems(mMenuItems);
		mMenu.setIsDebug(false);

		if ("电影".equals(pindaoName)) {
			weakReferenceHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mMenu.setDefault(4, 2);
				}
			}, 5000);
		}

	}

	public ArrayList<DropItemBean> parseDropItemList(String href) {
		ArrayList<DropItemBean> list = new ArrayList<DropItemBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("ul.mod_filter_list").first();
			Elements liElements = masthead.select("li.item");
			/**
			 * <ul class="mod_filter_list" style="display: none;" r-show="{ toggleStatus['paramStatus'] && afterJudge }">
			 * <li class="item" r-class="{item_toggle: toggleStatus['subtype' + 'Status']}">
			 * <span class="label">类型：</span> <div class="tags_list"> <a
			 * _boss="subtype"
			 * href="?cate=10001&#38;offset=0&#38;sort=4&#38;subtype=-1"
			 * class="tag selected" data-index="-1" data-type="subtype" >全部</a>
			 */
			// 解析文件
			for (int i = 0; i < liElements.size(); i++) {
				try {
					DropItemBean mDropItemBean = new DropItemBean();
					Element spanElement = liElements.get(i).select("span.label").first();
					String label = spanElement.text();
					mDropItemBean.setLabel(label);
					Log.i(TAG, "i===" + i + ";label ===" + label);

					Element tags_listElement = liElements.get(i).select("div.tags_list").first();
					Elements aElements = tags_listElement.select("a");
					if (aElements != null && aElements.size() > 0) {
						List<MenuBean> menulist = new ArrayList<MenuBean>();
						MenuBean mMenuBean = new MenuBean();
						mMenuBean.setMenuname(label);
						menulist.add(mMenuBean);
						for (int y = 0; y < aElements.size(); y++) {
							mMenuBean = new MenuBean();
							Element aElement = aElements.get(y).select("a").first();
							String boss = aElement.attr("_boss");
							String hrefurl = aElement.attr("href");
							String dataindex = aElement.attr("data-index");
							String datatype = aElement.attr("data-type");
							String menuname = aElement.text();
							mMenuBean.setBoss(boss);
							mMenuBean.setHref(hrefurl);
							mMenuBean.setDataindex(dataindex);
							mMenuBean.setDatatype(datatype);
							mMenuBean.setMenuname(menuname);
							menulist.add(mMenuBean);
							Log.i(TAG, "i===" + i + ";y==" + y + ";boss ===" + boss + ";hrefurl ===" + hrefurl + ";dataindex ===" + dataindex + ";datatype ===" + datatype + ";menuname ===" + menuname);
						}
						mDropItemBean.setMenulist(menulist);
					}
					list.add(mDropItemBean);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
