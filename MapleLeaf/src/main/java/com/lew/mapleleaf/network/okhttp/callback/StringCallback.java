package com.lew.mapleleaf.network.okhttp.callback;

import okhttp3.Response;

import java.io.IOException;

public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

}
