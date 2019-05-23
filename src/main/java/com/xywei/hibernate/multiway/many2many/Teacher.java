package com.xywei.hibernate.multiway.many2many;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个教师可以教多个学生
 * 
 * @author wodoo
 *
 */
public class Teacher {

	private Integer teacherId;
	private String teacherName;
	private Set<Student> students = new HashSet<Student>();

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", students=" + students + "]";
	}

}
