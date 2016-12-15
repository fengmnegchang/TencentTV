/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-24下午1:59:01
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
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.GridViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.StarHeadGridViewAdapter;
import com.open.tencenttv.bean.StarBean;
import com.open.tencenttv.bean.StarInfo;
import com.open.tencenttv.bean.StarRelateBean;
import com.open.tencenttv.json.StarJson;
import com.open.tencenttv.utils.UrlUtils;
import com.open.tencenttv.widget.CircleImageView;
import com.open.tencenttv.widget.ExpandableTextView;

/**
 ***************************************************************************************************************************************************************************** 
 * 明星简介head
 * 
 * @author :fengguangjing
 * @createTime:2016-11-24下午1:59:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarHeadFragment extends BaseV4Fragment<StarJson> {
	private String tencentStar;
	private ExpandableTextView expand_text_view;
	private ImageButton expand_collapse;// 折叠textview
	private CircleImageView image_logo;
    private GridViewTV gridViewTV;
    private StarHeadGridViewAdapter mStarHeadGridViewAdapter;
    private List<StarRelateBean> list = new ArrayList<StarRelateBean>();
	public static StarHeadFragment newInstance(String tencentStar, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		StarHeadFragment fragment = new StarHeadFragment();
		fragment.tencentStar = tencentStar;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_star_head, container, false);
		expand_text_view = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
		expand_collapse = (ImageButton) view.findViewById(R.id.expand_collapse);
		image_logo = (CircleImageView) view.findViewById(R.id.image_logo);
		gridViewTV = (GridViewTV) view.findViewById(R.id.gridView);
		doAsync(this, this, this);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mStarHeadGridViewAdapter = new StarHeadGridViewAdapter(getActivity(),list);
		gridViewTV.setAdapter(mStarHeadGridViewAdapter);
		expand_text_view.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
			@Override
			public void onExpandStateChanged(TextView textView, boolean isExpanded) {
				Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.BaseV4Fragment#call()
	 */
	@Override
	public StarJson call() throws Exception {
		// TODO Auto-generated method stub
		StarJson mCommonT = new StarJson();
		StarBean starbean = parseStar(UrlUtils.TENCENT_STAR);
		mCommonT.setStarbean(starbean);
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
	public void onCallback(StarJson result) {
		super.onCallback(result);
		StarBean starbean = result.getStarbean();
		StringBuilder builder = new StringBuilder();
		for(StarInfo info : starbean.getStarInfo_list()){
			builder.append(info.getListLeft()+":").append(info.getListRight()+"\n");
		}
		expand_text_view.setText(Html.fromHtml(starbean.getStarIntro_top()+"\n"+starbean.getStar_briefIntro()+"\n"+starbean.getStar_description()+
				"\n"+builder.toString()));
		
	    if(starbean.getHead_portrait()!=null && starbean.getHead_portrait().length()>0){
          DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist)
                  .showImageForEmptyUri(R.drawable.mainview_cloudlist)
                  .showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc()
                  .build();
          ImageLoader.getInstance().displayImage(starbean.getHead_portrait(),image_logo,options,getImageLoadingListener());
      }
	    list.clear();
	    list.addAll(starbean.getStarRelate_list());
	    mStarHeadGridViewAdapter.notifyDataSetChanged();
	}

	public StarBean parseStar(String href) {
		StarBean bean = new StarBean();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);
			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("div.star_container").first();
			Element star_currentElement = masthead.select("div.star_current").first();
			if (star_currentElement != null) {
				try {
					Element head_portraitElement = star_currentElement.select("div.head_portrait").first();
					String head_portrait = head_portraitElement.select("img").first().attr("src");
					Log.i(TAG, "head_portrait==" + head_portrait);
					bean.setHead_portrait(head_portrait);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					Element starIntroElement = star_currentElement.select("div.starIntro").first();
					try {
						Element starIntro_topElement = starIntroElement.select("div.starIntro_top").first();
						String starIntro_top = starIntro_topElement.select("h4").first().text();
						Log.i(TAG, "starIntro_top==" + starIntro_top);
						bean.setStarIntro_top(starIntro_top);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						Element pstar_briefIntroElement = starIntroElement.select("p.star_briefIntro").first();
						String pstar_briefIntro = pstar_briefIntroElement.text();
						Log.i(TAG, "pstar_briefIntro==" + pstar_briefIntro);
						bean.setStar_briefIntro(pstar_briefIntro);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// p.star_des_marginB
					try {
						Element pstar_descriptionElement = starIntroElement.select("p.star_des_marginB").first();
						String pstar_description = pstar_descriptionElement.text();
						Log.i(TAG, "pstar_description==" + pstar_description);
						bean.setStar_description(pstar_description);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// p.star_description
					try {
						Element starInfo_listElement = starIntroElement.select("ul.starInfo_list").first();
						if (starInfo_listElement != null) {
							Elements listElements = starInfo_listElement.select("li");
							if (listElements != null && listElements.size() > 0) {
								List<StarInfo> starInfo_list = new ArrayList<StarInfo>();
								for (int i = 0; i < listElements.size(); i++) {
									StarInfo star = new StarInfo();
									Element liElement = listElements.get(i);
									Element listLeftElement = liElement.select("span.listLeft").first();
									String listLeft = listLeftElement.text();
									Element listRightElement = liElement.select("span.listRight").first();
									String listRight = listRightElement.text();
									star.setListLeft(listLeft);
									star.setListRight(listRight);
									Log.i(TAG, "listLeft==" + listLeft + ";listRight==" + listRight);
									starInfo_list.add(star);
								}
								bean.setStarInfo_list(starInfo_list);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			Element star_relatedBoxElement = masthead.select("div.star_relatedBox").first();
			if (star_relatedBoxElement != null) {
				Elements star_relatedListElements = star_relatedBoxElement.select("ul.star_relatedList");
				if (star_relatedListElements != null && star_relatedListElements.size() > 0) {
					List<StarRelateBean> starRelate_list = new ArrayList<StarRelateBean>();
					for (int i = 0; i < star_relatedListElements.size(); i++) {
						Elements liElements = star_relatedListElements.get(i).select("li");
						if (liElements != null && liElements.size() > 0) {
							for (int y = 0; y < liElements.size(); y++) {
								StarRelateBean starRelateBean = new StarRelateBean();
								try {
									Element aElement = liElements.get(y).select("a").first();
									String star_relatedPic = aElement.select("img").first().attr("src");
									String hrefurl = aElement.attr("href");
									Log.i(TAG, "i=="+i+";y==="+y+"star_relatedPic==" + star_relatedPic + ";hrefurl==" + hrefurl);
									starRelateBean.setHrefurl(hrefurl);
									starRelateBean.setStar_relatedPic(star_relatedPic);
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element star_nameElement = liElements.get(y).select("div.star_name").first();
									String star_name = star_nameElement.select("h4").first().text();
									String relation = star_nameElement.select("p").first().text();
									Log.i(TAG,  "i=="+i+";y==="+y+"star_name==" + star_name + ";relation==" + relation);
									starRelateBean.setStar_name(star_name);
									starRelateBean.setRelation(relation);
									starRelate_list.add(starRelateBean);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}
					}
					bean.setStarRelate_list(starRelate_list);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
}
