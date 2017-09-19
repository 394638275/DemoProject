package com.lew.mapleleaf.network;

import com.lew.mapleleaf.beans.PersonBean;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Richie on 2017/8/19
 */

public interface RetrofitService {

    @GET("servlet/MyServlet")
    Observable<Result<List<PersonBean>>> listPersons();
}
