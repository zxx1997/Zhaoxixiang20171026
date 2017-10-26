package test.bwie.com.zhaoxixiang20171026.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import test.bwie.com.zhaoxixiang20171026.Model.bean.DatasBean;
import test.bwie.com.zhaoxixiang20171026.R;
import test.bwie.com.zhaoxixiang20171026.View.CardActivity;

/**
 * Created by admin on 2017/10/26/026.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<DatasBean.SongListBean> list;
    //private OnItemClick onItemClick;
    private DisplayImageOptions dio=new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher_round)
            .build();


    /*public interface OnItemClick{
        public void onItem(DatasBean.SongListBean bean,int position);
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }*/

    public MyAdapter(Context context, List<DatasBean.SongListBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rlv_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DatasBean.SongListBean bean=list.get(position);
        MyViewHolder holde= (MyViewHolder) holder;
        ImageLoader.getInstance().displayImage(bean.getPic_small(),holde.iv,dio);
        holde.name.setText(bean.getTitle());
        holde.author.setText(bean.getAuthor());
        holde.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CardActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView name,author;
        private LinearLayout ll;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.item_iv);
            name=itemView.findViewById(R.id.item_name);
            author=itemView.findViewById(R.id.item_author);
            ll=itemView.findViewById(R.id.ll);
        }
    }

}
