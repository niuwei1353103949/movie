package video.niuwei.com.myapplicationxiangmu.presenter;

import com.google.gson.Gson;

import video.niuwei.com.myapplicationxiangmu.bean.SBean;
import video.niuwei.com.myapplicationxiangmu.model.ModelInfo;
import video.niuwei.com.myapplicationxiangmu.model.SModel;
import video.niuwei.com.myapplicationxiangmu.view.SView;

/**
 * Created by One Dream on 2017/11/29.
 */

public class SPresenter {
    SView sView;
    private final SModel sModel;

    public SPresenter(SView sView) {
        this.sView = sView;
        sModel = new SModel();
    }

    public void getUrl(String url,String keyword){
        sModel.getData(url, keyword, new ModelInfo() {
            @Override
            public void success(String msg) {
                Gson gson=new Gson();
                SBean sBean = gson.fromJson(msg, SBean.class);
                sView.show(sBean);
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
    public void del(){
        if (sView!=null){
            sView=null;
        }
    }
}
