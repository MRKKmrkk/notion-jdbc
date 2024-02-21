package org.esni.notion.jdbc.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RetryInterceptor implements Interceptor {

    private final int maxRetry; // 最大重试次数
    private int retryNum = 0; // 已经重试的次数

    public RetryInterceptor(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        IOException exception = null;

        while (retryNum <= maxRetry) {
            try {
                response = chain.proceed(request);
//                if (response.isSuccessful()) {
//                    return response; // 如果请求成功，则直接返回结果
//                }
                return response; // 如果请求成功，则直接返回结果
            } catch (IOException e) {
                exception = e;
                retryNum++;
                System.out.println("请求失败，进行第 " + retryNum + " 次重试");
                // 这里可以加入退避算法（比如线性退避、指数退避等）来控制重试的间隔
            }
        }

        // 如果重试了指定次数后仍然失败，抛出最后一次的异常
        if (exception != null) {
            throw exception;
        } else {
            throw new IOException("unknow network error");
        }
    }

}
