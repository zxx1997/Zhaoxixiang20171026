package test.bwie.com.zhaoxixiang20171026.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.zhaoxixiang20171026.R;

/**
 * Created by admin on 2017/10/26/026.
 */

public class AddDelView extends LinearLayout{

    private TextView num;
    private int count=1;
    private OnItemClick onItemClick;


    public interface OnItemClick {
        public void onItemAddClick(int count);

        public void onItemDelClick(int count);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public AddDelView(Context context) {
        super(context, null);
    }
    public AddDelView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.add_del,this);
        TextView add=findViewById(R.id.add);
        TextView del=findViewById(R.id.del);
        num=findViewById(R.id.num);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText(++count+"");
                onItemClick.onItemAddClick(1);
            }
        });
        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>1){
                    count=--count;
                    onItemClick.onItemDelClick(-1);
                }else{
                    Toast.makeText(context,"最小数量为1",Toast.LENGTH_LONG).show();
                }
                num.setText(count>=1?count+"":1+"");
            }
        });
    }
    //获取商品数量
    public int getCount(){
        return count;
    }


    public void setCount(){
        count=1;
        num.setText(count+"");
    }
}
