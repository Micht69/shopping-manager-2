package com.micht.shoppingmanager;

import java.util.TreeSet;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.micht.shoppingmanager.bean.BuyItemBean;
import com.micht.shoppingmanager.bean.ListBean;
import com.micht.shoppingmanager.bean.SectionBean;

public class ShoppingManagerActivity extends Activity {
	static final ListBean listBean = new ListBean();
	static {
		SectionBean sec = null;
		BuyItemBean itm = null;
		for (int s=4; s>0; s--) {
			// Creation de la section
			sec = new SectionBean("Section "+s, s);
			sec.id = s;

			TreeSet<BuyItemBean> itmList = new TreeSet<BuyItemBean>();
			int maxItm = Math.max(5, 2 + (new Double(8*Math.random())).intValue());
			for (int i=1; i<maxItm; i++) {
				// Creation de l'item
				itm = new BuyItemBean("Item "+s+"."+i, sec, i%2==0 ? 1 : 2);
				itm.id = 10*s+i;

				itmList.add(itm);
			}

			listBean.items.put(sec, itmList);
		}
	}


	protected ExpandableListView listView = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (ExpandableListView) findViewById(R.id.list);

		listView.setAdapter(new ListBeanListAdapter(this, R.layout.list_item, R.layout.list_section, listBean));
		for (int i=0; i<listView.getExpandableListAdapter().getGroupCount(); i++) { listView.expandGroup(i); }

		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Object obj = listView.getAdapter().getItem(position);
				if (obj instanceof BuyItemBean) {
					BuyItemBean bItm = (BuyItemBean)obj;
					TextView tView = ((TextView) view);

					if (bItm.buyed) {
						bItm.buyed = false;
						tView.setPaintFlags(tView.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
					} else {
						bItm.buyed = true;
						tView.setPaintFlags(tView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

						String msg = getApplicationContext().getString(R.string.msg_item_buyed, bItm.name);
						Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}