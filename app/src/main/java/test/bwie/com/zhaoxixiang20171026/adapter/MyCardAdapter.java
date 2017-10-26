package test.bwie.com.zhaoxixiang20171026.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.CardBean;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.weight.AddDelView;
import test.bwie.com.zhaoxixiang20171026.weight.CheckEvent;
import test.bwie.com.zhaoxixiang20171026.weight.MyCountEvent;

/**
 * Created by admin on 2017/10/26/026.
 */

public class MyCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<CardBean> list;
    private int count=0;

    public MyCardAdapter(Context context,List<CardBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);

        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CardBean bean=list.get(position);
        final MyView myView= (MyView) holder;
        myView.cb.setChecked(bean.isChecked());
        myView.title.setText(bean.getName());
        myView.price.setText(bean.getPrice()+"");
        myView.adv.setOnItemClick(new AddDelView.OnItemClick() {
            @Override
            public void onItemAddClick(int count) {
                if(bean.isChecked()){
                    MyCountEvent myCountEvent=new MyCountEvent();
                    myCountEvent.setNum(1);
                    myCountEvent.setPrice(bean.getPrice());
                    EventBus.getDefault().post(myCountEvent);
                }else{
                    Toast.makeText(context,"请勾选",Toast.LENGTH_LONG).show();
                    myView.adv.setCount();
                }
            }

            @Override
            public void onItemDelClick(int count) {
                if(bean.isChecked()){
                    MyCountEvent myCountEvent=new MyCountEvent();
                    myCountEvent.setNum(-1);
                    myCountEvent.setPrice(-bean.getPrice());
                    EventBus.getDefault().post(myCountEvent);
                }else{
                    Toast.makeText(context,"请勾选",Toast.LENGTH_LONG).show();
                    myView.adv.setCount();
                }
            }
        });
        //复选框监听
        myView.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当前的选中状态
                if(myView.cb.isChecked()){
                    bean.setChecked(true);
                    MyCountEvent myCountEvent=new MyCountEvent();
                    myCountEvent.setNum(1);
                    myCountEvent.setPrice(bean.getPrice());
                    EventBus.getDefault().post(myCountEvent);
                    //判断是否全部勾选
                    if(isAllSelect()){
                        CheckEvent checkEvent=new CheckEvent();
                        checkEvent.setChecked(true);
                        EventBus.getDefault().post(checkEvent);
                    }
                }else{
                    MyCountEvent myCountEvent=new MyCountEvent();
                    myCountEvent.setNum(-1);
                    myCountEvent.setPrice(-bean.getPrice());
                    EventBus.getDefault().post(myCountEvent);
                    bean.setChecked(false);
                    //全选取消
                    CheckEvent checkEvent=new CheckEvent();
                    checkEvent.setChecked(false);
                    EventBus.getDefault().post(checkEvent);
                }
            }
        });
        myView.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(1);
                MyCountEvent myCountEvent=new MyCountEvent();
                myCountEvent.setNum(-1);
                myCountEvent.setPrice(-bean.getPrice());
                EventBus.getDefault().post(myCountEvent);
                notifyDataSetChanged();
            }
        });

    }
    //判断复选框是否全部选中
    public boolean isAllSelect(){
        for (CardBean bean:list){
            if(!bean.isChecked()){
                return false;
            }
        }
        return true;
    }

    //判断全选
    public void selectAll(boolean flag) {
        MyCountEvent mcEvent = new MyCountEvent();
        mcEvent.setFlag(true);
        EventBus.getDefault().post(mcEvent);
        for (CardBean bean : list) {
            if (flag) {
                MyCountEvent mcEvent1 = new MyCountEvent();
                mcEvent1.setPrice(bean.getPrice());
                mcEvent1.setNum(1);
                EventBus.getDefault().post(mcEvent1);
            } else {
                MyCountEvent mcEvent1 = new MyCountEvent();
                mcEvent1.setPrice(-bean.getPrice());
                mcEvent1.setNum(-1);
                EventBus.getDefault().post(mcEvent1);
            }
            bean.setChecked(flag);
            notifyDataSetChanged();
        }


    }


    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class MyView extends RecyclerView.ViewHolder{

        private CheckBox cb;
        private TextView title,price,num,sum_price;
        private AddDelView adv;
        private Button bt;
        public MyView(View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.item_cb);
            title=itemView.findViewById(R.id.item_title);
            price=itemView.findViewById(R.id.item_price);
            num=itemView.findViewById(R.id.item_sum);
            sum_price=itemView.findViewById(R.id.item_sumprice);
            adv=itemView.findViewById(R.id.adv);
            bt=itemView.findViewById(R.id.item_bt);
        }
    }

}
