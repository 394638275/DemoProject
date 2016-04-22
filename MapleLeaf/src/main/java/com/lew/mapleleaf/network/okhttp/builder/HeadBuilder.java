package com.lew.mapleleaf.network.okhttp.builder;

import com.lew.mapleleaf.network.httputils.OkHttpUtils;
import com.lew.mapleleaf.network.okhttp.request.OtherRequest;
import com.lew.mapleleaf.network.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
