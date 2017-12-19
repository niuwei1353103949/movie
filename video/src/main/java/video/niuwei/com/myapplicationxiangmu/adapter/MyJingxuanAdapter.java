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
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.XiangqingActivity;
import video.niuwei.com.myapplicationxiangmu.bean.JBean;
import video.niuwei.com.myapplicationxiangmu.utils.ImageLoaderBanner;

/**
 * Created by One Dream on 2017/11/27.
 */

public class MyJingxuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JBean.RetBean.ListBean> list;
    private Context context;
    private final  static  int TYPE_ONE=0;
    private final  static  int TYPE_TWO=1;
    private List<String> imageList;

    public MyJingxuanAdapter(List<JBean.RetBean.ListBean> list, FragmentActivity activity) {
        this.list=list;
        this.context=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==TYPE_ONE){
            View view=View.inflate(context,R.layout.recy_jingxuan_layout1,null);
            MyHolder1 myHolder1=new MyHolder1(view);
            return myHolder1;
        }else{
            View view=View.inflate(context,R.layout.recy_jingxuan_layout2,null);
            MyHolder myHolder=new MyHolder(view);
            return myHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type=getItemViewType(position);
        if (type==TYPE_ONE){
            ((MyHolder1)holder).banner.setImageLoader(new ImageLoaderBanner());
            imageList = new ArrayList<>();
            for (int i = 0; i < list.get(0).getChildList().size(); i++) {
                imageList.add(list.get(0).getChildList().get(i).getPic());
            }
            ((MyHolder1)holder).banner.setImages(imageList);
            ((MyHolder1)holder).banner.start();
        }else{
            ((MyHolder)holder).title.setText(list.get(4).getChildList().get(position).getTitle());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(list.get(4).getChildList().get(position).getPic())
                    .setLowResImageRequest(ImageRequest.fromUri(list.get(4).getChildList().get(position).getPic()))
                    .setImageRequest(ImageRequest.fromUri(list.get(4).getChildList().get(position).getPic()))
                    .setOldController(((MyHolder)holder).sdw.getController())
                    .build();
            ((MyHolder)holder).sdw.setController(controller);
            ((MyHolder)holder).sdw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,XiangqingActivity.class);
                    intent.putExtra("id",list.get(4).getChildList().get(position).getDataId());
                    context.startActivity(intent);
                }
            });
        }

//        ImageLoader.getInstance().displayImage(list.get(position).getPic(),((MyHolder)holder).image,new MyApplication().options());
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  MyHolder extends  RecyclerView.ViewHolder{

        private final SimpleDraweeView sdw;
        private final TextView title;

        public MyHolder(View itemView) {
            super(itemView);
            sdw = (SimpleDraweeView) itemView.findViewById(R.id.sdw);
            title = (TextView) itemView.findViewById(R.id.text);

        }
    }
    class  MyHolder1 extends RecyclerView.ViewHolder{

        private final Banner banner;

        public MyHolder1(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.myBanner);
        }
    }
}
