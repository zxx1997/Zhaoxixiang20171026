package test.bwie.com.zhaoxixiang20171026.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.CardBean;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.weight.AddDelView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

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
        public MyView(View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.item_cb);
            title=itemView.findViewById(R.id.item_title);
            price=itemView.findViewById(R.id.item_price);
            num=itemView.findViewById(R.id.item_sum);
        }
    }

}
