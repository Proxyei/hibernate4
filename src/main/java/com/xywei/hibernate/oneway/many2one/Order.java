package com.xywei.hibernate.oneway.many2one;

public class Order {

	private Integer oderId;
	private String orderName;
	private Customer customer;

	public Integer getOderId() {
		return oderId;
	}

	public void setOderId(Integer oderId) {
		this.oderId = oderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [oderId=" + oderId + ", orderName=" + orderName + ", customer=" + customer + "]";
	}

}
