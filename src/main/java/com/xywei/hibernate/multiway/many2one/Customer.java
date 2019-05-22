package com.xywei.hibernate.multiway.many2one;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer customerId;
	private String customerName;
	private Set<Order> orders = new HashSet<Order>();//防止nullpointerException

	public Customer() {
	}

	public Customer(Integer customerId, String customerName, Set<Order> orders) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.orders = orders;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", orders=" + orders + "]";
	}

}
