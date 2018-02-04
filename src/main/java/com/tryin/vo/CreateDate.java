package com.tryin.vo;

import java.sql.Connection;
import java.sql.Statement;
/**
 * 
 * @TODO用户注册信息表单对象
 * @author Tryin4Sage
 * @date 2018年2月4日下午9:50:40
 */
public class CreateDate {
	private static Connection conn;

	public static void main(String[] args) {
		try {
			conn = DBUtils.getConnection();
			Statement sta = conn.createStatement();
			String createSql = "create table userdate("
					+ "username varchar(20) not null,"
					+ "password varchar(20) not null,"
					+ "phone varchar(11) not null,"
					+ "nikename varchar(20))";
			sta.execute(createSql);
			System.out.println("存储数据库表单建成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
