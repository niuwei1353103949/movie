package video.niuwei.com.myapplicationxiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.adapter.MyJianjieAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.XBean;

/**
 * Created by One Dream on 2017/11/27.
 */

public class Jianjie extends Fragment {
    private TextView jianjie;
    private View view;
    private RecyclerView jianjieRecy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.jianjie_layout, null);
        initView();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        Bundle arguments = getArguments();
        XBean bean = arguments.getParcelable("bean");
        jianjie.setText(bean.getRet().getDescription());

        MyJianjieAdapter myJianjieAdapter = new MyJianjieAdapter(bean.getRet(), getActivity());
        jianjieRecy.setLayoutManager(new GridLayoutManager(getActivity(),3));
        jianjieRecy.setAdapter(myJianjieAdapter);

    }

    public Fragment get(XBean bean) {
        Jianjie jianjie = new Jianjie();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        jianjie.setArguments(bundle);
        return jianjie;
    }

    private void initView() {
        jianjie = (TextView) view.findViewById(R.id.jianjie);
        jianjieRecy = (RecyclerView) view.findViewById(R.id.jianjie_recy);
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
