package test.bwie.com.zhaoxixiang20171026.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.CardBean;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.adapter.MyCardAdapter;
import test.bwie.com.zhaoxixiang20171026.weight.CheckEvent;
import test.bwie.com.zhaoxixiang20171026.weight.MyCountEvent;

public class CardActivity extends AppCompatActivity {

    private RecyclerView cardRlv;
    private CheckBox cardCb;
    private TextView cardNum;
    private TextView cardSum;
    private MyCardAdapter adapter;
    private List<CardBean> list=new ArrayList<>();
    private int sumMoney=0;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initView();
        EventBus.getDefault().register(this);
        adapter.selectAll(cardCb.isChecked());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void allSelect(CheckEvent checkEvent){
        cardCb.setChecked(checkEvent.isChecked());
    }
    @Subscribe
    public void onMyEvent(MyCountEvent myCountEvent){
        if(myCountEvent.isFlag()){
            sumMoney=0;
            count=0;
        }
        int evNum=myCountEvent.getNum();
        int evPrice=myCountEvent.getPrice();
        sumMoney+=evPrice;
        count+=evNum;
        if(sumMoney<0||count<0){
            sumMoney=0;
            count=0;
        }
        cardNum.setText("总共"+count+"件");
        cardSum.setText("共"+sumMoney+"元");
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
        adapter=new MyCardAdapter(CardActivity.this,list);
        cardRlv.setLayoutManager(new LinearLayoutManager(this));
        cardRlv.setAdapter(adapter);
    }
}
