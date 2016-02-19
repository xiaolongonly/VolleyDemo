package com.xl.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_img;
    private ImageView iv_img2;
    private NetworkImageView networkImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        volley_Get();
        volley_Post();
        loadimage();
        cacheimage();
        netImageViewLoader();
    }

    private void netImageViewLoader() {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueues(),new BitmapCache());
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        networkImageView.setImageUrl(url,imageLoader);
    }

    private void cacheimage() {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueues(),new BitmapCache());
        ImageListener listener = ImageLoader.getImageListener(iv_img2,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(url,listener);
    }

    private void initView() {
        iv_img = (ImageView) findViewById(R.id.iv_img);
        iv_img2 = (ImageView) findViewById(R.id.iv_img2);
        networkImageView = (NetworkImageView) findViewById(R.id.iv_img3);
    }

    private void loadimage() {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        ImageRequest request =new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_img.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iv_img.setImageResource(R.mipmap.ic_launcher);
            }
        });
        MyApplication.getHttpQueues().add(request);
    }

    private void volley_Post() {
        

    }

    private void volley_Get() {
        String url = "http://apis.juhe.cn/mobile/get?phone=18850290882&key=759e239954bd5966ba8cbe8fd91675f9";
        VolleyRequest.RequestGet(this, url, "StringGet", new VolleyInterface(this,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
            @Override
            public void onMySuccess(String result) {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMyError(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueues().cancelAll("Json");
    }

/*    private void volley_Post() {
        String url= "http://apis.juhe.cn/mobile/get?";
//        StringRequest request =new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> hashMap = new HashMap<String,String>();
//                hashMap.put("phone","18850290882");
//                hashMap.put("key","759e239954bd5966ba8cbe8fd91675f9");
//                return hashMap;
//            }
//        };
//        request.setTag("StringPost");//方便在全局队列中寻找
//        MyApplication.getHttpQueues().add(request);
        HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("phone","18850290882");
                hashMap.put("key","759e239954bd5966ba8cbe8fd91675f9");
        JSONObject jsonObject = new JSONObject(hashMap);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        jsonObjectRequest.setTag("json");//方便在全局队列中寻找
        MyApplication.getHttpQueues().add(jsonObjectRequest);
    }

    private void volley_Get() {
        String url= "http://apis.juhe.cn/mobile/get?phone=18850290882&key=759e239954bd5966ba8cbe8fd91675f9";
//        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //数据请求成功
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//            }
//        },new Response.ErrorListener(){
//
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //数据请求失败
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//            }
//        });
//        request.setTag("StringGet");//方便在全局队列中寻找
//        MyApplication.getHttpQueues().add(request);

        JsonRequest jsonRequest =new JsonObjectRequest(Request.Method.GET,url,"" ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        jsonRequest.setTag("json");//方便在全局队列中寻找
        MyApplication.getHttpQueues().add(jsonRequest);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
