package com.open.tencenttv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.NavPopPinDaoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *  Top视频类型列表 电视+电影+ adapter
 * @author :fengguangjing
 * @createTime: 16/11/1
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class RecyclerViewPresenter extends OpenPresenter {
    private List<String> labels ;
    private List<NavPopPinDaoBean> list;
    private GeneralAdapter mAdapter;

    public RecyclerViewPresenter(List<NavPopPinDaoBean> list) {
        this.list = list;
    }

    public RecyclerViewPresenter(int count) {
    	labels = new ArrayList<String>();
        for(int i=0;i<count;i++){
            labels.add("item"+i);
        }
    }

    @Override
    public void setAdapter(GeneralAdapter adapter) {
        this.mAdapter = adapter;
    }

    /**
     * 用于数据加载更多测试.
     */
    public void addDatas(int count) {
        int sum = labels.size();
        for (int i = sum; i < sum + count; i++) {
            labels.add(String.valueOf(i));
        }
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
    	if(list==null){
    		return labels.size();
    	}
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
    	if(list==null){
    		return labels.get(position);
    	}
        return list.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview_view, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GridViewHolder gridViewHolder = (GridViewHolder) viewHolder;
        TextView textView = (TextView) gridViewHolder.tv;
        
        if(list==null){
        	 textView.setText(labels.get(position));
        }else{
        	NavPopPinDaoBean bean = (NavPopPinDaoBean) getItem(position);
        	 textView.setText(bean.getPindaoName());
        }
       
    }

}
