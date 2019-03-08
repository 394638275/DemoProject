package com.lew.mapleleaf.ui.module.main;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.base.BaseFragment;
import com.lew.mapleleaf.network.RetrofitService;
import com.lew.mapleleaf.utils.app.Colors;
import com.lew.mapleleaf.utils.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Richie 2017/4/10.
 */

public class MainDetailFragment extends BaseFragment {

    private static final String INDEX = "index";
    private static final int[] colors = {Colors.FUCHSIA_TRANSLUCENT, Colors.BLUE_TRANSLUCENT, Colors.CHOCOLATE_TRANSLUCENT};

    RetrofitService service;

    public static MainDetailFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        MainDetailFragment fragment = new MainDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_detail;
    }

    //    @Override
//    protected int attachLayoutRes() {
//        return R.layout.fragment_main_detail;
//    }
//
//    @Override
//    protected void initInjector() {
//
//    }
//
//    @Override
//    protected void initViews(View rootView) {
//
//    }

    @SuppressWarnings("unchecked")
    @Override
    protected void lazyLoadData(boolean isRefresh) {

//        Retrofit retrofit = new Retrofit.Builder()  //01:获取Retrofit对象
//                .baseUrl("http://localhost:8080/HelloWorld/") //02采用链式结构绑定Base url
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                // 针对rxjava2.x
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        service = retrofit.create(RetrofitService.class);//04获取API接口的实现类的实例对象

//        RxHelper.countdown(3)
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        showPageContent();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        showPageContent();
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Logger.d(integer + "");
//                    }
//                });
    }

    private void showPageContent() {
        int index = getArguments().getInt(INDEX, 0);
        Logger.d(index + "");
//        mFragmentContent.setText("这是第 " + index + " 页 ");
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
//                writeToExternalStorage();
                //创建okHttpClient对象
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //创建一个Request
                final Request request = new Request.Builder()
                        .url("http://192.168.1.106:8080/HelloWorld/servlet/MyServlet")
                        .build();
                //new call
                Call call = mOkHttpClient.newCall(request);
                //请求加入调度
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Logger.d(response.body().string());
                    }
                });

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
