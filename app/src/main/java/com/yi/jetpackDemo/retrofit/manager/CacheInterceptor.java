package com.yi.jetpackDemo.retrofit.manager;

import android.net.Uri;
import android.text.TextUtils;

import com.yi.jetpackDemo.retrofit.CacheUtilsKt;

import java.io.IOException;
import java.util.Set;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanghuayi on 2018/8/1.
 * todo vs okhttp3.internal.cache 中的cache有什么区别
 */
public class CacheInterceptor implements Interceptor {

    public static final String CACHE_QUERY = "CACHEQUERY";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        String method = originalRequest.method();
        HttpUrl url = originalRequest.url();
        Set<String> queryParameterNames = url.queryParameterNames();
        boolean hasCacheQuery = hasCacheQuery(queryParameterNames);// 是否存在缓存控制字段
        if (method == "GET") {// 仅针对GET请求进行缓存处理，POST请求理论上来说不应该有缓存
            if (hasCacheQuery) {
                if (isCacheEnable(url)) {
                    // 如果设置为了读取缓存，则将请求设置为读缓存（有缓存则读缓存，无缓存则请求服务器）
                    builder.cacheControl(CacheControl.FORCE_CACHE);
                }
                builder.url(CacheUtilsKt.removeCacheQuery(url.toString()));
            }
        }

        Request request = builder.build();
        Response response = chain.proceed(request);
        if (hasCacheQuery) {
            // 如果存在缓存控制，则为该请求增加4周的缓存
            long maxAge = 60 * 60 * 24 * 28; // 设置缓存为4周
            String cacheControl = "public,max-age=$maxAge";

            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .request(createCacheRequest(
                            originalRequest))// 此处生成保存cache用的request（保存cache时需要根据url生成key），生成的request
                    // url中的CACHE_QUERY字段必然为true，保证下次请求只有在CACHE_QUERY为true时才能够获取到缓存
                    .header("Cache-Control", cacheControl)
                    .build();
        }
        return response;
    }

    // 判断当前的请求中是否存在控制缓存的字段
    private boolean hasCacheQuery(Set<String> queryParameterNames) {
        return queryParameterNames.contains(CACHE_QUERY);
    }

    // 判断缓存字段的值是否为true
    private boolean isCacheEnable(HttpUrl url) {
        String value = url.queryParameter(CACHE_QUERY);
        if (!TextUtils.isEmpty(value)) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    // 如果调用了该方法，则必然存在CACHE_QUERY参数
    // 根据原请求生成缓存用的请求
    // 因为在请求过程中在加密时会自动添加_t参数，这导致在获取缓存的时候_t参数不同，无法获取
    // 所以在存储和读取缓存时都需要将_t参数去除
    private Request createCacheRequest(Request source) {
        Uri uri = Uri.parse(source.url().toString());
        String query = uri.getQuery();
        String[] parameters = query.split(CacheUtilsKt.getPARAMETER_SEPARATOR());
        for (String parameter : parameters) {
            if (parameter.startsWith(CACHE_QUERY)) {
                if (parameter.endsWith("false")) {
                    parameter.replace("false", "true");
                }
            }
        }
        Uri.Builder builder = new Uri.Builder().scheme(uri.getScheme()).path(uri.getPath()).authority
                (uri.getAuthority());
        for (String parameter : parameters) {
            String[] split = parameter.split(CacheUtilsKt.getNAME_VALUE_SEPARATOR());
            if (!split[0].startsWith("_t")) {
                builder.appendQueryParameter(split[0], split[1]);
            }
        }

        //parameters.map {
        //  it.split(NAME_VALUE_SEPARATOR)
        //}.filter {
        //  !it[0].startsWith("_t")
        //}.forEach {
        //  builder.appendQueryParameter(it[0], it[1]);
        //}

        return source.newBuilder().url(builder.build().toString()).build();
    }
}
