package video.niuwei.com.myapplicationxiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.adapter.MyJingxuanAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.JBean;
import video.niuwei.com.myapplicationxiangmu.presenter.JPresenter;
import video.niuwei.com.myapplicationxiangmu.utils.ImageLoaderBanner;
import video.niuwei.com.myapplicationxiangmu.view.JView;

/**
 * Created by One Dream on 2017/11/9.
 */

public class Jingxuan extends Fragment implements JView {

    private View view;
    private JPresenter presenter;
    private RecyclerView recy;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.jingxuan_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        presenter = new JPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/homePageApi/homePage.do");

    }

    @Override
    public void show(final JBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recy.setLayoutManager(new LinearLayoutManager(getActivity()));
                recy.setAdapter(new MyJingxuanAdapter(bean.getRet().getList(),getActivity()));
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
