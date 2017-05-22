package com.tdy.tdytravel.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时37分
 * 文件:AbsBaseAdapter.java
 * 工程:tdytravel
 *
 * listview 适配器
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {
	private Context context;
	private List<T> datas = new ArrayList<T>();
	private int[] resid;

	public AbsBaseAdapter(Context context, int... resid) {
		this.context = context;
		this.resid = resid;
	}

	/**
	 * @return the datas
	 */
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	public void addDatas(List<T> datas) {
		this.datas.addAll(datas);
		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {

		return 0;
	}
	
	

	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#getViewTypeCount()
	 */
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

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView != null) {
			viewHolder = (ViewHolder) convertView.getTag();
		} else {
			convertView = View.inflate(context,
					resid[getItemViewType(position)], null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		bindDatas(viewHolder, datas.get(position));
		return convertView;
	}

	protected abstract void bindDatas(ViewHolder viewHolder, T data);

	public class ViewHolder {
		private View layoutView;
		private Map<Integer, View> viewMap = new HashMap<Integer, View>();

		public ViewHolder(View layoutView) {
			this.layoutView = layoutView;
		}

		public View getView(int id) {
			if (viewMap.containsKey(id)) {
				return viewMap.get(id);
			} else {
				View cacheView = layoutView.findViewById(id);
				viewMap.put(id, cacheView);
				return cacheView;
			}
		}
	}
}
