package com.xywei.hibernate.oneway.many2many;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

public class TestOneWayMany2Many extends HibernateTest {

	@Test
	public void testSave() {

		Student student1 = new Student();
		student1.setStudentName("s1");
		Student student2 = new Student();
		student2.setStudentName("s2");

		Teacher teacher = new Teacher();
		teacher.setTeacherName("t1");

		teacher.getStudents().add(student1);
		teacher.getStudents().add(student2);

		session.save(teacher);
		session.save(student1);
		session.save(student2);

	}

	@Test
	public void testGet() {

		Teacher teacher = (Teacher) session.get(Teacher.class, 1);
		System.out.println("教师名字：" + teacher.getTeacherName());
		System.out.println("学生有" + teacher.getStudents());

		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		System.out.println("测试集合类缓存====================================");
		Teacher teacher2 = (Teacher) session.get(Teacher.class, 1);
		System.out.println("教师名字：" + teacher2.getTeacherName());
		System.out.println("学生有" + teacher2.getStudents());
		transaction.commit();
		session.close();

	}

}
