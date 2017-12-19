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
import video.niuwei.com.myapplicationxiangmu.bean.XBean;

/**
 * Created by One Dream on 2017/11/28.
 */

public class MyJianjieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private XBean.RetBean list;
    private Context context;
    public MyJianjieAdapter(XBean.RetBean childList, FragmentActivity activity) {
        this.list=childList;
        this.context=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.jianjie_recy_layout,null);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(list.getList().get(0).getChildList().get(position).getPic())
                .setLowResImageRequest(ImageRequest.fromUri(list.getList().get(0).getChildList().get(position).getPic()))
                .setImageRequest(ImageRequest.fromUri(list.getList().get(0).getChildList().get(position).getPic()))
                .setOldController(((MyHolder)holder).sdv.getController())
                .build();
        ((MyHolder)holder).sdv.setController(controller);
        ((MyHolder)holder).name.setText(list.getList().get(0).getChildList().get(position).getTitle());
        ((MyHolder)holder).sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, XiangqingActivity.class);
                intent.putExtra("id",list.getList().get(0).getChildList().get(position).getDataId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.getList().get(0).getChildList().size();
    }

    class MyHolder extends  RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            sdv = (SimpleDraweeView) itemView.findViewById(R.id.jianjie_sdw);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }
}
