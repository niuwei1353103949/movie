package video.niuwei.com.myapplicationxiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.adapter.MyZhuantiAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.ZBean;
import video.niuwei.com.myapplicationxiangmu.presenter.ZPresenter;
import video.niuwei.com.myapplicationxiangmu.view.ZView;

/**
 * Created by One Dream on 2017/11/9.
 */

public class Zhuanti extends Fragment implements ZView {

    private RecyclerView recy;
    private View view;
    private ZPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.zhuanti_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        presenter = new ZPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=9");
    }

    @Override
    public void show(final ZBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyZhuantiAdapter myZhuantiAdapter=new MyZhuantiAdapter(bean.getRet().getList(),getActivity());
                recy.setLayoutManager(new GridLayoutManager(getActivity(),3));
                recy.setAdapter(myZhuantiAdapter);
            }
        });
    }

    private void initView() {
        recy = (RecyclerView) view.findViewById(R.id.recy);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.del();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getActivity().getLocalClassName());
    }
}
