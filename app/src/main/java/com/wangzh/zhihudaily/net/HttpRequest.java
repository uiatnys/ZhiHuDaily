package com.wangzh.zhihudaily.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangzh.zhihudaily.bean.LatestListDTO;
import com.wangzh.zhihudaily.bean.ThemeListDTO;
import com.wangzh.zhihudaily.event.LatestListEvent;
import com.wangzh.zhihudaily.event.ThemeListEvent;
import com.wangzh.zhihudaily.utils.Constants;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by WangZH on 2016/8/19.
 */
public class HttpRequest {
    private Context context;
    private com.android.volley.RequestQueue mQuene;
    private Gson gson;

    public HttpRequest(Context context) {
        this.context = context;
        this.mQuene = Volley.newRequestQueue(context);
        gson=new Gson();
    }

    /**
     * 获取主题列表
     */
    public void getThemeList(){
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_THEMELIST , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ThemeListDTO themeListDTO=themeListResponse2DTO(response);
                if (themeListDTO!=null){
                    EventBus.getDefault().post(new ThemeListEvent(themeListDTO));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError",error.toString());
            }
        });
        mQuene.add(request);
    }

    /**
     * 主题列表实体处理
     * @param response
     * @return
     */
    private ThemeListDTO themeListResponse2DTO(String response){
        if (!TextUtils.isEmpty(response)){
            ThemeListDTO themeListDTO=gson.fromJson(response,ThemeListDTO.class);
            return  themeListDTO;
        }else {
            return null;
        }
    }

    /**
     * 获取最新消息
     */
    public void getLatestList(){
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_LATESTLIST , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LatestListDTO latestListDTO=latestListResponse2DTO(response);
                if (latestListDTO!=null){
                    EventBus.getDefault().post(new LatestListEvent(latestListDTO));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError",error.toString());
            }
        });
        mQuene.add(request);
    }

    /**
     * 最新消息列表实体处理
     * @param response
     * @return
     */
    private LatestListDTO latestListResponse2DTO(String response){
        if (!TextUtils.isEmpty(response)){
            LatestListDTO latestListDTO=gson.fromJson(response,LatestListDTO.class);
            return  latestListDTO;
        }else {
            return null;
        }
    }
}
