package com.main;

import java.awt.*;

import java.util.*;

import javax.swing.*;

public class MyPanel extends JPanel implements Runnable{
	
	
	ArrayList<Ball> balls=null;
	
	public MyPanel()
	{
		this.setSize(1000,1000);
	
		
	}
	
	public void setBalls(ArrayList<Ball> balls)
	{
		this.balls=balls;
	}
	
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		for(int i=0;i<balls.size();i++)
		{
			
			
			Ball temp=balls.get(i);
			this.paintBall(temp, g);
			
			
		}
	}
		
	
	public void paintBall(Ball b,Graphics g)
	{
		
		g.setColor(b.c);
		g.fillOval(b.x-b.r, b.y-b.r, b.r*2, b.r*2);
		g.setColor(Color.BLACK);
		g.drawString("Ball: "+b.name+", Mass: "+b.mass+"kg", b.x-50, b.y-10);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
		

}









