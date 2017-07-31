package com.lew.mapleleaf.ui.module.main;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseFragment;
import com.lew.mapleleaf.utils.app.Colors;
import com.lew.mapleleaf.utils.logger.Logger;
import com.lew.mapleleaf.utils.math.Fibonacci;
import com.lew.mapleleaf.utils.rx.RxHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;

/**
 * Created by Richie 2017/4/10.
 */

public class MainDetailFragment extends BaseFragment {

    private static final String INDEX = "index";
    private static final int[] colors = {Colors.FUCHSIA_TRANSLUCENT, Colors.BLUE_TRANSLUCENT, Colors.CHOCOLATE_TRANSLUCENT};

    @BindView(R.id.tv_fragment_content)
    TextView mFragmentContent;
    @BindView(R.id.btn_start)
    Button mStart;

    public static MainDetailFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        MainDetailFragment fragment = new MainDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void lazyLoadData(boolean isRefresh) {
        RxHelper.countdown(3)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        showPageContent();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showPageContent();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.d(integer + "");
                    }
                });
    }

    private void showPageContent() {
        int index = getArguments().getInt(INDEX, 0);
        Logger.d(index + "");
        mFragmentContent.setText("这是第 " + index + " 页 ");
    }

    @OnClick(R.id.btn_start)
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_start:
                writeToExternalStorage();
                break;
        }
    }

    public void writeToExternalStorage() {
        File path = Environment.getExternalStorageDirectory();
        File destFile = new File(path, "strictmode.txt");
        try {
            OutputStream output = new FileOutputStream(destFile, true);
            output.write("测试strictmnode".getBytes());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
