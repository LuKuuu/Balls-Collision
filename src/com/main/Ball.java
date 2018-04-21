package com.main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ball {
	
	int name;
	int mass;
	int r;
	
	int x;
	int y;
	
	int vx;
	int vy;
	
	Color c;
	
	Ball_running_x brx;
	Ball_running_y bry;
	
	boolean running=true; 
	
	
	
	
	
	public Ball(int name, int mass, int r, int xLocation, int yLocation, int xVelocity, int yVelocity)
	{
		this.name=name;
		this.mass=mass;
		this.r=r;
		this.x=xLocation;
		this.y=yLocation;
		this.vx=xVelocity;
		this.vy=yVelocity;
		
		brx=new Ball_running_x(this);
		bry=new Ball_running_y(this);
		
		RandumColor rc=new RandumColor();
		this.c=rc.c;
		
		
	}	


	public void start() {
		
		
		brx.start();
		bry.start();
				
		
	}
	
	public void sleep(long millis)
	{
		try {
			brx.sleep(millis);
			bry.sleep(millis);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
		
	}
	
	



	
	
	

}

class RandumColor 
{
	Color c;
	
	public RandumColor()
	{
		int r= (int) (255*Math.random());
		int g= (int) (255*Math.random());
		int b= (int) (255*Math.random());
		
		this.c= new Color(r,g,b);
		
	}
	
	
}


class Ball_running_x extends Thread
{
	Ball b;
	Time t;
	public Ball_running_x(Ball b)
	{
		this.b=b;
		
	}
	
	public void run()
	{				
		
		while(true) {
			
			t=new Time(b.vx);
			
			
			if(b.running)
			{
				
				if(b.vx>0)
				{
					b.x=b.x+1;
					try {
						Thread.sleep(t.millis,t.nano);
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
					if(b.x+b.r>=1000)
					{
						b.vx=-b.vx;
					}
					
					
					
				}
				else if(b.vx<0)
				{
					b.x=b.x-1;
					try {
						Thread.sleep(t.millis,t.nano);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(b.x-b.r<=0)
					{
						b.vx=-b.vx;
					}
					
				}
				else if (b.vx==0)
				{
					
				}
				
				
			}
			else {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			
		}
	}

}


class Ball_running_y extends Thread
{
	Ball b;
	Time t;
	
	public Ball_running_y(Ball b)
	{
		this.b=b;
		
	}
	
	public void run()
	{
		while(true)
		{
			
			
			if(b.running)
			{
				t=new Time(b.vx);
				if(b.vy>0)
				{
					b.y=b.y+1;
					try {
						Thread.sleep(t.millis,t.nano);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
					if(b.y+b.r>=1000)
					{
						b.vy=-b.vy;
					}
					
					//System.out.println(b.y);
					
				}
				else if(b.vy<0)
				{
					b.y=b.y-1;
					try {
						Thread.sleep(t.millis,t.nano);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(b.y-b.r<=0)
					{
						b.vy=-b.vy;
					}
					
				}
				else if (b.vy==0)
				{
					
				}
			}
			
			else {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			
		}
		
	}
	
	
}





class Time
{
	int speed;
	
	long millis;
	int nano;
	
	public Time(int speed)
	{
		this.speed=speed;
		
		float time_in_second=Math.abs(0.100000000f/speed);
		//System.out.println("Time in second is "+time_in_second);
		float time_in_millisecond=time_in_second*1000;
		
		millis=(long)time_in_millisecond;
		
				
		float time_in_nanosecond=time_in_millisecond*1000000;
				
		nano=(int)time_in_nanosecond-(int)millis*1000000;
		
		//System.out.println("Time in million second is "+millis);
		//System.out.println("The rest is"+nano);
		
	}
	
	
	
	
}






