package com.open.tencenttv.fragment;

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

import java.util.ArrayList;
import java.util.List;

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
public class PinDaoListFragment extends BaseV4ListFragment {
    private List<PinDaoBean> data = new ArrayList<PinDaoBean>();

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
        initData();
        this.setListAdapter(new PinDaoAdapter(getActivity(), data));

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 延时请求其它位置的item.
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                getListView().requestFocusFromTouch();
                getListView(). setSelection(0);
            }
        };
        handler.sendMessageDelayed(handler.obtainMessage(), 188);
        getListView().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                System.out.println("listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
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
        PinDaoFragment rightFragment = PinDaoFragment.newInstance("ListFragmentPinDaoActivity"+position,mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_pindao, rightFragment).commit();
    }


    public void initData() {
        data.clear();
        PinDaoBean bean = new PinDaoBean();
        bean.setType(0);
        bean.setTypeName("筛选");
        data.add(bean);

        bean = new PinDaoBean();
        bean.setType(1);
        bean.setTypeName("搜索");
        data.add(bean);

        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("大剧精选");
        data.add(bean);


        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("卫视同步");
        data.add(bean);


        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("抗战风云");
        data.add(bean);


        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("会员专享");
        data.add(bean);

        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("热门话题");
        data.add(bean);

        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("剧星专场");
        data.add(bean);

        bean = new PinDaoBean();
        bean.setType(2);
        bean.setTypeName("乡里乡亲");
        data.add(bean);
    }

}
