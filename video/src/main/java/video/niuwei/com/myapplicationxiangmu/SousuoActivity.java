package video.niuwei.com.myapplicationxiangmu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.umeng.analytics.MobclickAgent;

import video.niuwei.com.myapplicationxiangmu.adapter.MySousuoAdapter;
import video.niuwei.com.myapplicationxiangmu.bean.SBean;
import video.niuwei.com.myapplicationxiangmu.presenter.SPresenter;
import video.niuwei.com.myapplicationxiangmu.view.SView;

/**
 * Created by One Dream on 2017/11/29.
 */

public class SousuoActivity extends Activity implements SView {

    private SPresenter presenter;
    private RecyclerView sousuoRecy;
    private ImageView image;
    private TextView tvname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(0.5f)
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        presenter = new SPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do", keyword);
        initView();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvname.setText(keyword);
    }

    @Override
    public void show(final SBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MySousuoAdapter mySousuoAdapter = new MySousuoAdapter(bean.getRet().getList(), SousuoActivity.this);
                sousuoRecy.setLayoutManager(new LinearLayoutManager(SousuoActivity.this));
                sousuoRecy.setAdapter(mySousuoAdapter);
                mySousuoAdapter.setOnItemClickListener(new MySousuoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(SousuoActivity.this, XiangqingActivity.class);
                        intent.putExtra("id", bean.getRet().getList().get(position).getDataId());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
        presenter.del();
    }

    private void initView() {
        sousuoRecy = (RecyclerView) findViewById(R.id.sousuo_recy);
        image = (ImageView) findViewById(R.id.image);
        tvname = (TextView) findViewById(R.id.tvname);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
