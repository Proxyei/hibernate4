package com.xywei.hibernate.session;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xywei.hibernate.domain.Student;
import com.xywei.hibernate.domain.User;

public class TestHibernateSession {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void inti() {

		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.getTransaction();
		transaction.begin();

	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testDDL() {
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("a");
		user.setPassword("b");
		user.setDate(new Date());
		session.save(user);
		// 保存后会自动与数据库关联，这样也会自动更新数据库
		user.setUsername("aa");

		Student student = new Student();
		student.setUsername("a");
		student.setPassword("b");
		student.setDate(new Date());

		session.save(student);
	}

	// 查询，这么多年又忘记了，查询与事务无关
	@Test
	public void testQuery() {
		// 下面两个是ID查询
		// Student student = (Student) session.get(Student.class, 1);
		// System.out.println(student);
		// User user=(User) session.get(User.class, "297ebf926ad97421016ad97424c20000");
		// System.out.println(user);
		// 查询所有
		// SQLQuery sqlQuery = session.createSQLQuery("select * from user"); //SQL查询
		// List<User> users = sqlQuery.addEntity(User.class).list();
		// Query query = session.createQuery("from User");//HQL查询
		// List<User> users = query.list();
		// Criteria criteria=session.createCriteria(User.class);//QBC
		// List<User> users= criteria.list();
		// System.out.println(users);

		// 占位符查询
		// Query query = session.createQuery("from Student where id= :id");
		Query query = session.createQuery("from Student where id= ?");
		// query.setParameter("id", 1);
		query.setParameter(0, 1);
		Student student = (Student) query.uniqueResult();
		System.out.println(student);
		// TODO 连接重新，分组查询，子查询，排序

	}

	// 缓存针对查询有作用flush:缓存更新到数据库，refresh：发送SQL保持最新缓存(需要注意事务隔离级别)
	@Test
	public void testSessionCache() {

		Student student1 = (Student) session.get(Student.class, 1);
		System.out.println(student1);
		session.refresh(student1);
		System.out.println(student1);
		// session.clear();
		// 如果与缓存数据不同，会隐式进行flush,update操作（如果没开启事务、提交事务，则不会进行update，但是会出现幻读，student2数据是错的，因为事务commit之前有flush操作）
		// student1.setUsername("ates212t1");
		// Student student2 = (Student) session.get(Student.class, 1);
		// System.out.println(student2);
		// 如果使用HQL/QBC，也会发送SQL，更新缓存
		// Query query=session.createQuery("from Student where id=?").setParameter(0,
		// 1);
		// Student student3=(Student) query.uniqueResult();
		// System.out.println(student3);

	}

}
