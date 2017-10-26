package test.bwie.com.zhaoxixiang20171026.Model;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import test.bwie.com.zhaoxixiang20171026.Model.bean.DatasBean;
import test.bwie.com.zhaoxixiang20171026.Model.util.OkhttpUtil;

/**
 * Created by admin on 2017/10/26/026.
 */

public class IModel {
    private Context context;

    public IModel(Context context){
        this.context=context;
    }

    public void get(String url, final ModelListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("type","1");
        OkhttpUtil.getInstance(context).doPost(url, map, new OkhttpUtil.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                DatasBean bean=new Gson().fromJson(result,DatasBean.class);
                listener.onSuccess(bean);
            }
        });
    }
}
