package com.open.tencenttv;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.keyboard.SkbContainer;
import com.open.androidtvwidget.keyboard.SoftKey;
import com.open.androidtvwidget.keyboard.SoftKeyBoardListener;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.bean.CircularBean;
import com.open.tencenttv.bean.PanBean;
import com.open.tencenttv.fragment.PinDaoFragment;
import com.open.tencenttv.fragment.PinDaoListFragment;
import com.open.tencenttv.widget.CircularPopupWindow;
import com.open.tencenttv.widget.androidtagview.TagContainerLayout;
import com.open.tencenttv.widget.androidtagview.TagView;

import java.util.ArrayList;
import java.util.List;


/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/11
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class SearchKeyBoardActivity extends CommonFragmentActivity {
    private TextView input_tv;
    private SkbContainer skbContainer;
    private TagContainerLayout mTagContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_key_board);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);

        input_tv = (TextView) findViewById(R.id.input_tv);
        skbContainer = (SkbContainer) findViewById(R.id.skbContainer);
        mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);

    }

    @Override
    protected void initValue() {
        super.initValue();
        // 默认是 OpenEff...，建议使用 NoDraw... ...
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
        mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setTranDurAnimTime(200);
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距



        skbContainer.setFocusable(true);
        skbContainer.setFocusableInTouchMode(true);
        // 设置属性(默认是不移动的选中边框)
        setSkbContainerMove();
        skbContainer.setSkbLayout(R.xml.skb_t9_keys);
        //
        skbContainer.setSelectSofkKeyFront(true); // 设置选中边框最前面.

        List<String> list2 = new ArrayList<String>();
        list2.add("China");
        list2.add("USA");
        list2.add("Austria");
        list2.add("Japan");
        list2.add("Sudan");
        list2.add("Spain");
        list2.add("UK");
        list2.add("Germany");
        list2.add("Niger");
        list2.add("Poland");
        list2.add("Norway");
        list2.add("Uruguay");
        list2.add("Brazil");
        mTagContainerLayout.setTags(list2);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        // 监听键盘事件.
        skbContainer.setOnSoftKeyBoardListener(new SoftKeyBoardListener() {
            @Override
            public void onCommitText(SoftKey softKey) {
                if ((skbContainer.getSkbLayoutId() == R.xml.skb_t9_keys)) {
                    onCommitT9Text(softKey);
                }
            }

            @Override
            public void onBack(SoftKey key) {
                finish();
            }

            @Override
            public void onDelete(SoftKey key) {
                String text = input_tv.getText().toString();
                input_tv.setText(text.substring(0, text.length() - 1));
            }

        });
        //  （测试键盘失去焦点和获取焦点)
        skbContainer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                OPENLOG.D("hasFocus:" + hasFocus);
                if (hasFocus) {
                    if (mOldSoftKey != null)
                        skbContainer.setKeySelected(mOldSoftKey);
                    else
                        skbContainer.setDefualtSelectKey(0, 0);
                } else {
                    mOldSoftKey = skbContainer.getSelectKey();
                    skbContainer.setKeySelected(null);
                }
            }
        });


        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onItemPreSelected(View itemView,int position) {
                mRecyclerViewBridge.setUnFocusView(mOldView);
                System.out.println("item ========onItemPreSelected "+position);
            }

            @Override
            public void onItemSelected(View itemView,int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.2f);
                mOldView = itemView;
                System.out.println("item  ========onItemSelected "+position);
            }

            @Override
            public void onTagClick(TagView view, int position, String text) {
                mainUpView1.setFocusView(view, mOldView, 1.1f);
                mOldView = view;
                Toast.makeText(SearchKeyBoardActivity.this, "click-position:" + position + ", text:" + text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTagLongClick(TagView view, final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(SearchKeyBoardActivity.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTagContainerLayout.removeTag(position);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onTagCrossClick(TagView view, int position) {
//                mTagContainerLayout1.removeTag(position);
                Toast.makeText(SearchKeyBoardActivity.this, "Click TagView cross! position = " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });



        mTagContainerLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mainUpView1.setFocusView(mTagContainerLayout.getChildAt(0), mOldView, 1.1f);
                    mOldView = mTagContainerLayout.getChildAt(0);
                } else {
                    for (int i = 0; i < mTagContainerLayout.getChildCount(); i++) {
                        mainUpView1.setUnFocusView(mTagContainerLayout.getChildAt(i));
                    }
                }
            }
        });

        //搜索内容

        FragmentManager manager = getSupportFragmentManager();
        PinDaoListFragment rightFragment =  PinDaoListFragment.newInstance(mainUpView1,mRecyclerViewBridge,mOldView);
        PinDaoFragment leftFragment = PinDaoFragment.newInstance("ListFragmentPinDaoActivity",mainUpView1,mOldView,mRecyclerViewBridge);

        manager.beginTransaction().replace(R.id.frame_listview, rightFragment).commit();
        manager.beginTransaction().replace(R.id.frame_pindao, leftFragment).commit();

    }

    private void setSkbContainerMove() {
        mOldSoftKey = null;
        skbContainer.setMoveSoftKey(true); // 设置是否移动按键边框.
        RectF rectf = new RectF((int) getResources().getDimension(R.dimen.
                w_40), (int) getResources().getDimension(R.dimen.
                h_40), (int) getResources().getDimension(R.dimen.
                w_40), (int) getResources().getDimension(R.dimen.
                h_40));
        skbContainer.setSoftKeySelectPadding(rectf); // 设置移动边框相差的间距.
        skbContainer.setMoveDuration(200); // 设置移动边框的时间(默认:300)
        skbContainer.setSelectSofkKeyFront(true); // 设置选中边框在最前面.
    }

    /**
     * 切换布局测试.
     * 因为布局不相同，所以属性不一样，
     * 需要重新设置(不用参考我的,只是DEMO)
     */
    private void setSkbContainerOther() {
        mOldSoftKey = null;
        skbContainer.setMoveSoftKey(false);
        skbContainer.setSoftKeySelectPadding(0);
        skbContainer.setSelectSofkKeyFront(false);
    }

    SoftKey mOldSoftKey;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (skbContainer.onSoftKeyDown(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (skbContainer.onSoftKeyUp(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 处理T9键盘的按键.
     *
     * @param softKey
     */
    private void onCommitT9Text(SoftKey softKey) {
        switch (softKey.getKeyCode()) {
            case 1251://1
                input_tv.append("1");
                break;
            case 1250://0
                input_tv.append("0");
                break;
            case 1260://删除
                String text = input_tv.getText().toString();
                input_tv.setText(text.substring(0, text.length() - 1));
                break;
            case 1261://清除
                input_tv.setText("");
                break;
            default:
                CircularBean circularBean = new CircularBean();
                circularBean.setXoff(0);
                circularBean.setYoff(-400);
                ArrayList<PanBean> panBeenlist = new ArrayList<PanBean>();
                circularBean.setCenterValue(softKey.getKeyLabel().charAt(0) + "");
                for (int i = 1; i < softKey.getKeyLabel().length(); i++) {
                    PanBean panBean = new PanBean();
                    panBean.setKeycode(softKey.getKeyCode());
                    panBean.setKeyValue(softKey.getKeyLabel().charAt(i) + "");
                    panBeenlist.add(panBean);
                }
                circularBean.setPanList(panBeenlist);
                CircularPopupWindow mCircularPopupWindow = new CircularPopupWindow(this, circularBean, input_tv,mainUpView1,mOldView,mRecyclerViewBridge);
                mCircularPopupWindow.showPopupWindow(skbContainer);
                break;
        }
        Toast.makeText(SearchKeyBoardActivity.this, "keycode:" + softKey.getKeyCode(), Toast.LENGTH_SHORT).show();

    }

}
