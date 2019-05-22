package com.xywei.hibernate.coreapi;

import java.util.Date;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;
import com.xywei.hibernate.domain.User;

/**
 * 测试常用的API，四种状态的转换
 * 
 * @author wodoo
 *
 */
public class CoreAPI extends HibernateTest {

	@Test
	public void testSave() {

		User user = new User();
		user.setDate(new Date());
		user.setUsername("AA");
		user.setPassword("ABC");
		user.setId("238477ad234792837492");// 会被后面的覆盖
		System.out.println(user);

		System.out.println("===临时状态变成持久化状态===");
		session.save(user);
		System.out.println(user);

	}

	@Test
	public void testPersistence() {

		User user = new User();
		user.setDate(new Date());
		user.setUsername("AA");
		user.setPassword("ABC");
		user.setId("238477ad234792837492");// 会直接抛出异常，不会insert
		System.out.println(user);

		System.out.println("===临时状态变成持久化状态===");
		session.persist(user);
		System.out.println(user);

	}

	/**
	 * 立即执行查询，假如数据不存在，不会抛出异常，假如查询完毕后关闭session，也不会异常
	 */
	@Test
	public void testGET() {
		User user = (User) session.get(User.class, "297ebf926add99b4016add99b7c40000");
		// User user = (User) session.get(User.class,
		// "397ebf926add99b4016add99b7c40000");
		session.close();
		System.out.println(user);
	}

	/**
	 * 延迟加载对象，使用到的时候才会发送SQL，假如数据不存在（数据库没记录），使用到对象的时候会报异常，假如session关闭，会报LazyInitializationException
	 */
	@Test
	public void testLOAD() {
		User user = (User) session.load(User.class, "297ebf926add99b4016add99b7c40000");
		// User user = (User) session.load(User.class,
		// "397ebf926add99b4016add99b7c40000");
		session.close();
		System.out.println(user.getUsername());
	}

	/**
	 * 1同一个session内，setter之前缓存会与记录对比，如果不一样，则会发出更新SQL，可以不写update，如果不同session，则需要使用update
	 * 2确切说，有记录但是不关联session的是游离对象，需要显式更新update，即使游离对象不变，使用update也都会发送sql
	 * 3，如何让不同的session下相同的游离对象，在update的时候，不盲目发送sql？在影射文件下配置select-before-update=true，但是出于性能考虑，一般不设置
	 * 4，把一个临时对象使用update，则抛出异常，同一个session内不能有两个相同的对象
	 */
	@Test
	public void testUpdate() {

		User user = (User) session.get(User.class, "297ebf926add99b4016add99b7c40000");
		System.out.println(user);
		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		// user.setUsername("4444441111pw231pd");
		// System.out.println(user);
		transaction = session.beginTransaction();
		// session.update(user);
		User user2 = (User) session.get(User.class, "297ebf926add99b4016add99b7c40000");
		session.update(user);// 关联另外一个游离对象，报异常，同一个sessin内不能有2个一样的OID
		transaction.commit();
		System.out.println(user);

	}

	@Test
	public void testSaveOrUpdate() {

		// 根据ID设置，如果不是asigned，OID不为空，数据库中也没记录，则报错
		// User user0 = new User("jdjsfoisjdiofuso", "W", "Y");
		// 根据ID设置，如果不是asigned，OID不为空，数据库中也没记录，则报错！如果OID不为空，数据库中有对应记录，则进行更新，也就是游离->持久
		User user = new User("297ebf926adddecc016adddecf430000", "WWWWWW", "Y");
		session.saveOrUpdate(user);

	}

	/**
	 * 删除数据库表记录方法 1，通过删除游离状态，只要游离状态对象的OID与数据库中有对应的，那么该记录会被删除
	 * 2，通过持久化状态对象，先查询，关联表记录后，进行删除 3,如果OID没有数据库对应的，则会被删除
	 * 
	 */
	@Test
	public void testDele() {
		// 删除游离状态
		// User user = new User();
		// user.setId("297ebf926adddecc016adddecf430000");
		// session.delete(user);
		// System.out.println(user);

		// 删除持久化状态
		// User user = new User("297ebf926add99b4016add99b7c40000", "WWWWWW", "Y");
		// System.out.println(user);
		// session.delete(user);
		// System.out.println(user);

		// User user = new User();
		// user.setId("94237423ljadfadfad");
		// session.delete(user);
		// System.out.println(user);
	}

	/**
	 * 把对象从缓存中移除，就不会发送update
	 *
	 */
	@Test
	public void testEvict() {
		User user = (User) session.get(User.class, "297ebf926addb31f016addb323b60000");
		user.setUsername("ffsldjfsfioweu");
		session.evict(user);

	}

}
