package com.main;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.io.*;
import java.awt.event.*;

import java.util.*;


public class Manager extends JFrame implements ActionListener{
	
	Interface i=null;	
	
	boolean i_exist=false;
	

	JMenuBar jmb=null;
	
	JMenu start=null;	
	JMenuItem save=null;
	JMenuItem new_file=null;
	JMenuItem new_example=null;
	
	JMenu open=null;
	JMenuItem[] file=null;
	
	Control c=null;
	
	Table t=null;
	
	BallControl bc=null;
	
	

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Manager m=new Manager();
		

	}
	
	public Manager()
	{
		jmb=new JMenuBar();
		this.setJMenuBar(jmb);
		
		start= new JMenu("Start (s)");
		start.setMnemonic('s');
		jmb.add(start);
		
		new_file=new JMenuItem("new");
		new_file.addActionListener(this);
		new_file.setActionCommand("new");
		start.add(new_file);
		
		
		save=new JMenuItem("save");
		save.addActionListener(this);
		save.setActionCommand("save");
		start.add(save);
		
		new_example=new JMenuItem("new example");
		new_example.addActionListener(this);
		new_example.setActionCommand("example");
		start.add(new_example);
		
		
		
		
		
		c=new Control();
		c.setI(this);
		
		this.add(c,BorderLayout.NORTH);
		
		t=new Table();
		t.setI(this);
		Thread reprint=new Thread(t);
		reprint.start();
		this.add(t, BorderLayout.CENTER);
		
		
		
		bc=new BallControl();
		this.add(bc, BorderLayout.SOUTH);
		
		 
		
		
		
		this.setSize(600, 400);
		this.setTitle("Manager");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("new"))
		{
			System.out.println("new");
			i=new Interface();
			i_exist=true;


			
		}
		else if(e.getActionCommand().equals("example"))
		{
			System.out.println("new example");
			i=new Interface();
			i_exist=true;
			
			Ball b1=new Ball(1, 10, 70, 500, 370, 30, -70);
			i.bm.addBall(b1);			
			
			Ball b2=new Ball(2, 10, 70, 500, 570, 27, 90);
			i.bm.addBall(b2);
			 
			Ball b3=new Ball(3, 10, 100, 500, 870, 20, 40);
			
			i.bm.addBall(b3);
			c.setI(this);
		}
		
		
		else if(e.getActionCommand().equals("open"))
		{
			System.out.println("open");
		}
		else if(e.getActionCommand().equals("save"))
		{
			System.out.println("save");
		}
	}

}


class Control extends JPanel implements ActionListener
{
	Interface i=null;
	boolean i_exist;
	
	JButton start=null;
	JButton stop=null;
	
	
	public void setI(Manager m)
	{
		this.i=m.i;
		this.i_exist=m.i_exist;
	}
	
	public Control()
	{
		start=new JButton("start");
		start.addActionListener(this);
		start.setActionCommand("start");
		stop=new JButton("Stop");		
		stop.addActionListener(this);
		stop.setActionCommand("stop");
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.add(start);		
		this.add(stop);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// TODO Auto-generated method stub
		
		
		if(i_exist)
		{
			if(e.getActionCommand().equals("start"))
			{
				
				
				
				System.out.println("Start");
				
				i.bm.ballStart();
			}
			
			else if(e.getActionCommand().equals("stop"))
			{
				System.out.println("Stop");
				
				i.bm.ballStop();
				
			}
		}
		
		
		
	}
	
}



class Table extends JPanel implements Runnable
{
	
	Manager m;
	BallModel bm;
	
	JTable table=null;
	JScrollPane jsp=null;
	
	
	public void setI(Manager m)
	{
		this.m=m;
		bm.setI(m);
		
	}

	
	
	public Table()
	{		
		bm=new BallModel();		
		
		table=new JTable(bm);			
		jsp=new JScrollPane(table);		
		this.add(jsp);
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("Thread enter");
			

			if(m.i_exist)
			{
				System.out.println(m.i_exist);
				bm=new BallModel();
				
			}
			else
			{
				System.out.println("error");
			}
				
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			
		}

		
		
		
		
	}

}



class BallModel extends AbstractTableModel
{
	
	
	
	Manager m;
	boolean i_exist;
	
	Vector<String> c=null;
	Vector<Vector> rowData=null;
	
	JTable table=null;
	JScrollPane jsp=null;
	
	
	public void setI(Manager m)
	{
		this.m=m;
		this.i_exist=m.i_exist;


	}

	
	
	public BallModel()
	{			

		
		c=new Vector<String>();
		c.addElement("name");
		c.addElement("mass");
		c.addElement("radius");
		c.addElement("x");
		c.addElement("y");
		c.addElement("vx");
		c.addElement("vy");
		c.addElement("Energy");		
		
		
		
		rowData=new Vector<Vector>();	
		
		
		
		
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.c.get(column);
		
	}	
	
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.c.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(i_exist)
		{
			return this.m.i.bm.balls.size();

		}
		else
		{
			return 1;
		}
	}

	@Override
	public Object getValueAt(int y, int x) {
		// TODO Auto-generated method stub
		
		if(i_exist)
		{
			Ball b=this.m.i.bm.balls.get(y);
			if(x==1)
			{
				return b.name;

			}
			else if(x==2)
			{
				return b.mass;
			}
			else if(x==3)
			{
				return b.r;
			}
			else if(x==4)
			{
				return b.x;
			}
			else if(x==5)
			{
				return b.y;
			}
			else if(x==6)
			{
				return b.vx;
			}
			else if(x==7)
			{
				return b.vy;
			}
			else if(x==8)
			{
				return (1/2)*b.mass*(b.vx*b.vx+b.vy*b.vy);
			}
			
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
		
		
	}
	
}





class BallControl extends JPanel implements ActionListener
{
	
	JButton add=null;
	JButton delete=null;
	JButton edit=null;
	
	
	
	public BallControl()
	{
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add=new JButton("add ball");
		add.addActionListener(this);
		add.setActionCommand("add");
		this.add(add);
		
		delete= new JButton("delete ball");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		this.add(delete);
		
		edit= new JButton("edit ball");
		edit.addActionListener(this);
		edit.setActionCommand("edit");
		this.add(edit);	
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("add"))
		{
			
		}
		
		else if(e.getActionCommand().equals("delete"))
		{
			
		}
		
		else if(e.getActionCommand().equals("edit"))
		{
			
		}
	}
	
	
	
}




