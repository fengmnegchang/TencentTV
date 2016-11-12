package com.open.tencenttv;


import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.keyboard.SkbContainer;
import com.open.androidtvwidget.keyboard.SoftKey;
import com.open.androidtvwidget.keyboard.SoftKeyBoardListener;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.tencenttv.adapter.PanAdapter;
import com.open.tencenttv.widget.CircularMenu;


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
public class SearchKeyBoardActivity extends FragmentActivity {
    private CircularMenu wheelMenuView;
    private PanAdapter adapter;
    TextView input_tv;
    SkbContainer skbContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_key_board);
        wheelMenuView = (CircularMenu) findViewById(R.id.panview);

        adapter = new PanAdapter();
        wheelMenuView.setAdapter(adapter);

        input_tv = (TextView) findViewById(R.id.input_tv);
        skbContainer = (SkbContainer) findViewById(R.id.skbContainer);
        skbContainer.setFocusable(true);
        skbContainer.setFocusableInTouchMode(true);
        // 设置属性(默认是不移动的选中边框)
        setSkbContainerMove();
        skbContainer.setSkbLayout(R.xml.skb_t9_keys);
        //
        skbContainer.setSelectSofkKeyFront(true); // 设置选中边框最前面.
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
                OPENLOG.D("hasFocus:"+hasFocus);
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

    }

    private void setSkbContainerMove() {
        mOldSoftKey = null;
        skbContainer.setMoveSoftKey(true); // 设置是否移动按键边框.
        RectF rectf = new RectF((int)getResources().getDimension(R.dimen.
                w_80), (int)getResources().getDimension(R.dimen.
                h_80), (int)getResources().getDimension(R.dimen.
                w_80), (int)getResources().getDimension(R.dimen.
                h_80));
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
     * @param softKey
     */
    private void onCommitT9Text(SoftKey softKey) {
        Toast.makeText(SearchKeyBoardActivity.this, "keycode:" + softKey.getKeyCode(), Toast.LENGTH_SHORT).show();
    }

}
