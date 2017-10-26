package test.bwie.com.zhaoxixiang20171026.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.CardBean;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.adapter.MyCardAdapter;

public class CardActivity extends AppCompatActivity {

    private RecyclerView cardRlv;
    private CheckBox cardCb;
    private TextView cardNum;
    private TextView cardSum;
    private MyCardAdapter adapter;
    private List<CardBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initView();

    }

    private void initView() {
        cardRlv = (RecyclerView) findViewById(R.id.card_rlv);
        cardCb = (CheckBox) findViewById(R.id.card_cb);
        cardNum = (TextView) findViewById(R.id.card_num);
        cardSum = (TextView) findViewById(R.id.card_sum);
        for (int i=1;i<=5;i++){
            CardBean bean=new CardBean(false,"第"+i+"件商品",i*10);
            list.add(bean);
        }

    }
}
