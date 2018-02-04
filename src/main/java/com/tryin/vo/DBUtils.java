package com.tryin.vo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
/**
 * 数据库连接池对象
 * @author Tryin4Sage
 * @date 2018年2月4日下午5:30:39
 */
public class DBUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static int initialSize;
	private static int maxActive;
	private static BasicDataSource bs;
	
	static {
		try {
			//读取配置文件对象
			Properties cfg = new Properties();
			//反射获取文件(流)
			InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
			//载入流
			cfg.load(in);
			
			// 获取对应数据
			driver = cfg.getProperty("driver");
			url = cfg.getProperty("url");
			user = cfg.getProperty("user");
			password = cfg.getProperty("password");
			initialSize = Integer.parseInt(cfg.getProperty("initialSize"));
			maxActive = Integer.parseInt(cfg.getProperty("maxActive"));
			
			// 设置连接池参数
			bs = new BasicDataSource();
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(user);
			bs.setPassword(password);
			bs.setInitialSize(initialSize);
			bs.setMaxActive(maxActive);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取数据库连接对象
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = bs.getConnection();
		return conn;
	}
	/*
	 * 归还对象
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
