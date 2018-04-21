package com.test;


import java.awt.*;
import java.util.*;

import java.sql.*;
import java.awt.event.*;

import javax.swing.*;


public class Test4 extends JFrame{
	
	
	JScrollPane jsp=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		
		Test4 test4=new Test4();
		
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		

	}
	
	public Test4()
	{
		JPanel jp=new JPanel();
		
		Vector<String> c=new Vector<String>();
		c.addElement("name");
		c.addElement("mass");
		c.addElement("r");
		c.addElement("x");
		c.addElement("y");
		c.addElement("vx");
		c.addElement("vy");
		
		Vector rowData=new Vector();
		
		
		try {
			
			Class.forName("com.microsoft.jdbc.sqlserver.SQLserverDriver");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
		}
		
		
		Vector r=new Vector();
		
		r.add("1");
		r.add("300");
		r.add("75");
		
		r.add("40");
		r.add("20");
		
		r.add("35");
		r.add("20");
		
		
		
		rowData.add(r);
		
			
		
		JTable table=new JTable(rowData,c);
		
		
		
		jsp=new JScrollPane(table);
		
		this.add(jsp);
		
		
		

		
		
		
		
		this.setSize(300,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}


