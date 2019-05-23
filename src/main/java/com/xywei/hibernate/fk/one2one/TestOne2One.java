package com.xywei.hibernate.fk.one2one;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

/**
 * 测试1:1，基于外键
 *
 */
public class TestOne2One extends HibernateTest {

	@Test
	public void testSave() {

		Manager manager1 = new Manager();
		manager1.setManagerName("A1");
		Department department1 = new Department();
		department1.setDeptName("D1");
		// 设置关联关系
		manager1.setDepartment(department1);
		department1.setManager(manager1);
		session.save(manager1);
		session.save(department1);

		Manager manager2 = new Manager();
		manager2.setManagerName("A2");
		Department department2 = new Department();
		department2.setDeptName("D2");
		// 设置关联关系
		manager2.setDepartment(department2);
		department2.setManager(manager2);
		// 不推荐这样，因为会多出一条更新sql
		session.save(department2);
		session.save(manager2);

	}

	@Test
	public void testDelete() {
		Department department = (Department) session.get(Department.class, "297ebf926ae346e6016ae346ec5a0000");
		session.delete(department);

	}

	@Test
	public void testUpdate() {

		Department department = (Department) session.get(Department.class, "297ebf926ae3679a016ae3679f350001");
		Manager manager = new Manager();
		manager.setManagerName("A3");
		manager.setDepartment(department);
		session.save(manager);
		department.setManager(manager);

	}

	@Test
	public void testGet() {

//		Department department = (Department) session.get(Department.class, "297ebf926ae3679a016ae3679f350001");
//		System.out.println(department.getDeptName());
//		System.out.println(department.getDeptName() + "部门的经理是" + department.getManager().getManagerName());

		Manager manager = (Manager) session.get(Manager.class, "297ebf926ae36a64016ae36a66de0000");
		System.out.println(manager.getManagerName());
		System.out.println("经理的部门是：" + manager.getDepartment().getDeptName());

	}

}
