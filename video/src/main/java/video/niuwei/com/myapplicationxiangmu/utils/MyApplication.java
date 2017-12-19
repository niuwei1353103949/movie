package video.niuwei.com.myapplicationxiangmu.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import video.niuwei.com.myapplicationxiangmu.R;

/**
 * Created by One Dream on 2017/11/27.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
//        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
//                .memoryCacheSize(2*1024*1024)
//                .diskCacheSize(50*1024*1024)
//                .memoryCacheExtraOptions(400,800)
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .build();
//        ImageLoader.getInstance().init(config);
    }
//    public DisplayImageOptions options(){
//
//        DisplayImageOptions options=new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_launcher)
//                .showImageOnFail(R.mipmap.ic_launcher)
//                .showImageForEmptyUri(R.mipmap.ic_launcher)
//                .build();
//        return options;

//    }
}

