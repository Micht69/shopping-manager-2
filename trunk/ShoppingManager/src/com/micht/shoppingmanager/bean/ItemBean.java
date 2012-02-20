/**
 * 
 */
package com.micht.shoppingmanager.bean;

/**
 * @author Micht
 *
 */
public class ItemBean extends CommonBean implements Comparable<ItemBean> {
	/** Section on the Item */
	public SectionBean section;

	public ItemBean(String pName, SectionBean pSection) {
		super(pName);
		section = pSection;
	}

	@Override
	public String getName() {
		return name;
	}

	public int compareTo(ItemBean oItem) {
		return this.name.compareTo(oItem.name);
	}

	@Override
	public String toString() {
		return name;
	}
}
