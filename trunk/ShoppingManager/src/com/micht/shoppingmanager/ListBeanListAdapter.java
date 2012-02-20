package com.micht.shoppingmanager;

import java.util.TreeMap;
import java.util.TreeSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.micht.shoppingmanager.bean.BuyItemBean;
import com.micht.shoppingmanager.bean.ListBean;
import com.micht.shoppingmanager.bean.SectionBean;

public class ListBeanListAdapter extends BaseExpandableListAdapter {

	private int itemViewResourceId;
	private int sectionViewResourceId;
	private TreeMap<SectionBean, TreeSet<BuyItemBean>> items;
	private SectionBean[] groups;
	private LayoutInflater mInflater;

	private class ViewHolder {
		TextView text;
	}


	public ListBeanListAdapter(Context context, int itemViewResourceId, int sectionViewResourceId, ListBean listBean) {
		this.itemViewResourceId = itemViewResourceId;
		this.sectionViewResourceId = sectionViewResourceId;

		this.items = listBean.items;
		groups = this.items.keySet().toArray(new SectionBean[this.items.size()]);

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	public boolean hasStableIds() {
		return true;
	}


	//==================================
	// Group methods
	//==================================

	public int getGroupCount() {
		return this.items.size();
	}

	public SectionBean getGroup(int groupPosition) {
		return this.groups[groupPosition];
	}

	public long getGroupId(int groupPosition) {
		return getGroup(groupPosition).id;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = mInflater.inflate(this.sectionViewResourceId, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.txt_section_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(getGroup(groupPosition).getName());

		return convertView;
	}

	//==================================
	// Children methods
	//==================================

	public int getChildrenCount(int groupPosition) {
		return this.items.get(getGroup(groupPosition)).size();
	}

	public BuyItemBean getChild(int groupPosition, int childPosition) {
		return (BuyItemBean) this.items.get(getGroup(groupPosition)).toArray()[childPosition];
	}

	public long getChildId(int groupPosition, int childPosition) {
		return getChild(groupPosition, childPosition).id;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// Always
		return true;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView,
			ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = mInflater.inflate(this.itemViewResourceId, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.txt_item_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(getChild(groupPosition, childPosition).getName());

		return convertView;
	}







	/*
	@Override
	public boolean isEnabled(int position) {
		CommonBean obj = getItem(position);
		return (obj instanceof ItemBean);
	}

	public int getCount() {
		return items.size();
	}

	public CommonBean getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			if (isEnabled(position)) {
				convertView = mInflater.inflate(this.itemViewResourceId, parent, false);
				holder.text = (TextView) convertView.findViewById(R.id.txt_item_name);
				convertView.setTag(holder);
			} else {
				convertView = mInflater.inflate(this.sectionViewResourceId, parent, false);
				holder.text = (TextView) convertView.findViewById(R.id.txt_section_name);
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(getItem(position).getName());

		return convertView;
	}
	 */
}
