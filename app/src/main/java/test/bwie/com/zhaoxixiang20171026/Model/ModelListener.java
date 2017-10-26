package test.bwie.com.zhaoxixiang20171026.Model;

import java.io.IOException;

import test.bwie.com.zhaoxixiang20171026.Model.bean.DatasBean;

/**
 * Created by admin on 2017/10/26/026.
 */

public interface ModelListener {

    public void onSuccess(DatasBean bean);

    public void onError(IOException e);
}
