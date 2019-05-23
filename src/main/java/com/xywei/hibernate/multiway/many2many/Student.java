package com.xywei.hibernate.multiway.many2many;

import java.util.Set;

/**
 * 一个学生可以有多个教师
 * 
 * @author wodoo
 *
 */
public class Student {

	private Integer studentId;
	private String studentName;
	private Set<Teacher> teachers;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", teachers=" + teachers + "]";
	}

}
