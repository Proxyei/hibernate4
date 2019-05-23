package com.xywei.hibernate.multiway.many2many;

import java.util.Set;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

public class TestMultiWayMany2Many extends HibernateTest {

	@Test
	public void testSave() {

		Student student1 = new Student();
		student1.setStudentName("s1");
		Student student2 = new Student();
		student2.setStudentName("s2");

		Teacher teacher1 = new Teacher();
		teacher1.setTeacherName("t1");
		Teacher teacher2 = new Teacher();
		teacher2.setTeacherName("t2");

		teacher1.getStudents().add(student1);
		teacher1.getStudents().add(student2);
		teacher2.getStudents().add(student1);
		teacher2.getStudents().add(student2);

		session.save(teacher1);
		session.save(teacher2);

		session.save(student1);
		session.save(student2);

	}

	@Test
	public void testGet() {

		Teacher teacher = (Teacher) session.get(Teacher.class, 1);
		System.out.println("教师名字：" + teacher.getTeacherName());
		System.out.println("学生有" + teacher.getStudents().size());
		Set<Student> students = teacher.getStudents();
		System.out.println(students);

	}

}
