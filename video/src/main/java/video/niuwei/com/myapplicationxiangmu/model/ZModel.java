package video.niuwei.com.myapplicationxiangmu.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by One Dream on 2017/11/28.
 */

public class ZModel {
    public void getData(String url, final ModelInfo modelInfo){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                modelInfo.fail("失败了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                modelInfo.success(response.body().string());
            }
        });
    }
}
