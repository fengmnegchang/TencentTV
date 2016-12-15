/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:27:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.androidtvwidget.view.ExpendGridView;
import com.open.androidtvwidget.view.GridViewTV;
import com.open.tencenttv.R;
import com.open.tencenttv.bean.UserMyViewBean;
import com.open.tencenttv.bean.UserMyViewGridBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:27:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserMyViewExpandableListAdapter extends CommonExpandableListAdapter<UserMyViewBean, UserMyViewGridBean> {

	public UserMyViewExpandableListAdapter(Context mContext, List<UserMyViewBean> list) {
		super(mContext, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.adapter.CommonExpandableListAdapter#getChild(int,
	 * int)
	 */
	@Override
	public UserMyViewGridBean getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).getList().get(childPosition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getChildView(int,
	 * int, boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean arg2, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder mChildViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_user_my_view_child, null);
			mChildViewHolder = new ChildViewHolder();
			mChildViewHolder.gridView = (ExpendGridView) convertView.findViewById(R.id.gridView);
			convertView.setTag(mChildViewHolder);
		} else {
			mChildViewHolder = (ChildViewHolder) convertView.getTag();
		}

		mChildViewHolder.list = getGroup(groupPosition).getList();
		mChildViewHolder.mUserMyViewGridViewAdapter = new UserMyViewGridViewAdapter(mContext,mChildViewHolder.list);
		mChildViewHolder.gridView.setAdapter(mChildViewHolder.mUserMyViewGridViewAdapter);
		mChildViewHolder.mUserMyViewGridViewAdapter.notifyDataSetChanged();
		return convertView;
	}

	public class ChildViewHolder {
		ExpendGridView gridView;
		UserMyViewGridViewAdapter mUserMyViewGridViewAdapter;
		List<UserMyViewGridBean> list = new ArrayList<UserMyViewGridBean>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getChildrenCount
	 * (int)
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if(getGroup(groupPosition)!=null &&getGroup(groupPosition).getList()!=null && getGroup(groupPosition).getList().size()>0){
			return 1;
		}else{
			return 0;
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getGroupView(int,
	 * boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(int groupPosition, boolean arg1, View convertView, ViewGroup parent) {
		GroupViewHolder mGroupViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_user_my_view_group, null);
			mGroupViewHolder = new GroupViewHolder();
			mGroupViewHolder.text_group_name = (TextView) convertView.findViewById(R.id.text_group_name);
			convertView.setTag(mGroupViewHolder);
		} else {
			mGroupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		UserMyViewBean bean = (UserMyViewBean) getGroup(groupPosition);
		if (bean != null) {
			String groupname = "";
			if ("t".equals(bean.getType())) {
				groupname = "今天";
			} else if ("y".equals(bean.getType())) {
				groupname = "昨天";
			} else if ("e".equals(bean.getType())) {
				groupname = "更早";
			}
			mGroupViewHolder.text_group_name.setText(groupname);
		}
		return convertView;
	}

	public class GroupViewHolder {
		TextView text_group_name;
	}

}
