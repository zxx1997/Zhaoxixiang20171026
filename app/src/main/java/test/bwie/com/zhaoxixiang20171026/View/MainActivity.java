package test.bwie.com.zhaoxixiang20171026.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.DatasBean;
import test.bwie.com.zhaoxixiang20171026.Presenter.MainPresenter;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity implements IMainListener{

    private RecyclerView rlv;
    private DatasBean bean;
    private List<DatasBean.SongListBean> list;
    private MyAdapter adapter;
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new MainPresenter(this);
        presenter.get();
        initView();
    }

    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        list=new ArrayList<>();

    }

    @Override
    public String getUrl() {
        return "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&size=10&offset=0 ";
    }

    @Override
    public void setResult(DatasBean b) {
        //bean=b;
        list=b.getSong_list();
        adapter=new MyAdapter(MainActivity.this,list);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter);
    }
}
