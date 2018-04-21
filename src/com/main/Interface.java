package com.main;

import java.awt.*;
import javax.swing.*;
public class Interface extends JFrame{
	
	MyPanel mp;
	BallManager bm=null;
	
	String Interface_name="Interface";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interface I=new Interface();

	}
	
	public Interface()
	{
		this.setTitle("Collision");
		this.setSize(1020, 1050);
		this.setLocation(400, 0);
		this.setVisible(true);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		 bm=new BallManager(this);
		 mp=new MyPanel();
		 mp.setBalls(bm.balls);
		 
		
		
		
		
		Thread panel_t=new Thread(mp);
		panel_t.start();
		Thread BallManger_t=new Thread(bm);
		BallManger_t.start();	
		

		
		this.add(mp);	
		
		
		
	
	}

}


