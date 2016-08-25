package com.wangzh.zhihudaily.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wangzh.zhihudaily.bean.LatestListDTO;
import com.wangzh.zhihudaily.bean.ThemeListDTO;
import com.wangzh.zhihudaily.bean.ThemeListItemDTO;
import com.wangzh.zhihudaily.event.ContentEvent;
import com.wangzh.zhihudaily.event.LatestListEvent;
import com.wangzh.zhihudaily.event.ThemeItemListEvent;
import com.wangzh.zhihudaily.event.ThemeListEvent;
import com.wangzh.zhihudaily.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * 获取主题日报消息
     */
    public void getThemeItemList(String themeId){
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_THEMEITEMLIST+themeId , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ThemeListItemDTO themeItemListDTO=themeItemListResponse2DTO(response);
                if (themeItemListDTO!=null){
                    EventBus.getDefault().post(new ThemeItemListEvent(themeItemListDTO));
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
     * 主题日报列表获取数据实体转换
     * @param response
     * @return
     */
    private ThemeListItemDTO themeItemListResponse2DTO(String response){
        //此处处理json数组中image不存在的情况
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("stories");
            for (int i = 0, size = jsonArray.length(); i < size; i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                int type=0,id=0;
                String title="";
                try {
                    type=object.getInt("type");
                    id=object.getInt("id");
                    title=object.getString("title");
                    object.get("images");
                } catch (Exception e) {
                    object.remove("type");
                    object.remove("id");
                    object.remove("title");
                    JSONArray array=new JSONArray();
                    array.put("http://img3.imgtn.bdimg.com/it/u=1086050392,1019818139&fm=206&gp=0.jpg");
                    object.put("images", array);
                    object.put("type",type);
                    object.put("id",id);
                    object.put("title",title);
                }
            }
            response=jsonObject.toString();
        }catch (JSONException e){

        }
        if (!TextUtils.isEmpty(response)){
            ThemeListItemDTO themeItemListDTO=gson.fromJson(response,ThemeListItemDTO.class);
            return  themeItemListDTO;
        }else {
            return null;
        }
    }

    /**
     * 最新信息加载更多（也就是获取前一天的信息）
     */
    public void getLatestBeforeList(String yesterday){
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_LATESTLIST_LOADMORE+yesterday , new Response.Listener<String>() {
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
     * 获取消息内容
     * @param docId
     */
    public void getNewsContent(String docId){
        StringRequest request=new StringRequest(Request.Method.GET,Constants.URL_GETCONTENT+docId,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                EventBus.getDefault().post(new ContentEvent(getShareUrl(response)));
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError",error.toString());
            }
        });
        mQuene.add(request);
    }

    /**
     * 获取正文
     * @param response
     * @return
     */
    private String getShareUrl(String response){
        String value="http://daily.zhihu.com/";
        try {
            JSONObject jsonObject = new JSONObject(response);
            value= jsonObject.getString("share_url");
        }catch (JSONException e){
            Toast.makeText(context,"Json解析出错",Toast.LENGTH_SHORT).show();
        }finally {
            return value;
        }
    }
}
