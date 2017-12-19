package video.niuwei.com.myapplicationxiangmu.presenter;

import com.google.gson.Gson;

import video.niuwei.com.myapplicationxiangmu.bean.XBean;
import video.niuwei.com.myapplicationxiangmu.model.ModelInfo;
import video.niuwei.com.myapplicationxiangmu.model.XModel;
import video.niuwei.com.myapplicationxiangmu.view.XView;

/**
 * Created by One Dream on 2017/11/27.
 */

public class XPresenter {
    XView xView;
    private final XModel xModel;

    public XPresenter(XView xView) {
        this.xView = xView;
        xModel = new XModel();
    }
    public void getUrl(String url,String id){
        xModel.getData(url, id, new ModelInfo() {
            @Override
            public void success(String msg) {
                Gson gson=new Gson();
                XBean xBean = gson.fromJson(msg, XBean.class);
                if(xView!= null){
                    xView.show(xBean);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
    public void del(){
        if (xView!=null){
            xView=null;
        }
    }
}
