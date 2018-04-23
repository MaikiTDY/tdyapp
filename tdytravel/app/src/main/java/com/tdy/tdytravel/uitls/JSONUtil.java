package com.tdy.tdytravel.uitls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tdy.tdytravel.bean.HomeBean;
import com.tdy.tdytravel.bean.ImageBean;
import com.tdy.tdytravel.bean.ScenicBean;

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
//    public static List<CityEntity> getCitysByJSON(String json){
//        List<CityEntity> datas = null;
//        if (json != null){
//            datas = new ArrayList<CityEntity>();
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                int code = jsonObject.getInt("retcode");
//                if(code == 0){
//                    JSONObject jsonobj = jsonObject.getJSONObject("cities");
//                    for(int i = 0; i < strs.length; i++){
//                        try {
//                            JSONArray jsonArray = jsonobj.getJSONArray(strs[i]);
//                            CityEntity ce = new CityEntity(strs[i], 0);
//                            datas.add(ce);
//
//                            TypeToken<List<CityEntity>> tt = new TypeToken<List<CityEntity>>(){};
//                            List<CityEntity> data = new Gson().fromJson(jsonArray.toString(), tt.getType());
//                            datas.addAll(data);
//                        }catch (Exception e){
//                        }
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return datas;
//    }
//
//
    /**
     * 解析首页资讯列表
     * @param json
     * @return
     */
    public static List<ImageBean> getNewsByJSON(String json){
        List<ImageBean> rlist = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("retcode");
            if(code == 0){
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                TypeToken<List<ImageBean>> tt = new TypeToken<List<ImageBean>>(){};
                rlist = new Gson().fromJson(jsonArray.toString(), tt.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rlist;
    }

    /**
     * by tdy
     * json 解析
     * @param json
     * @return
     */
    public static List<HomeBean> getDetailsBeanJSONData(String json){
        List<HomeBean> listHomeBean = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("status");
            JSONArray homeBeanJsonArray = new JSONArray();

            if (code==1){
                List<HomeBean.DetailsBean> detailsBeans;
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                // 获取数据格式相同的两层
                for(int i=0; i<jsonArray.length(); i++){
                    HomeBean homeBean = new HomeBean();
                    JSONObject homeBeanObj = (JSONObject) jsonArray.get(i);
                    String module_name = homeBeanObj.getString("module_name");
                    if("顶部轮播图".equals(module_name) || "导航菜单".equals(module_name)){
                        JSONArray jsonArrayDetails = homeBeanObj.getJSONArray("details");
                        TypeToken<List<HomeBean.DetailsBean>> typeToken = new TypeToken<List<HomeBean.DetailsBean>>(){};
                        detailsBeans = new Gson().fromJson(jsonArrayDetails.toString(), typeToken.getType());
                        homeBean.details = detailsBeans;
                        listHomeBean.add(homeBean);
                    }
                }
                // 开始逐层解析
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listHomeBean;
    }

    /***
     * 获取json
     * 景点列表
     * @param json
     * @return
     */
    public static List<ScenicBean> getScenicBeans(String json){
        List<ScenicBean> scenicBeans = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String resultStr = jsonObject.getString("result");
            if (resultStr!=null){
                JSONObject resultJson = new JSONObject(resultStr);
                JSONArray jsonArray = resultJson.getJSONArray("rows");
                TypeToken<List<ScenicBean>> typeToken = new TypeToken<List<ScenicBean>>(){};
                scenicBeans = new Gson().fromJson(jsonArray.toString(), typeToken.getType());
                // 开始逐层解析
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scenicBeans;
    }


}
