package com.xywei.hibernate.oneway.many2one;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

/**
 * 测试单向N:1
 * 
 * @author wodoo
 *
 */
public class TestMany2One extends HibernateTest {

	/**
	 * 建立映射文件过程中犯错：order是数据库关键字
	 */
	@Test
	public void testSave() {

		Customer customer = new Customer();
		customer.setCustomerName("C3");

		Order order1 = new Order();
		order1.setCustomer(customer);
		order1.setOrderName("order5");

		Order order2 = new Order();
		order2.setCustomer(customer);
		order2.setOrderName("order6");
		// 推荐： 先保存多了一方只发送3条insert SQL
		session.save(customer);
		session.save(order1);
		session.save(order2);
		// 先保存多的一方，会先3条insert，再查询更新外键，性能不行
		// session.save(order1);
		// session.save(order2);
		// session.save(customer);
	}

	@Test
	public void testDelete() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);// 无法删除，外键约束
	}

	@Test
	public void testGet() {

		Order order = (Order) session.get(Order.class, 1);
		System.out.println("order:" + order);

		System.out.println("proxy" + order.getCustomer().getClass().getName());
		// System.out.println("session closed");
		// 可能会发生lazyinitialException
		// session.close();
		Customer customer = order.getCustomer();
		System.out.println("用到才会加载customer:" + customer);

	}

	@Test
	public void testUpdate() {
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("XOXO");
	}

}
