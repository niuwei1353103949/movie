package video.niuwei.com.myapplicationxiangmu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.XiangqingActivity;
import video.niuwei.com.myapplicationxiangmu.bean.ZBean;

/**
 * Created by One Dream on 2017/11/28.
 */

public class MyZhuantiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZBean.RetBean.ListBean> list;
    private Context context;
    public MyZhuantiAdapter(List<ZBean.RetBean.ListBean> list, FragmentActivity activity) {
        this.list=list;
        this.context=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.recy_zhuanti_layout,null);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyHolder)holder).name.setText(list.get(position).getTitle());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(list.get(position).getPic())
                .setLowResImageRequest(ImageRequest.fromUri(list.get(position).getPic()))
                .setImageRequest(ImageRequest.fromUri(list.get(position).getPic()))
                .setOldController(((MyHolder)holder).sdv.getController())
                .build();
        ((MyHolder)holder).sdv.setController(controller);
        ((MyHolder)holder).sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,XiangqingActivity.class);
                intent.putExtra("id",list.get(position).getDataId());
                System.out.println("66666"+list.get(position).getDataId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends  RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            sdv = (SimpleDraweeView) itemView.findViewById(R.id.zhuanti_sdw);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
