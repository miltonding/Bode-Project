package com.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionMgr {
	//JDBC��ص��ַ�������
	private  static final  String  DB_CLASS="oracle.jdbc.driver.OracleDriver";
	private  static final  String  URL="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
	private  static final  String  USER="scott";
	private  static final  String  PASSWORD="tiger";
		//��ȡconnection
		public static Connection getConnection(){
			Connection c=null;
			try {
				Class.forName(DB_CLASS);
				c=DriverManager.getConnection(URL,USER,PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
		}
		//�ر�������Դ
		public static void closeAll(ResultSet rs,Statement st,Connection c){
			try {
				if(rs!=null){
					rs.close();
				}
				
				if(st!=null){
					st.close();
				}
				
				if(c!=null){
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		public static void closeAll(Statement st,Connection c){
			try {
				if(st!=null){
					st.close();
				}
				
				if(c!=null){
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
