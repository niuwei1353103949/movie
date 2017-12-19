package video.niuwei.com.myapplicationxiangmu.presenter;

import com.google.gson.Gson;

import video.niuwei.com.myapplicationxiangmu.bean.JBean;
import video.niuwei.com.myapplicationxiangmu.model.JModel;
import video.niuwei.com.myapplicationxiangmu.model.ModelInfo;
import video.niuwei.com.myapplicationxiangmu.view.JView;

/**
 * Created by One Dream on 2017/11/27.
 */

public class JPresenter {
    JView jView;
    private final JModel jModel;

    public JPresenter(JView jView) {
        this.jView = jView;
        jModel = new JModel();
    }

    public void getUrl(String url){
        jModel.getData(url, new ModelInfo() {
            @Override
            public void success(String msg) {
                Gson gson=new Gson();
                JBean jBean = gson.fromJson(msg, JBean.class);
                jView.show(jBean);
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
    public void del(){
        if (jView!=null){
            jView=null;
        }
    }
}
