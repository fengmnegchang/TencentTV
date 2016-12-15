/*
 Copyright 2013 Tonic Artos

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.adapter.StickGridHeadersNewFeatureAdapter;
import com.open.tencenttv.bean.NewFeatureBean;
import com.open.tencenttv.json.NewFeatureJson;
import com.open.tencenttv.utils.UrlUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderClickListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderLongClickListener;

/**
 * A list fragment representing a list of Items. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 * 
 * @author Tonic Artos
 */
public class NewFeatureStickYGridHeaderFragment extends BaseV4Fragment<NewFeatureJson> implements AdapterView.OnItemClickListener, OnHeaderClickListener, OnHeaderLongClickListener {
	private static final String KEY_LIST_POSITION = "key_list_position";

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(int id) {
		}
	};

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;
	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	private int mFirstVisible;

	private StickyGridHeadersGridView mGridView;

	private StickGridHeadersNewFeatureAdapter mStickGridHeadersNewFeatureAdapter;

	private ArrayList<NewFeatureBean> newfeaturelist = new ArrayList<NewFeatureBean>();
	// private Menu mMenu;

	private Toast mToast;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public NewFeatureStickYGridHeaderFragment() {
	}

	public static NewFeatureStickYGridHeaderFragment newInstance(MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		NewFeatureStickYGridHeaderFragment fragment = new NewFeatureStickYGridHeaderFragment();
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_last_history_stick_y_grid_header, container, false);
		mGridView = (StickyGridHeadersGridView) view.findViewById(R.id.asset_grid);
		return view;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onHeaderClick(AdapterView<?> parent, View view, long id) {
		String text = "Header " + ((TextView) view.findViewById(android.R.id.text1)).getText() + " was tapped.";
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
		// if (view != null) {
		// mainUpView1.setFocusView(view, mOldView, 1.2f);
		// }
		// mOldView = view;
	}

	@Override
	public boolean onHeaderLongClick(AdapterView<?> parent, View view, long id) {
		String text = "Header " + ((TextView) view.findViewById(android.R.id.text1)).getText() + " was long pressed.";
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
		// if (view != null) {
		// mainUpView1.setFocusView(view, mOldView, 1.2f);
		// }
		// mOldView = view;
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		if (view != null) {
			mainUpView1.setFocusView(view, mOldView, 1.1f);
		}
		mOldView = view;
		// 进入频道
		if (newfeaturelist != null && id >= 0 && newfeaturelist.get((int) id) != null && newfeaturelist.get((int) id).getHrefUrl() != null) {
			TencentTVWebViewActivity.startTencentTVWebViewActivity(getActivity(), newfeaturelist.get((int) id).getHrefUrl());
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null) {
			mFirstVisible = savedInstanceState.getInt(KEY_LIST_POSITION);
		}

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
		}
		doAsync(NewFeatureStickYGridHeaderFragment.this, NewFeatureStickYGridHeaderFragment.this, NewFeatureStickYGridHeaderFragment.this);
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mGridView.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
		}
	}

	@SuppressLint("NewApi")
	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			mGridView.setItemChecked(mActivatedPosition, false);
		} else {
			mGridView.setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(int position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseV4Fragment#call()
	 */
	@Override
	public NewFeatureJson call() throws Exception {
		// TODO Auto-generated method stub
		// Simulates a background job.
		NewFeatureJson mCommonT = new NewFeatureJson();
		ArrayList<NewFeatureBean> list = new ArrayList<NewFeatureBean>();
		try {
			// 解析网络标签
			list = parseNewfeaturelist(UrlUtils.TENCENT_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCommonT.setList(list);
		return mCommonT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.newsinfo.BaseV4Fragment#onCallback(com.example.newsinfo.bean
	 * .NewsBean[])
	 */
	@Override
	public void onCallback(NewFeatureJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		newfeaturelist.clear();
		newfeaturelist.addAll(result.getList());
		mStickGridHeadersNewFeatureAdapter = new StickGridHeadersNewFeatureAdapter(getActivity().getApplicationContext(), newfeaturelist, R.layout.item_new_feature_stick_header,
				R.layout.item_new_feature_stick_grid);
		mGridView.setAdapter(mStickGridHeadersNewFeatureAdapter);
		mGridView.setOnItemClickListener(this);
		((StickyGridHeadersGridView) mGridView).areHeadersSticky();

		((StickyGridHeadersGridView) mGridView).setOnHeaderClickListener(this);
		((StickyGridHeadersGridView) mGridView).setOnHeaderLongClickListener(this);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mGridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * 这里注意要加判断是否为NULL. 因为在重新加载数据以后会出问题.
				 */
				if (view != null) {
					mainUpView1.setFocusView(view, mOldView, 1.1f);
				}
				mOldView = view;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseV4Fragment#onCallEarliest()
	 */
	@Override
	public void onCallEarliest() throws Exception {
		// TODO Auto-generated method stub
		super.onCallEarliest();
	}

	public ArrayList<NewFeatureBean> parseNewfeaturelist(String href) {
		ArrayList<NewFeatureBean> list = new ArrayList<NewFeatureBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// Element masthead = doc.select("site_head_black").first();

			// mod_row_box 分类
			Elements mod_row_boxElements = doc.select("div.mod_row_box");
			// 解析文件
			for (int i = 0; i < mod_row_boxElements.size(); i++) {
				// mod_hd cf 为你精选
				/**
				 * <div class="mod_hd cf"> <div class="mod_title"> <h2 class="title">
				 * 为你精选</h2> <div class="sub_title"><a href=
				 * "http://v.qq.com/x/cover/2oz855yfxhynr6o/b0346tgf6v2.html"
				 * target="_blank"
				 * _stat="new_vs_feature:subtitle">习近平总书记的一天</a></div> <div
				 * class="title_action"> <div class="title_action"> <a
				 * href="javascript:;" class="btn_refresh"
				 * _stat="new_vs_feature:next"><i class="icon icon_refresh"><svg
				 * class="svg_icon svg_icon_refresh" viewBox="0 0 20 20"><use
				 * xlink:href="#svg_icon_refresh" /></svg></i><span
				 * class="btn_inner">换一换</span></a> </div> </div> </div> </div>
				 */
				NewFeatureBean bean;
				String typetitle = "";
				String typeurl = "";
				Element mod_hdElement = mod_row_boxElements.get(i).select("div.mod_hd").first();
				if (mod_hdElement != null) {
					try {
						Element h2Element = mod_hdElement.select("h2").first();
						bean = new NewFeatureBean();
						bean.setItemType(i + 1);
						typetitle = h2Element.text();
						bean.setTitle(typetitle);
						Log.i(TAG, "i===" + i + ";typetitle ===" + typetitle);
						bean.setItemTypeName(typetitle);

						Element aElement = mod_hdElement.select("a").first();
						typeurl = aElement.attr("href");
						String alt = aElement.text();
						Log.i(TAG, "i===" + i + ";typeurl ===" + typeurl + ";alt==" + alt);
						bean.setAlt(alt);
						bean.setHrefUrl(typeurl);
						bean.setItemTypeUrl(typeurl);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// mod_bd cf 右边内容
					Elements list_itemElements = mod_row_boxElements.get(i).select("li.list_item");
					for (int y = 0; y < list_itemElements.size(); y++) {
						bean = new NewFeatureBean();
						try {
							/**
							 * <li class="list_item">
							 * <a href=
							 * "http://v.qq.com/x/cover/5bha7ic050wdzrq/w0022z2r8vn.html"
							 * target="_blank" class="figure" tabindex="-1"
							 * title="《Big磅来了》专访唐嫣：李未央是怎么活到最后的"
							 * _stat="new_vs_feature:item"> <img src=
							 * "//img1.gtimg.com/ent/pics/hv1/215/151/2155/140167595.jpg"
							 * alt="《Big磅来了》专访唐嫣：李未央是怎么活到最后的"
							 * onerror="picerr(this,'f')"> <div
							 * class="figure_caption figure_caption_two">
							 * <strong
							 * class="figure_title figure_title_two_row">
							 * 《Big磅来了》专访唐嫣：李未央是怎么活到最后的 </strong> <div
							 * class="figure_desc" title="友情提醒：真的不是靠美瞳！">
							 * 友情提醒：真的不是靠美瞳！ </div> </div> </a></li>
							 */
							String lititle = "";
							String lihrefurl = "";
							Element liaElement = null;
							try {
								liaElement = list_itemElements.get(y).select("a").first();
								lititle = liaElement.attr("title");
								lihrefurl = liaElement.attr("href");
							} catch (Exception e) {
								e.printStackTrace();
							}

							bean.setItemType(i + 1);
							bean.setItemTypeUrl(typeurl);
							bean.setItemTypeName(typetitle);
							bean.setTitle(lititle);
							bean.setHrefUrl(lihrefurl);

							String liimageurl = "";
							try {
								Element liimgElement = list_itemElements.get(y).select("img").first();
								liimageurl = liimgElement.attr("src");
								bean.setImageUrl(liimageurl);
							} catch (Exception e) {
								e.printStackTrace();

							}

							String lifigure_desc = "";
							try {
								Element lifigure_descElement = list_itemElements.get(y).select("div.figure_desc").first();
								if (lifigure_descElement != null) {
									lifigure_desc = lifigure_descElement.text();
								} else {
									Element lifigure_countElement = list_itemElements.get(y).select("div.figure_count").first();
									lifigure_desc = lifigure_countElement.text();
								}
								bean.setFigure_desc(lifigure_desc);
							} catch (Exception e) {
								e.printStackTrace();
							}

							Log.i(TAG, "i===" + i + ";y===" + y + ";lititle ===" + lititle + ";lihrefurl==" + lihrefurl + ";liimageurl = " + liimageurl + ";lifigure_desc==" + lifigure_desc);
						} catch (Exception e) {
							e.printStackTrace();
						}
						list.add(bean);
					}
				} else {
					Element mod_ad_spreadElement = mod_row_boxElements.get(i).select("div.mod_ad_spread").first();
					// 广告
					if (mod_ad_spreadElement != null) {
						try {
							Elements liitemElements = mod_ad_spreadElement.select("li.item");
							for (int y = 0; y < liitemElements.size(); y++) {
								bean = new NewFeatureBean();
								bean.setItemType(i + 1);
								bean.setItemTypeUrl("");
								bean.setItemTypeName("广告");

								/**
								 * <li class="item item_1">
								 * <a class="pic_default" href=
								 * "http://v.qq.com/cover/8/8479ahinkqm7czh.html"
								 * target="_blank"
								 * _stat="new_vs_tv_banner:spic"> <img lz_src=
								 * "//i.gtimg.cn/qqlive/images/20161110/i1478745988_1.jpg"
								 * alt="锦绣未央" onerror="picerr(this,'h')"> </a>
								 * <a class="pic_large" href=
								 * "http://v.qq.com/cover/8/8479ahinkqm7czh.html"
								 * target="_blank"
								 * _stat="new_vs_tv_banner:lpic"> <img lz_src=
								 * "//puui.qpic.cn/tv/0/5727637_56895/0"
								 * alt="锦绣未央" onerror="picerr(this,'h')"> </a></li>
								 */
								Element aElement = liitemElements.get(y).select("a.pic_default").first();
								String lihrefurl = aElement.attr("href");
								Element liimgElement = liitemElements.get(y).select("img").first();
								String liimageurl = liimgElement.attr("lz_src");
								String lititle = liimgElement.attr("alt");
								Log.i(TAG, "i===" + i + ";y===" + y + ";lititle ===" + lititle + ";lihrefurl==" + lihrefurl + ";liimageurl = " + liimageurl);
								bean.setTitle(lititle);
								bean.setHrefUrl(lihrefurl);
								bean.setImageUrl(liimageurl);
								list.add(bean);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
