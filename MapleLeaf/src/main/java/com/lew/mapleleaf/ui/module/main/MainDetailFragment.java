package com.lew.mapleleaf.ui.module.main;

import android.os.Bundle;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.base.BaseFragment;
import com.lew.mapleleaf.databinding.FragmentMainDetailBinding;
import com.lew.mapleleaf.network.RetrofitService;
import com.lew.mapleleaf.ui.module.home.MainDetailFragmentPresenter;
import com.lew.mapleleaf.utils.app.Colors;
import com.lew.mapleleaf.utils.logger.Logger;
import com.lew.mapleleaf.utils.rx.RxHelper;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Richie 2017/4/10.
 */

public class MainDetailFragment extends BaseFragment<MainDetailFragmentPresenter, FragmentMainDetailBinding> {

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
        lazyLoadData(false);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_detail;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void lazyLoadData(boolean isRefresh) {

        Retrofit retrofit = new Retrofit.Builder()  //01:获取Retrofit对象
                .baseUrl("http://localhost:8080/HelloWorld/") //02采用链式结构绑定Base url
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                // 针对rxjava2.x
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);//04获取API接口的实现类的实例对象

        Disposable subscribe = RxHelper.countdown(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logger.d(integer + "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showPageContent();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        showPageContent();
                    }
                });
    }

    private void showPageContent() {
        int index = getArguments().getInt(INDEX, 0);
        Logger.d(index + "");
//        mFragmentContent.setText("这是第 " + index + " 页 ");
    }

//    public void OnClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_start:
////                writeToExternalStorage();
//                //创建okHttpClient对象
//                OkHttpClient mOkHttpClient = new OkHttpClient();
//                //创建一个Request
//                final Request request = new Request.Builder()
//                        .url("http://192.168.1.106:8080/HelloWorld/servlet/MyServlet")
//                        .build();
//                //new call
//                Call call = mOkHttpClient.newCall(request);
//                //请求加入调度
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Logger.d(response.body().string());
//                    }
//                });
//
//                break;
//        }
//    }

//    public void writeToExternalStorage() {
//        File path = Environment.getExternalStorageDirectory();
//        File destFile = new File(path, "strictmode.txt");
//        try {
//            OutputStream output = new FileOutputStream(destFile, true);
//            output.write("测试strictmnode".getBytes());
//            output.flush();
//            output.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
