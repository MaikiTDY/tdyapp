package com.tdy.tdytravel.uitls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.model.CityEntity;
import com.qf.model.NewsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析
 */
public class JSONUtil {

    private static String[] strs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 解析城市列表JSON
     * @param json
     * @return
     *
     * List<CityEntity>
     *     CityEntity: cityname : A type = 0
     *     CityEntity: cityname : 北京 type = 1
     *     CityEntity: cityname : 上海 type = 1
     *     CityEntity: cityname : 广州 type = 1
     *
     */
    public static List<CityEntity> getCitysByJSON(String json){
        List<CityEntity> datas = null;
        if (json != null){
            datas = new ArrayList<CityEntity>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                int code = jsonObject.getInt("retcode");
                if(code == 0){
                    JSONObject jsonobj = jsonObject.getJSONObject("cities");
                    for(int i = 0; i < strs.length; i++){
                        try {
                            JSONArray jsonArray = jsonobj.getJSONArray(strs[i]);
                            CityEntity ce = new CityEntity(strs[i], 0);
                            datas.add(ce);

                            TypeToken<List<CityEntity>> tt = new TypeToken<List<CityEntity>>(){};
                            List<CityEntity> data = new Gson().fromJson(jsonArray.toString(), tt.getType());
                            datas.addAll(data);
                        }catch (Exception e){
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }


    /**
     * 解析首页资讯列表
     * @param json
     * @return
     */
    public static List<NewsEntity> getNewsByJSON(String json){
        List<NewsEntity> rlist = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("retcode");
            if(code == 0){
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                TypeToken<List<NewsEntity>> tt = new TypeToken<List<NewsEntity>>(){};
                rlist = new Gson().fromJson(jsonArray.toString(), tt.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rlist;
    }
}
