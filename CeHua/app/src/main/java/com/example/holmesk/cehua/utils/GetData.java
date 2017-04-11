package com.example.holmesk.cehua.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 作者：holmes k
 * 时间：2017.04.10 19:22
 */


public class GetData {

    private OnGetDataListener onGetDataListener;

    public void getDataFromNet(String path, String uriType) {

        RequestParams params = new RequestParams(path);
        params.addQueryStringParameter("uri", uriType);
        // 默认缓存存活时间, 单位:毫秒（如果服务器没有返回有效的max-age或Expires则参考）
        params.setCacheMaxAge(1000 * 60);

        x.http().get(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    onGetDataListener.getData(result);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    public void setData(OnGetDataListener onGetDataListener) {
        this.onGetDataListener = onGetDataListener;
    }

    public interface OnGetDataListener {

        public void getData(String result);

    }

}
