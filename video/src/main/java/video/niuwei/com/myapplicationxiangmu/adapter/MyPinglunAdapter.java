package video.niuwei.com.myapplicationxiangmu.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.bean.PBean;

/**
 * Created by One Dream on 2017/11/28.
 */

public class MyPinglunAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PBean.RetBean.ListBean> list;
    public MyPinglunAdapter(List<PBean.RetBean.ListBean> list, FragmentActivity activity) {
        this.context=activity;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.recy_pinglun_layout,null);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyHolder)holder).name.setText(list.get(position).getPhoneNumber());
        ((MyHolder)holder).data.setText(list.get(position).getTime());
        ((MyHolder)holder).info.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyHolder extends  RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView data;
        private final TextView info;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            data = (TextView) itemView.findViewById(R.id.data);
            info = (TextView) itemView.findViewById(R.id.info);
        }
    }
}
