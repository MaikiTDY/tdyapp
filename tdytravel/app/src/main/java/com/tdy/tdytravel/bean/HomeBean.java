package com.tdy.tdytravel.bean;

import java.util.List;

/*******************************
 * Created by tangdayi on 2018/4/19.
 * 作者:tangdayi
 * 日期:2018年04月19日13时10分
 * 文件:HomeBean
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class HomeBean {

    public String module_id;
    public String module_type;
    public List<ParamBean> param;
    public List<DetailsBean> details;
    public String module_name;


    public class ParamBean{
        public String p_value;
        public String p_key;

    }

    public class DetailsBean{
        public String urldesc;
        public String link_url;
        public String id;
        public String pic;
        public String title;

    }
}
