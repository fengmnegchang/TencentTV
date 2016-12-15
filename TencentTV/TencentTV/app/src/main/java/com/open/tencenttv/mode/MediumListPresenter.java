package com.open.tencenttv.mode;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;

/**
 * Leanback 横向item demo.
 * 如果你想改变标题头的样式，那就写自己的吧.
 * Created by hailongqiu on 2016/8/25.
 */
public class MediumListPresenter extends AsyncTaskListPresenter {
    boolean mIsSelect;
    Context context;

    /**
     * 你可以重写这里，传入AutoGridViewLayoutManger.
     */
    @Override
    public RecyclerView.LayoutManager getLayoutManger(Context context) {
        this.context = context;
        return super.getLayoutManger(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medium, parent, false);
        return new ViewHolder(itemView);
    }

    public void setSelect(boolean isSelect) {
        this.mIsSelect = isSelect;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Movie movie = ((Movie) getItem(position));
        OpenCardView openCardView = (OpenCardView) viewHolder.view;
        openCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                } else {
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                }
            }
        });

//        if(movie.getLz_srcurl()!=null && movie.getLz_srcurl().length()>0){
//            imageurl = movie.getLz_srcurl();
//            doAsync(this,this,this);
//        }else{
//            openCardView.setBackgroundResource(R.drawable.mainview_cloudlist);
//        }
//        if (movie.getLz_srcurl() != null && movie.getLz_srcurl().length() > 0) {
//            Bitmap bitmap = mImageLoader.getBitmap(movie.getLz_srcurl());
//            openCardView.setBackground(new BitmapDrawable(bitmap));
//        } else {
//            openCardView.setBackgroundResource(R.drawable.mainview_cloudlist);
//        }

        Drawable d = viewHolder.view.getResources().getDrawable(R.drawable.ic_sp_block_focus);
        openCardView.setShadowDrawable(d);
        TextView tv = (TextView) openCardView.findViewById(R.id.title_tv);
        tv.setText(movie.getTitle());

        ImageView imageview = (ImageView) openCardView.findViewById(R.id.imageview);
        if (movie.getLz_srcurl() != null && movie.getLz_srcurl().length() > 0) {
            DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.mainview_cloudlist)
                    .showImageForEmptyUri(R.drawable.mainview_cloudlist)
                    .showImageOnFail(R.drawable.mainview_cloudlist).cacheInMemory().cacheOnDisc()
                    .build();
            ImageLoader.getInstance().displayImage(movie.getLz_srcurl(), imageview,options,getImageLoadingListener());
        }

        if (this.mIsSelect) {
            openCardView.setAlpha(0.5f);
        } else {
            openCardView.setAlpha(1.0f);
        }
        openCardView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 if(movie.getHrefurl()!=null){
					 TencentTVWebViewActivity.startTencentTVWebViewActivity(context, movie.getHrefurl());
				 }
				
			}
		});
    }

//    @Override
//    public CommonT call() throws Exception {
//        CommonT mCommonT = new CommonT();
//        Bitmap bitmap = mImageLoader.getBitmap(imageurl);
//        mCommonT.setBitmap(bitmap);
//        return mCommonT;
//    }
//
//    @Override
//    public void onCallback(CommonT result) {
//        super.onCallback(result);
//        openCardView.setBackground(new BitmapDrawable(result.getBitmap()));
//    }
}
