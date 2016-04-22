package com.lew.mapleleaf.network.okhttp.builder;

import java.util.Map;

public interface HasParamsAble {
    OkHttpRequestBuilder params(Map<String, String> params);

    OkHttpRequestBuilder addParams(String key, String val);
}
