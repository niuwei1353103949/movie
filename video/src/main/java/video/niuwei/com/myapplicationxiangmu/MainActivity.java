package video.niuwei.com.myapplicationxiangmu;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.jpeng.jpspringmenu.MenuListener;
import com.jpeng.jpspringmenu.SpringMenu;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import video.niuwei.com.myapplicationxiangmu.fragment.Faxian;
import video.niuwei.com.myapplicationxiangmu.fragment.Jingxuan;
import video.niuwei.com.myapplicationxiangmu.fragment.Wode;
import video.niuwei.com.myapplicationxiangmu.fragment.Zhuanti;

public class MainActivity extends FragmentActivity implements View.OnClickListener , MenuListener{
    private CheckNetWork mCheckNetWork =new CheckNetWork();
    private RadioButton jingxuan;
    private RadioButton zhuanti;
    private RadioButton faxian;
    private RadioButton wode;
    private FragmentManager supportFragmentManager;
    private List<Fragment> list;
    private List<RadioButton> radioButtons;
    private SpringMenu menu;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return menu.dispatchTouchEvent(ev);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        menu = new SpringMenu(this, R.layout.layout_home_menu);

        // 内容页变暗的效果
        menu.setFadeEnable(true);

        // 允许菜单开始拖动的距离
        menu.setDragOffset(100);

        menu.setMenuListener(this);

        menu.setDirection(SpringMenu.DIRECTION_LEFT);

        radioButtons = new ArrayList<>();
        radioButtons.add(jingxuan);
        radioButtons.add(zhuanti);
        radioButtons.add(faxian);
        radioButtons.add(wode);

        list = new ArrayList<>();
        list.add(new Jingxuan());
        list.add(new Zhuanti());
        list.add(new Faxian());
        list.add(new Wode());

        jingxuan.setOnClickListener(this);
        zhuanti.setOnClickListener(this);
        faxian.setOnClickListener(this);
        wode.setOnClickListener(this);

        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.add(R.id.frame, list.get(0));
        beginTransaction.add(R.id.frame, list.get(1));
        beginTransaction.add(R.id.frame, list.get(2));
        beginTransaction.add(R.id.frame, list.get(3));



        beginTransaction.show(list.get(0));
        beginTransaction.hide(list.get(1));
        beginTransaction.hide(list.get(2));
        beginTransaction.hide(list.get(3));

        changeFragment(0);
        beginTransaction.commit();
        checkNet();
    }

    private void initView() {
        jingxuan = (RadioButton) findViewById(R.id.jingxuan);
        zhuanti = (RadioButton) findViewById(R.id.zhuanti);
        faxian = (RadioButton) findViewById(R.id.faxian);
        wode = (RadioButton) findViewById(R.id.wode);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.jingxuan:
                beginTransaction.show(list.get(0));
                beginTransaction.hide(list.get(1));
                beginTransaction.hide(list.get(2));
                beginTransaction.hide(list.get(3));
                changeFragment(0);
                break;
            case R.id.zhuanti:
                beginTransaction.show(list.get(1));
                beginTransaction.hide(list.get(0));
                beginTransaction.hide(list.get(2));
                beginTransaction.hide(list.get(3));
                changeFragment(1);
                break;

            case R.id.faxian:
                beginTransaction.show(list.get(2));
                beginTransaction.hide(list.get(0));
                beginTransaction.hide(list.get(1));
                beginTransaction.hide(list.get(3));
                changeFragment(2);
                break;
            case R.id.wode:
                beginTransaction.show(list.get(3));
                beginTransaction.hide(list.get(0));
                beginTransaction.hide(list.get(1));
                beginTransaction.hide(list.get(2));
                changeFragment(3);
                break;

        }
        beginTransaction.commit();
    }
        public void changeFragment(int id){
            for (int i=0;i<list.size();i++){
                if (id==i){
                    radioButtons.get(i).setChecked(true);
                }else{
                    radioButtons.get(i).setChecked(false);
                }
            }
        }

    private void checkNet() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        //网络状态存在并且是已连接状态
        if (info != null && info.isConnected()) {
        } else {
            mCheckNetWork.showNetDialog(MainActivity.this);
        }

    }

    @Override
    public void onMenuOpen() {

    }

    @Override
    public void onMenuClose() {

    }

    @Override
    public void onProgressUpdate(float value, boolean bouncing) {

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



