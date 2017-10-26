package test.bwie.com.zhaoxixiang20171026.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by admin on 2017/10/26/026.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration conf=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(conf);
    }
}
