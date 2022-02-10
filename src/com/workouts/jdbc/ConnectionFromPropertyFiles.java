package com.workouts.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFromPropertyFiles {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("D:\\workspace\\JdbcDBConnectivity\\src\\com\\workouts\\jdbc\\dbdetails.properties"));
			Class.forName(properties.getProperty("driver"));
			Connection con = DriverManager.getConnection(properties.getProperty("url"),properties);
			System.out.println("User:>>>" + properties.getProperty("user"));
			System.out.println("Password:>>>" + properties.getProperty("password"));
			System.out.println(con);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student");
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("No of columns: " + numberOfColumns);
			
			
			for(int i = 1; i <= numberOfColumns; i++) {
				System.out.print(">>>ColumnNames>>>> :" + metaData.getColumnName(i) + "\t");
			}
				
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
