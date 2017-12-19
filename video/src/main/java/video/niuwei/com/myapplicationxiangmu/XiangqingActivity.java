package video.niuwei.com.myapplicationxiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.superplayer.library.SuperPlayer;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import video.niuwei.com.myapplicationxiangmu.bean.XBean;
import video.niuwei.com.myapplicationxiangmu.fragment.Jianjie;
import video.niuwei.com.myapplicationxiangmu.fragment.Pinglun;
import video.niuwei.com.myapplicationxiangmu.presenter.XPresenter;
import video.niuwei.com.myapplicationxiangmu.view.XView;

/**
 * Created by One Dream on 2017/11/27.
 */

public class XiangqingActivity extends FragmentActivity implements XView {

    private XPresenter presenter;
    private SuperPlayer sp;
    private TabLayout tab;
    private ViewPager vp;
    String[] str = {"简介", "评论"};
    List<Fragment> list;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(0.5f)
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        presenter = new XPresenter(this);
        presenter.getUrl("http://api.svipmovie.com/front/videoDetailApi/videoDetail.do", id);
        initView();

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //getPageTitle这里是得到滑动本页面的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }

    @Override
    public void show(final XBean bean) {
        MobclickAgent.onEvent(XiangqingActivity.this, "title");

        list = new ArrayList<>();
        list.add(new Jianjie().get(bean));
        list.add(new Pinglun());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean.getMsg().equals("视频已下线")) {
                    tv.setVisibility(View.VISIBLE);
                    sp.setVisibility(View.GONE);

                }else{

                    String title = bean.getRet().getTitle();
                    Log.i("5656", "run: "+title);

                    sp.setTitle(bean.getRet().getTitle());
                        sp.play(bean.getRet().getHDURL());
                    //进行数据的适配，由于这里用到了Fragment，所以要用到一个Fragment的管理者
                    vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
                    //设置TabLayout联动
                    tab.setupWithViewPager(vp);

                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();presenter.del();
        sp.stop();

        SwipeBackHelper.onDestroy(this);
    }

    private void initView() {
        sp = (SuperPlayer) findViewById(R.id.sp);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tv = (TextView) findViewById(R.id.tv);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.del();
        sp.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.del();
        sp.stop();
        MobclickAgent.onPause(this);
    }

}
