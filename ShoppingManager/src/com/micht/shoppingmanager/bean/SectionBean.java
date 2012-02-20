/**
 * 
 */
package com.micht.shoppingmanager.bean;

/**
 * @author Micht
 *
 */
public class SectionBean extends CommonBean implements Comparable<SectionBean> {
	/** Position of the section */
	public int position = 0;

	public SectionBean(String pName, int pPosition) {
		super(pName);
		position= pPosition;
	}

	@Override
	public String getName() {
		return name;
	}

	public int compareTo(SectionBean oSection) {
		if (oSection.position > this.position) {
			return -1;
		} else if (oSection.position < this.position) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return name;
	}
}
