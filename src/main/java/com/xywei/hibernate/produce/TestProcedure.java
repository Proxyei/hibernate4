package com.xywei.hibernate.produce;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;
import org.junit.Test;

import com.xywei.hibernate.HibernateTest;

/**
 * 测试hibernate使用dowork调用存储过程
 * 
 * @author wodoo
 *
 */
public class TestProcedure extends HibernateTest {

	@Test
	public void test1() {

		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				String sql = "{call testProcedure()}";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.execute();
				// 后面就使用原生jdbc调用produce

			}
		});

	}

}
