package com.lew.mapleleaf.ui.fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseFragment;
import com.lew.mapleleaf.ui.activity.BaseWebViewActivity;

/**
 * Created by richie 2016/5/21.
 */
public class AndroidDesignFragment extends BaseFragment {
    private TextInputLayout mInputLayout;
    private FloatingActionButton mButton;
    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_android_design;
    }

    @Override
    protected void initView() {
        mInputLayout = findView(R.id.text_input_layout);
        mInputLayout.setCounterEnabled(true);
        mInputLayout.setCounterMaxLength(5);
        mButton = findView(R.id.float_button);

        EditText et_content = mInputLayout.getEditText();
        if (et_content == null){
            return;
        }
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //检测错误输入，当输入错误时，hint会变成红色并提醒
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override public void afterTextChanged(Editable editable) {
                //检查实际是否匹配，由自己实现
                if (checkType(editable.toString())) {
                    mInputLayout.setErrorEnabled(true);
                    mInputLayout.setError("长度不能超过5！");
                } else {
                    mInputLayout.setErrorEnabled(false);
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BaseWebViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkType(String s) {
        return s.length() > 5;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected void initData() {

    }
}
