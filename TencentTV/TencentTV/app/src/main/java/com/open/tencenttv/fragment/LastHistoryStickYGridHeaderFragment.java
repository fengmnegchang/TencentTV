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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.VedioPreViewActivity;
import com.open.tencenttv.adapter.StickGridHeadersLastHistoryAdapter;
import com.open.tencenttv.bean.LastHistoryBean;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderClickListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderLongClickListener;

import java.util.ArrayList;
import java.util.List;

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
public class LastHistoryStickYGridHeaderFragment extends BaseV4Fragment implements OnItemClickListener,
        OnHeaderClickListener, OnHeaderLongClickListener {
//    private static final String KEY_LIST_POSITION = "key_list_position";

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
//    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    /**
     * The current activated item position. Only used on tablets.
     */
//    private int mActivatedPosition = ListView.INVALID_POSITION;
    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

//    private int mFirstVisible;

    private StickyGridHeadersGridView mGridView;

//    private Menu mMenu;

    private Toast mToast;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LastHistoryStickYGridHeaderFragment() {
    }

    public static LastHistoryStickYGridHeaderFragment newInstance(MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
        LastHistoryStickYGridHeaderFragment fragment = new LastHistoryStickYGridHeaderFragment();
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

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.fragment_item_list, menu);
//        mMenu = menu;
//        menu.findItem(R.id.menu_toggle_sticky).setChecked(
//                ((StickyGridHeadersGridView)mGridView).areHeadersSticky());
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_last_history_stick_y_grid_header, container, false);
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
        if (view != null) {
            mainUpView1.setFocusView(view, mOldView, 1.2f);
        }
        mOldView = view;
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
        if (view != null) {
            mainUpView1.setFocusView(view, mOldView, 1.2f);
        }
        mOldView = view;
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(position);
        if (view != null) {
            mainUpView1.setFocusView(view, mOldView, 1.2f);
        }
        mOldView = view;
        //进入频道
        Intent intent = new Intent();
        intent.setClass(getActivity(), VedioPreViewActivity.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_toggle_sticky:
//                item.setChecked(!item.isChecked());
//                ((StickyGridHeadersGridView)mGridView)
//                        .setAreHeadersSticky(!((StickyGridHeadersGridView)mGridView)
//                                .areHeadersSticky());
//
//                return true;
//            case R.id.menu_use_list_adapter:
//                mGridView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item,
//                        getResources().getStringArray(R.array.countries)));
//                mMenu.findItem(R.id.menu_use_list_adapter).setVisible(false);
//                mMenu.findItem(R.id.menu_use_sticky_adapter).setVisible(true);
//                mMenu.findItem(R.id.menu_toggle_sticky).setVisible(false);
//                return true;
//            case R.id.menu_use_sticky_adapter:
//                mGridView.setAdapter(new StickyGridHeadersSimpleArrayAdapter<String>(getActivity()
//                        .getApplicationContext(), getResources().getStringArray(R.array.countries),
//                        R.layout.item_last_history_stick_header, R.layout.item));
//                mMenu.findItem(R.id.menu_use_list_adapter).setVisible(true);
//                mMenu.findItem(R.id.menu_toggle_sticky).setVisible(true);
//                mMenu.findItem(R.id.menu_use_sticky_adapter).setVisible(false);
//                return true;
//
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (mActivatedPosition != ListView.INVALID_POSITION) {
//            // Serialize and persist the activated item position.
//            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
//        }
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (StickyGridHeadersGridView) view.findViewById(R.id.asset_grid);
        mGridView.setOnItemClickListener(this);
        ((StickyGridHeadersGridView) mGridView).areHeadersSticky();
        /*
         * Currently set in the XML layout, but this is how you would do it in
         * your code.
         */
        // mGridView.setColumnWidth((int) calculatePixelsFromDips(100));
        // mGridView.setNumColumns(StickyGridHeadersGridView.AUTO_FIT);
        List<LastHistoryBean> list = new ArrayList<LastHistoryBean>();
        LastHistoryBean bean;
        for (int i = 0; i <15; i++) {
            bean = new LastHistoryBean();
            if(i<=8){
                bean.setItemType(1);
                bean.setItemTypeName("观看");
            }else if(i<=11){
                bean.setItemType(2);
                bean.setItemTypeName("应用");
            }else{
                bean.setItemType(3);
                bean.setItemTypeName("大家都在看");
            }
            bean.setDramaName("爸爸去哪儿");
            bean.setDramaCount("第24集");
            bean.setDate("9-11");
            list.add(bean);
        }

        mGridView.setAdapter(new StickGridHeadersLastHistoryAdapter(
                getActivity().getApplicationContext(),
                list,
                R.layout.item_last_history_stick_header,
                R.layout.item_last_history_stick_grid));

//        if (savedInstanceState != null) {
//            mFirstVisible = savedInstanceState.getInt(KEY_LIST_POSITION);
//        }

//        mGridView.setSelection(mFirstVisible);

//        // Restore the previously serialized activated item position.
//        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
//            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
//        }

        ((StickyGridHeadersGridView) mGridView).setOnHeaderClickListener(this);
        ((StickyGridHeadersGridView) mGridView).setOnHeaderLongClickListener(this);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 这里注意要加判断是否为NULL.
                 * 因为在重新加载数据以后会出问题.
                 */
                if (view != null) {
                    mainUpView1.setFocusView(view, mOldView, 1.2f);
                }
                mOldView = view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        setHasOptionsMenu(true);
    }

//    /**
//     * Turns on activate-on-click mode. When this mode is on, list items will be
//     * given the 'activated' state when touched.
//     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public void setActivateOnItemClick(boolean activateOnItemClick) {
//        // When setting CHOICE_MODE_SINGLE, ListView will automatically
//        // give items the 'activated' state when touched.
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            mGridView.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
//                    : ListView.CHOICE_MODE_NONE);
//        }
//    }
//
//    @SuppressLint("NewApi")
//    private void setActivatedPosition(int position) {
//        if (position == ListView.INVALID_POSITION) {
//            mGridView.setItemChecked(mActivatedPosition, false);
//        } else {
//            mGridView.setItemChecked(position, true);
//        }
//
//        mActivatedPosition = position;
//    }

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
}