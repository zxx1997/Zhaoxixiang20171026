package test.bwie.com.zhaoxixiang20171026.Model.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2017/10/26/026.
 */
//okhttp框架
public class OkhttpUtil {

    private OkHttpClient client;

    private volatile static OkhttpUtil util;

    private Handler handler;

    private Context context;

    private OkhttpUtil(Context context){
        LoggingInterceptor logging=new LoggingInterceptor();
        client=new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        handler=new Handler(Looper.getMainLooper());
        this.context=context;
    }

    public static OkhttpUtil getInstance(Context context){
        if(util==null){
            synchronized (OkhttpUtil.class){
                if(util==null){
                    util=new OkhttpUtil(context);
                }
            }
        }
        return util;
    }

    public void doGet(String url, final FuncJsonString callback){
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("=========TAG==========","解析失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    OnSuccess(response.body().string(),callback);
                }
            }
        });
    }

    public void doPost(String url, Map<String,String> map, final FuncJsonString callback){
        FormBody.Builder from=new FormBody.Builder();
        if(map!=null&&!map.isEmpty()){
            for (Map.Entry<String,String> entry:map.entrySet()){

                from.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody body=from.build();
        Request request=new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("=========TAG==========","解析失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    OnSuccess(response.body().string(),callback);
                }
            }
        });
    }

    //返回json字符串的接口
    public interface FuncJsonString{
        void onResponse(String result);
    }
    //返回字符串
    private void OnSuccess(final String jsonStr, final FuncJsonString callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    try {
                        callback.onResponse(jsonStr);
                    }catch (Exception e){

                    }
                }
            }
        });
    }
}
