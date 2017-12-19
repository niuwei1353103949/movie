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

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.adapter.MyPinglunAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.PBean;
import video.niuwei.com.myapplicationxiangmu.presenter.PPresenter;
import video.niuwei.com.myapplicationxiangmu.view.PView;

/**
 * Created by One Dream on 2017/11/27.
 */

public class Pinglun extends Fragment implements PView{

    private View view;
    private RecyclerView pinglunRecy;
    private PPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.pinglun_layout, null);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new PPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=CMCC_00000000000000001_621653189");
    }

    private void initView() {
        pinglunRecy = (RecyclerView)view.findViewById(R.id.pinglun_recy);
    }

    @Override
    public void show(final PBean bean) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyPinglunAdapter myPinglunAdapter=new MyPinglunAdapter( bean.getRet().getList(),getActivity());
                pinglunRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                pinglunRecy.setAdapter(myPinglunAdapter);
            }
        });
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
