package com.tdy.tdytravel.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
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
/**
 * 多布局自定义适配器封装 -- 要求用这个适配器的实体类（T）必须有一个type的字段
 */
public abstract class AbsMoreBaseAdapter<T> extends BaseAdapter{

    private Context context;
    private List<T> datas;
    private int[] resid;

    /**
     * 布局文件的ID 必须和类型一一对应  R.layout.item1 0  R.layout.itme2 1
     * @param context
     * @param resid
     */
    public AbsMoreBaseAdapter(Context context, int... resid){
        this.context = context;
        this.resid = resid;
        datas = new ArrayList<T>();
    }

    public void setDatas(List<T> datas){
        if(datas != null){
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    public void addDatas(List<T> datas){
        if(datas != null) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 多布局中 这个方法的返回类型 必须为 0 ~ (getViewTypeCount() - 1)
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        T t = datas.get(position);
        Class c = t.getClass();
        try {
            Field field = c.getDeclaredField("moduleType");
            field.setAccessible(true);
            return field.getInt(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return resid.length;
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
            convertView = LayoutInflater.from(context).inflate(
                    resid[getItemViewType(position)],parent,false);
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
