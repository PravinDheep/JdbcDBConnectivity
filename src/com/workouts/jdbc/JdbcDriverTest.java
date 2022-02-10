package com.workouts.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class JdbcDriverTest {

	public static void main(String[] args) {
		//Load Driver
		//GetConnection
		//Prepare statement
		//Run resultset
		
		try {
			//1. Load Driver : 3 ways
			
			/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			DriverManager.registerDriver(driver);
			java -D jdbc.drivers = drivername applicationname*/
			
			//2. Get Connection : 3 ways
			
			/*DriverManager.getConnection(url); url = jdbc.odbc.datasource for sql
			DriverManager.getConnection(url, username, password); for oracle user,pass mandatory
			DriverManager.getConnection(url, Properties properties);
			create datasource
				goto controlpanel
				select administrative tools
				select Datasource(ODBC) doubleclick
				In Datasource administrator window, click on add
				select datasource.
				create any datasource name
				pass userid-username
				tns service-choose xe if oracle10g, orcl for oracle11 or other based on database */
				
			//3. Create Statement
			
			/*i. using statement - forward queries to database
				Statement st = con.createStatement();
			ii. using preparedStatement
				PreparedStatement pst = con.prepareStatement("SQL");
			iii. using callableStatement - used for procedures and functions
				CallableStatement cst = con.createCall();*/
			
			//4. Execute SQL statements  & ExecuteQuery using DML, DDL, DRL.
			// If statement is DDL(st.execute(DDL)), it will return boolean (will return if query executed or not)
			// If statement is DML(st.executeUpdate(DML), it will return number of rows affected using your query, transaction
			// commit need to apply only for DML statements(con.setAutoCommit(false), con.commit());
			// If statement is DRL(st.executeQuery(DRL)), then you can expect DRL.
			
			/*
			i. Using Statement
				st.execute("DDL"); - create,alter,drop,truncate,rename
				st.executeUpdate("DML"); DML - insert, update, delete 
				st.executeQuery("DRL"); DRL - select
			
			ii. using preparedStatement - while creating itself will pass SQL statements
				PreparedStatement pst = con.prepareStatement("SQL");
				pst.execute(); - DDL
				pst.executeUpdate(); - DML
				pst.executeQuery(); - DRL
				
			iii. using CallableStatement
				we can execute procedures and functions in database */
			
			//5. Read Data from database
			
			/*i. Using ResultSet(interface)
				we can iterate data which is coming from database
				Result Set internally stores as an array with an extra column with the boolean flag, 
				when this flag value is true(when record is found in the table), you can continue to 
				access the record using next(), to access the table values you can use getInt(1)
				for ID column, getString(column number/index) for table column name etc.*/
			
			/* while(rs.next()){
		 		 System.out.println("ID :" + rs.getInt(1));
		 		 System.out.println("NAME :" + rs.getString(2));
		 		 System.out.println("EMAIL :" + rs.getString(3));
		 		 // where 1,2 & 3 is the column number/index.
			   }
			*/
			
			/* ResultSet getMetadata() is used to get Column count and Column data type if we don't know the table columns.*/
			/* ResultSetMetaData md = rs.getMetaData();
			 * int noofcolumns = md.getColumnCount();
			 * md.getColumnName(column index);
			 */
			//6. Jdbc will always throw compile time exceptions
			
				
			/*
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
			 * Connection con = DriverManager.getConnection();
			 * 
			 */
			
			//Driver d = new JdbcOdbcDriver();
			//DriverManager.registerDriver(d);
			
			//Connection con = DriverManager.getConnection("sun.jdbc.odbc");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/workouts","root","welcome");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/workouts", null);
			System.out.println(con);
			Enumeration<Driver> e = DriverManager.getDrivers(); 
			while(e.hasMoreElements()) { 
				Driver d = (Driver) e.nextElement(); 
				System.out.println(d.getClass().getName()); 
			}
			
			Statement st = con.createStatement();
			String sqlinsert = "insert into student values(1,'test',78,78,156)";
			int rowsAffected = st.executeUpdate(sqlinsert);
			System.out.println("No of records : " + rowsAffected);
			
			ResultSet rs = st.executeQuery("select * from student");
			
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("No of columns: " + numberOfColumns);
			
			
			for(int i = 1; i <= numberOfColumns; i++) {
				System.out.print(">>>ColumnNames>>>> :" + metaData.getColumnName(i) + "\t");
				System.out.print("\t>>>ColumnType>>>>> :" + metaData.getColumnType(i) );
				System.out.print("\t>>>ColumnTypeName>>>>> :" + metaData.getColumnTypeName(i) + "\n");
			}
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)  +  "," + rs.getString(2) + "," + rs.getInt(3)  + "," + rs.getInt(4) + "," + rs.getInt(5));
				break;
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

}