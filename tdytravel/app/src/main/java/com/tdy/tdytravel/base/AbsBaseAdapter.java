package com.tdy.tdytravel.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*******************************
 * Created by tangdayi on 2018/4/20.
 * 作者:tangdayi
 * 日期:2018年04月20日13时00分
 * 文件:HomeListViewAdapter
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public abstract class AbsBaseAdapter<T> extends BaseAdapter{

    private Context context;
    private List<T> datas;
    private int resid;

    public AbsBaseAdapter(Context context, int resid){
        this.context = context;
        this.resid = resid;
        datas = new ArrayList<T>();
    }

    public void setDatas(List<T> datas){
        this.datas = datas;

        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(resid,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        //从数据源中获取数据放入相应的控件
        bindDatas(viewHolder, datas.get(position));
        return convertView;
    }

    public abstract void bindDatas(ViewHolder viewHolder, T data);

    /**
     * ViewHolder作用 --> 缓存布局文件中的子控件对象，下次使用该对象不再需要findViewById
     */
    public class ViewHolder{
        private View layoutView;//布局对象
        private Map<Integer, View> mapCache = new HashMap<Integer, View>();

        public ViewHolder(View layoutView){
            this.layoutView = layoutView;
        }

        public View getView(int id){
            if(mapCache.containsKey(id)){
                return mapCache.get(id);
            } else {
                View v = layoutView.findViewById(id);
                mapCache.put(id, v);
                return v;
            }
        }
    }
}
