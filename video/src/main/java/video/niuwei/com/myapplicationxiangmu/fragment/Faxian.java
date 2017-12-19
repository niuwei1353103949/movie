package video.niuwei.com.myapplicationxiangmu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.umeng.analytics.MobclickAgent;

import video.niuwei.com.myapplicationxiangmu.R;
import video.niuwei.com.myapplicationxiangmu.SousuoActivity;
import video.niuwei.com.myapplicationxiangmu.XiangqingActivity;
import video.niuwei.com.myapplicationxiangmu.adapter.MyFaxianAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.JBean;
import video.niuwei.com.myapplicationxiangmu.bean.PBean;
import video.niuwei.com.myapplicationxiangmu.bean.SBean;
import video.niuwei.com.myapplicationxiangmu.presenter.JPresenter;
import video.niuwei.com.myapplicationxiangmu.presenter.SPresenter;
import video.niuwei.com.myapplicationxiangmu.view.JView;
import video.niuwei.com.myapplicationxiangmu.view.PView;
import video.niuwei.com.myapplicationxiangmu.view.SView;


/**
 * Created by One Dream on 2017/11/9.
 */

public class Faxian extends Fragment implements JView{
    private View view;
    private JPresenter presenter;
    private EditText edit;
    private RecyclerView faxianRecy;
    private MyFaxianAdapter myFaxianAdapterm;
    private Button btn;
    private SPresenter sPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.faxian_layout, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        presenter = new JPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/homePageApi/homePage.do");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SousuoActivity.class);
                intent.putExtra("keyword",edit.getText().toString());
                startActivity(intent);
            }
        });
    }


    @Override
    public void show(final JBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myFaxianAdapterm = new MyFaxianAdapter(bean.getRet().getList().get(3).getChildList(), getActivity());
                faxianRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                faxianRecy.setAdapter(myFaxianAdapterm);
                myFaxianAdapterm.setOnItemClickListener(new MyFaxianAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                        intent.putExtra("id", bean.getRet().getList().get(3).getChildList().get(position).getDataId());
                        getActivity().startActivity(intent);
                    }
                });
            }
        });


    }

    private void initView() {
        edit = (EditText) view.findViewById(R.id.one_edit);
        faxianRecy = (RecyclerView) view.findViewById(R.id.faxian_recy);
        btn = (Button) view.findViewById(R.id.btn);
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
