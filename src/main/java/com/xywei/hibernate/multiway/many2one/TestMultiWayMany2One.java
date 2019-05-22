package com.xywei.hibernate.multiway.many2one;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

/**
 * 测试多向N:1 相对论
 * 
 * @author wodoo
 *
 */
public class TestMultiWayMany2One extends HibernateTest {

	/**
	 * 建立映射文件过程中犯错：order是数据库关键字，相对论
	 */
	@Test
	public void testSave() {

		Customer customer = new Customer();
		customer.setCustomerName("C1");

		Order order1 = new Order();
		order1.setOrderName("ORDER1");

		Order order2 = new Order();
		order2.setOrderName("ORDER2");

		// 维护关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		// ，因为两端都维护关联关系所以多出2次update

		// 一共是2次insert orders，1次 insert customers ，4次update orders表
		// session.save(order1);
		// session.save(order2);
		// session.save(customer);

		// 3次insert，2次update orders
		// 建议1的一端使用inverse=true放弃维护关联关系
		session.save(customer);
		session.save(order1);
		session.save(order2);

	}

	@Test
	public void testDelete() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);// 无法删除，外键约束
	}

	@Test
	public void testOne2ManyGet() {

		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getOrders().getClass());

		// lazyinitializationException
		// session.close();
		// System.out.println(customer.getOrders().size());
	}

	@Test
	public void testMany2OneGet() {
		// 默认N的一方会懒加载1的一方
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());

		System.out.println(order.getCustomer().getCustomerName());
	}

	@Test
	public void testUpdate() {

		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getOrders().getClass());

		customer.getOrders().iterator().next().setOrderName("AAA_ORDER1");

	}

}
