package video.niuwei.com.myapplicationxiangmu.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.SousuoActivity;
import video.niuwei.com.myapplicationxiangmu.bean.JBean;
import video.niuwei.com.myapplicationxiangmu.bean.SBean;

/**
 * Created by One Dream on 2017/11/29.
 */

public class MySousuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<SBean.RetBean.ListBean> list;
    private Context context;
    public MySousuoAdapter(List<SBean.RetBean.ListBean> childList, SousuoActivity SousuoActivity ) {
        this.list=childList;
        this.context=SousuoActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.sousuo_recy_layout,null);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faxian_recy_layout, parent, false);
        MyHolder myHolder=new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyHolder)holder).name.setText(list.get(position).getTitle());

        ((MyHolder)holder).airtime.setText("上线日期："+list.get(position).getAirTime()+"");
        ((MyHolder)holder).duration.setText("时长："+list.get(position).getDuration());
        ((MyHolder)holder).description.setText("简介："+list.get(position).getDescription());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(list.get(position).getPic())
                .setLowResImageRequest(ImageRequest.fromUri(list.get(position).getPic()))
                .setImageRequest(ImageRequest.fromUri(list.get(position).getPic()))
                .setOldController(((MyHolder)holder).sdv.getController())
                .build();

        ((MyHolder)holder).sdv.setController(controller);

        ((MyHolder)holder).description.setMaxLines(3);
        ((MyHolder)holder).description.setEllipsize(TextUtils.TruncateAt.END);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    class MyHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView airtime;
        private final TextView duration;
        private final TextView description;
        private final SimpleDraweeView sdv;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.fx_name);
            airtime = (TextView)itemView.findViewById(R.id.airTime);
            duration = (TextView)itemView.findViewById(R.id.duration);
            description = (TextView)itemView.findViewById(R.id.description);
            sdv = itemView.findViewById(R.id.faxian_recy_sdv);
        }
    }
    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
