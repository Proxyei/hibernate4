package com.xywei.hibernate.pk.one2one;

import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

/**
 * 测试1:1，基于主键
 *
 */
public class TestOne2One extends HibernateTest {

	@Test
	public void testSave() {

		Manager manager = new Manager();
		manager.setManagerName("manager2");
		Department department = new Department();
		department.setDeptName("D2");

		department.setManager(manager);
		manager.setDepartment(department);

		session.save(manager);
		session.save(department);

	}

	@Test
	public void testDelete() {

//		Manager manager = (Manager) session.get(Manager.class, "297ebf926ae3926e016ae392701f0000");
//
//		session.delete(manager);
//		Department department = (Department) session.get(Department.class, "297ebf926ae397a6016ae397d25a0000");
//		session.delete(department);
		
	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testGet() {
//		Manager manager = (Manager) session.get(Manager.class, "297ebf926ae3926e016ae392701f0000");
//		System.out.println("经理：" + manager.getManagerName() + "部门是" + manager.getDepartment().getDeptName());
		
		Department department = (Department) session.get(Department.class, "297ebf926ae3926e016ae392701f0000");
		System.out.println("部门" + department.getDeptName() );
		System.out.println("经理是" + department.getManager().getManagerName());

	}

}
