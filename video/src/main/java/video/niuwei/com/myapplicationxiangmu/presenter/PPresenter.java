package video.niuwei.com.myapplicationxiangmu.presenter;

import com.google.gson.Gson;

import video.niuwei.com.myapplicationxiangmu.bean.PBean;
import video.niuwei.com.myapplicationxiangmu.model.ModelInfo;
import video.niuwei.com.myapplicationxiangmu.model.PModel;
import video.niuwei.com.myapplicationxiangmu.view.PView;

/**
 * Created by One Dream on 2017/11/28.
 */

public class PPresenter {
    PView pView;
    private final PModel pModel;

    public PPresenter(PView pView) {
        this.pView = pView;
        pModel = new PModel();
    }

    public void getUrl(String url){
        pModel.getData(url, new ModelInfo() {
            @Override
            public void success(String msg) {
                Gson gson=new Gson();
                PBean pBean = gson.fromJson(msg, PBean.class);
                if(pView!=null){
                    pView.show(pBean);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
    public void del(){
        if (pView!=null){
            pView=null;
        }
    }
}
