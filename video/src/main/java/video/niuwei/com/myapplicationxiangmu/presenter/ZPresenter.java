package video.niuwei.com.myapplicationxiangmu.presenter;

import com.google.gson.Gson;

import video.niuwei.com.myapplicationxiangmu.bean.ZBean;
import video.niuwei.com.myapplicationxiangmu.model.ModelInfo;
import video.niuwei.com.myapplicationxiangmu.model.ZModel;
import video.niuwei.com.myapplicationxiangmu.view.ZView;

/**
 * Created by One Dream on 2017/11/28.
 */

public class ZPresenter {
    ZView zView;
    public  ZModel zModel;

    public ZPresenter(ZView zView) {
        this.zView = zView;
        zModel = new ZModel();
    }

    public void getUrl(String url){
        zModel.getData(url, new ModelInfo() {
            @Override
            public void success(String msg) {
                Gson gson=new Gson();
                ZBean bean = gson.fromJson(msg, ZBean.class);
                zView.show(bean);
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    public void del(){
        if (zView!=null){
            zView=null;
        }
    }
}
