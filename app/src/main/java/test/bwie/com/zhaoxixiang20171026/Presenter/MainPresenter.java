package test.bwie.com.zhaoxixiang20171026.Presenter;

import android.content.Context;

import java.io.IOException;

import test.bwie.com.zhaoxixiang20171026.Model.IModel;
import test.bwie.com.zhaoxixiang20171026.Model.ModelListener;
import test.bwie.com.zhaoxixiang20171026.Model.bean.DatasBean;
import test.bwie.com.zhaoxixiang20171026.View.IMainListener;

/**
 * Created by admin on 2017/10/26/026.
 */

public class MainPresenter {

    private IMainListener iMainListener;
    private Context context;
    private IModel iModel;

    public MainPresenter(IMainListener iMainListener){
        this.context= (Context) iMainListener;
        this.iMainListener=iMainListener;
        iModel=new IModel(context);
    }

    public void get(){
        String url=iMainListener.getUrl();
        iModel.get(url, new ModelListener() {
            @Override
            public void onSuccess(DatasBean bean) {
                iMainListener.setResult(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
