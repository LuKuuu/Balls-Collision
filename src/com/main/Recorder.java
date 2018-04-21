package com.main;

import java.sql.*;

import java.awt.*;
import javax.swing.*;


public class Recorder extends JFrame implements Runnable{
	
	
	Manager m=null;
	
	
	Connection con=null;
	PreparedStatement create_database=null;
	PreparedStatement create_interface_table=null;
	PreparedStatement create_balls_table=null;
	
	
	
	public static void main(String args[])
	{
		
		
		Recorder r=new Recorder();
		
//		r.removeAll();
		r.addInterface("bayha");
//		r.deleteInterface("haaaa");
		r.getInterface();
		
	}
	
	
	public Recorder()
	{
		
	try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +  
			        "databaseName=Ball_Collision;user=sa;password=Lcy990808");
			
			
			//create database
//			create_database=con.prepareStatement(
//					"if not exists(select * from master.sys.databases\r\n" + 
//					"where name='Ball_Collision')\r\n" + 
//					"create database Ball_Collision");
			
			
//			create_database.executeUpdate();
//			System.out.println("database created");				
			
			
			//create table "Interface"			
			create_interface_table=con.prepareStatement(
					"if not exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='Interface')\r\n" + 
					"create table Interface(\r\n" + 
					"\r\n" + 
					"Interface_Name  varchar(20) unique,\r\n" + 
					"Interface_No int primary key identity(1,1),\r\n" + 
					"Created_Time datetime default GETDATE(),\r\n" + 
					"Total_Number_of_balls int default 0,\r\n" + 
					"Size_x int default 1000,\r\n" + 
					"Size_y int default 1000,\r\n" + 
					"\r\n" + 
					"Update_Time datetime default GETDATE()\r\n" + 
					"\r\n" + 
					")\r\n" + 
					"");
			
			create_interface_table.executeUpdate();
			System.out.println("table Interface has been created");
			
			create_balls_table=con.prepareStatement(
					"if not exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='Balls')\r\n" + 
					"create table Balls(\r\n" + 
					"\r\n" + 
					"Interface_No int foreign key references Interface(Interface_No),\r\n" + 
					"Ball_name int not null,\r\n" + 
					"primary key(Interface_No, Ball_name),\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"mass int not null,\r\n" + 
					"radius int not null,\r\n" + 
					"\r\n" + 
					"x int not null,\r\n" + 
					"y int not null,\r\n" + 
					"\r\n" + 
					"vx int not null,\r\n" + 
					"vy int not null,\r\n" + 
					"\r\n" + 
					"Color_R int check (0<=Color_R and Color_R<=255) not null ,\r\n" + 
					"Color_G int check (0<=Color_G and Color_G<=255) not null,\r\n" + 
					"Color_B int check (0<=Color_B and Color_B<=255) not null,\r\n" + 
					"\r\n" + 
					"running char check (running in ('T','F')) not null ,\r\n" + 
					"\r\n" + 
					"Created_date datetime default GETDATE()\r\n" + 
					"\r\n" + 
					"\r\n" + 
					")");
			create_balls_table.executeUpdate();
			System.out.println("table balls has been created");
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			
//			if(create_database!=null)
//			{
//				try {
//					create_database.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
			
			if(create_interface_table!=null)
			{
				try {
					create_interface_table.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(create_balls_table!=null)
			{
				try {
					create_balls_table.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}
		
	}
	
	
	public void removeAll()
	{
		PreparedStatement remove_interface_table=null;
		PreparedStatement remove_ball_table=null;
		
		try {
			
			remove_ball_table=con.prepareStatement("drop table Balls");
			remove_ball_table.executeUpdate();
			remove_interface_table=con.prepareStatement("drop table Interface");
			remove_interface_table.executeUpdate();
			
			System.out.println("tables have been dropped");
			
			Recorder new_recorder =new Recorder();

			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	public void addInterface(String interface_name)
	{
		
		PreparedStatement insert_interface=null;
		
		try {
			
			insert_interface=con.prepareStatement("insert into Interface (Interface_Name) values (?)");
			insert_interface.setString(1, interface_name);			
			
			insert_interface.executeUpdate();
			System.out.println(interface_name+" has been inserted");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			if(insert_interface!=null)
			{
				try {
					insert_interface.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void deleteInterface(String interface_name)
	{
		
		PreparedStatement delete_interface=null;
		
		try {
			delete_interface=con.prepareStatement("delete from Interface where Interface_Name=?");
			delete_interface.setString(1, interface_name);
			
			delete_interface.executeUpdate();
			System.out.println(interface_name+" has been deleted");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				delete_interface.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void updateInterface(String interface_name, String new_interface_name)
	{
		
		PreparedStatement update_interface=null;
		
		try {
			update_interface=con.prepareStatement("update Interface set Interface_Name='?' where Interface_Name='?'");
			update_interface.setString(1, new_interface_name);
			update_interface.setString(2, interface_name);
			
			update_interface.executeUpdate();
			System.out.println(interface_name+"'s has been to "+new_interface_name);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				update_interface.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
	public void getInterface()
	{
		
		PreparedStatement get_interface=null;
		ResultSet interface_result=null;
		
		try {
			get_interface=con.prepareStatement("select * from Interface order by Interface_No");
			interface_result=get_interface.executeQuery();
			
			while(interface_result.next())
			{
				String name=interface_result.getString(1);
				int No=interface_result.getInt(2);
				String created_date=interface_result.getString(3);
				int totalballs=interface_result.getInt(4);
				
				
				System.out.println(name+"  "+No+"  "+created_date+"  "+totalballs);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
		}
		finally {
			try {
				if(interface_result!=null)
				{
					interface_result.close();

				}
				
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(get_interface!=null)
				{
					get_interface.close();

				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

	}
	
	public void getInterface(String interface_name)
	{
		
		PreparedStatement get_interface=null;
		ResultSet interface_result=null;
		
		try {
			get_interface=con.prepareStatement("select * from Interface where Interface_Name=?");
			get_interface.setString(1, interface_name);
			interface_result=get_interface.executeQuery();
			
			String name=interface_result.getString(1);
			int No=interface_result.getInt(2);
			String created_date=interface_result.getString(3);
			int totalballs=interface_result.getInt(4);			
			
			System.out.println(name+"  "+No+"  "+created_date+"  "+totalballs);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
		}
		finally {
			try {
				if(interface_result!=null)
				{
					interface_result.close();

				}
				
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(get_interface!=null)
				{
					get_interface.close();

				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

	}
	
	
	public void addBall(Ball b)
	{
		
		
		PreparedStatement add_ball=null;
		
		try {
			add_ball=con.prepareStatement("insert into Balls (Interface_No,Ball_name, mass, radius, x,y,vx,vy,Color_R,Color_G,Color_B, running)\r\n" + 
					"values (2,1,243,34,536,633,70,77, 100,100,100,1)");
//			add_ball.setInt();
//			
//			delete_interface.executeUpdate();
//			System.out.println(interface_name+" has been deleted");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				add_ball.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public void deleteBall(Ball b)
	{
		
		
		
	}
	public void updateBall(Ball b)
	{
		
	}
	
	public void getBalls()
	{
		
	}

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//keep updating balls' information
		
	}

	
}
