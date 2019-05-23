package com.xywei.hibernate.pk.one2one;

/**
 * 一个部门只能有一个经理
 * 
 */
public class Department {

	private String deptId;
	private String deptName;
	private Manager manager;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
