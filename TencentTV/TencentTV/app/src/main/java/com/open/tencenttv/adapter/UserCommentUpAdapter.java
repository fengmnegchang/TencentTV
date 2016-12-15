/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:13:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.open.tencenttv.R;
import com.open.tencenttv.bean.CommentBean;
import com.open.tencenttv.bean.MessageBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:13:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserCommentUpAdapter extends CommonAdapter<MessageBean> {

	SimpleDateFormat simpleDateFormat;
	public UserCommentUpAdapter(Context mContext, List<MessageBean> list) {
		super(mContext, list);
		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_user_comment_up, parent, false);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_reply_username = (TextView) convertView.findViewById(R.id.text_reply_username);
			viewHolder.text_reply_comment_content = (TextView) convertView.findViewById(R.id.text_reply_comment_content);

			viewHolder.layout_mycomment = (RelativeLayout) convertView.findViewById(R.id.layout_mycomment);
			viewHolder.text_mycomment_content = (TextView) convertView.findViewById(R.id.text_mycomment_content);
			viewHolder.text_mycomment_up = (TextView) convertView.findViewById(R.id.text_mycomment_up);
			viewHolder.text_mycomment_poke = (TextView) convertView.findViewById(R.id.text_mycomment_poke);
			viewHolder.text_mycomment_repnum = (TextView) convertView.findViewById(R.id.text_mycomment_repnum);
			viewHolder.text_mycomment_article = (TextView) convertView.findViewById(R.id.text_mycomment_article);

//			viewHolder.text_reply_up = (TextView) convertView.findViewById(R.id.text_reply_up);
//			viewHolder.text_reply_poke = (TextView) convertView.findViewById(R.id.text_reply_poke);
//			viewHolder.text_reply_repnum = (TextView) convertView.findViewById(R.id.text_reply_repnum);
//			viewHolder.text_reply_time = (TextView) convertView.findViewById(R.id.text_reply_time);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MessageBean bean = (MessageBean) getItem(position);
		if (bean != null) {
			CommentBean mycomment = bean.getMyComment();
			if (mycomment != null) {
				StringBuffer buffer = new StringBuffer();
				for(int i=0;i<mycomment.getUpusers().size();i++){
					buffer.append(mycomment.getUpusers().get(i).getNick()).append("、");
				}
				String reply_username = buffer.substring(0, buffer.length()-1)+"等"+mycomment.getUpusers().size()+"人顶了你的短评：";
				viewHolder.text_reply_username.setText(reply_username);
				viewHolder.text_mycomment_content.setText(mycomment.getContent());
				viewHolder.text_mycomment_up.setText("(" + mycomment.getUp() + ")");
				viewHolder.text_mycomment_poke.setText("(" + mycomment.getPoke() + ")");
				viewHolder.text_mycomment_repnum.setText("(" + mycomment.getRepnum() + ")");
				if(bean.getArticle()!=null){
					viewHolder.text_mycomment_article.setText(simpleDateFormat.format(new Date(Long.parseLong(mycomment.getTime())*1000))+""+bean.getArticle().getTitle());
				}else{
					viewHolder.text_mycomment_article.setText(simpleDateFormat.format(new Date(Long.parseLong(mycomment.getTime())*1000))+"");
				}
				
			}
			
//			CommentBean replyComment = bean.getReplyComment();
//			if (replyComment != null) {
//				viewHolder.text_reply_comment_content.setText(replyComment.getContent());
//				viewHolder.text_reply_up.setText("(" + replyComment.getUp() + ")");
//				viewHolder.text_reply_poke.setText("(" + replyComment.getPoke() + ")");
//				viewHolder.text_reply_repnum.setText("(" + replyComment.getRepnum() + ")");
//				viewHolder.text_reply_time.setText(simpleDateFormat.format(new Date(Long.parseLong(replyComment.getTime())*1000))+"");
//			}
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;
		TextView text_reply_username;
		TextView text_reply_comment_content;

		RelativeLayout layout_mycomment;
		TextView text_mycomment_content;
		TextView text_mycomment_up;
		TextView text_mycomment_poke;
		TextView text_mycomment_repnum;
		TextView text_mycomment_article;

//		TextView text_reply_up;
//		TextView text_reply_poke;
//		TextView text_reply_repnum;
//		TextView text_reply_time;

	}
}
