package com.micht.shoppingmanager.bean;

public class BuyItemBean extends ItemBean {
	public int quantity;
	public boolean buyed = false;

	public BuyItemBean(String pName, SectionBean pSection) {
		super(pName, pSection);
		quantity = 1;
	}

	@Override
	public String getName() {
		return quantity + " " + name;
	}

	public BuyItemBean(String pName, SectionBean pSection, int pQuantity) {
		super(pName, pSection);
		quantity = pQuantity;
	}

	@Override
	public String toString() {
		return quantity + " " + name;
	}

}
