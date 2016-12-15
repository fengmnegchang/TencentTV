package com.jayfang.dropdownmenu;

import java.util.List;

import com.jayfang.dropdownmenu.example.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 *  <a href="http://fangjie.info">JayFang</a>
 *  Email:JayFang1993@gmail.com
 *  GitHub:github.com/JayFang1993
 *
 */

public class MenuListAdapter extends BaseAdapter {

    private Context context;
    private List<MenuBean> menulist;

    private int SelectIndex;
    private int TextSize;
    private int TextColor;
    private boolean showCheck;
    private int CheckIcon;

    public MenuListAdapter(Context context, List<MenuBean> menulist) {
        this.context = context;
        this.menulist=menulist;
        this.TextColor= Color.BLACK;
        this.TextSize=15;
    }

    public void setSelectIndex(int selectIndex) {
        SelectIndex = selectIndex;
    }


    public void setShowCheck(boolean showCheck) {
        this.showCheck = showCheck;
    }

    public void setTextSize(int textSize) {
        TextSize = textSize;
    }

    public void setTextColor(int textColor) {
        TextColor = textColor;
    }

    public void setCheckIcon(int checkIcon) {
        CheckIcon = checkIcon;
    }

    @Override
    public int getCount() {
        return menulist.size();
    }

    @Override
    public Object getItem(int position) {
        return menulist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= LayoutInflater.from(context).inflate(R.layout.menu_list_item,parent,false);
        TextView textView=(TextView)v.findViewById(R.id.tv_menu_item);
        textView.setTextSize(TextSize);
        textView.setTextColor(TextColor);
        MenuBean bean = (MenuBean) getItem(position);
        textView.setText(bean.getMenuname());

        if (showCheck&&SelectIndex==position) {
            ImageView imageView = (ImageView) v.findViewById(R.id.iv_menu_select);
            imageView.setVisibility(View.VISIBLE);
            if (CheckIcon!=0)
               imageView.setImageResource(CheckIcon);
        }
        return v;
    }
}