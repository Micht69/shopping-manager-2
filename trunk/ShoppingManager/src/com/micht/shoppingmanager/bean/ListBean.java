/**
 * 
 */
package com.micht.shoppingmanager.bean;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Micht
 *
 */
public class ListBean {
	public TreeMap<SectionBean, TreeSet<BuyItemBean>> items;

	public ListBean() {
		super();

		items = new TreeMap<SectionBean, TreeSet<BuyItemBean>>();
	}
}
