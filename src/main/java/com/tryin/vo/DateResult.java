package com.tryin.vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DateResult {
	private static Connection conn;

	/*
	 * 注册时用;
	 * 检索数据库看有户名是否存在;
	 */
	public static boolean ifExist(String username) {
		try {
			conn = DBUtils.getConnection();
			String sql = "select count(*) from userdate where username=\'"
					+username+ "\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int n = rs.getInt(1);
				if(n==1)return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return false;
	}
	
	/*
	 * 检查用户名密码是否正确
	 */
	public static boolean ifTrue(String username,String password) {
		try {
			conn = DBUtils.getConnection();
			String sql = "select count(*) from userdate where username=\'"
					+username+ "\' and password=\'"+password+"\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int n = rs.getInt(1);
				if(n==1)return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return false;
	}
	
	/*
	 * 将注册信息写入数据库
	 */
	public static boolean isinFoDate(String username,String password,String phone,String nikename) {
		try {
			conn = DBUtils.getConnection();
			String sql = "insert into userdate values(\'"
					+username+"\',\'"+password+"\',\'"+phone+"\',\'"+nikename+"\')";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return ifExist(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return false;
	}
	
}
