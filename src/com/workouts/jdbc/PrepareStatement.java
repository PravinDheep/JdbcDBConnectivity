package com.workouts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PrepareStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/workouts","root","welcome");
			//Can take DML or DRL, based on the query provided we can use pst.executeUpdate(), pst.executeQuery().
			PreparedStatement pst = con.prepareStatement("insert into student values(?,?,?,?,?)");
			pst.setInt(1, 1);
			pst.setString(2, "Apple");
			pst.setInt(3, 90);
			pst.setInt(4, 90);
			pst.setInt(5, 180);
			pst.executeUpdate();

			pst.setInt(1, 2);
			pst.setString(2, "Orange");
			pst.setInt(3, 80);
			pst.setInt(4, 80);
			pst.setInt(5, 160);
			pst.executeUpdate();
			 
			/*
			 * PreparedStatement pt =
			 * con.prepareStatement("delete from student where id =?"); pt.setInt(1, 2);
			 * pt.executeUpdate();
			 */
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
