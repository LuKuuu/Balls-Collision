package com.main;

import java.util.*;



public class BallManager implements Runnable {
	
	ArrayList<Ball> balls=null;
	boolean ball_touched=false;
	Thread collision=null;
	Thread repaint=null;
	
	boolean running=true;
	
	
	public BallManager(Interface i)
	{
		balls=new ArrayList<Ball>();
		
		collision=new Thread(this);
		
		repaint=new Thread(i.mp);
		
		
	
		
		System.out.println("rc created");
		
	}
	
	public void addBall(Ball b)	
	{
		balls.add(b);
		
		if(running)
		{
			b.start();	

		}
		

		
		
		
		
		
		System.out.println("Ball has been added");
	
	}
	
	public void deleteBall(int name)
	{
		for(int i=0;i<balls.size();i++)
		{
			Ball temp=balls.get(i);
			if(name==temp.name)
			{
				balls.remove(i);
				System.out.println("Ball name is "+ temp.name+", ball has been deleted.");
				
							
			}
				
		
		}
	}
	
	public void ballStart()
	{
		
		running=true;
		
		for(int i=0;i<balls.size();i++)
		{
			balls.get(i).running=true;
		}
		
		System.out.println("ball manager running");
		
	}
	
	
	public void ballStop()
	{
		
		running=false;
		for(int i=0;i<balls.size();i++)
		{
			balls.get(i).running=false;
		}
		
		
		
		System.out.println("ball stop");
		
	}
	
	
	public void checkballTouch()
	{
		int distance;
		
		int b1_No=-1;
		int b2_No=-1;		
		
		
		for(int i=0;i<balls.size();i++)
		{
			Ball bi=balls.get(i);
			
			for(int j=i+1;j<balls.size()-i;j++)
			{
				Ball bj=balls.get(j);
				
				if(true)
				{
					distance=(int) Math.sqrt((bi.x-bj.x)*(bi.x-bj.x)+(bi.y-bj.y)*(bi.y-bj.y));
					//System.out.println("distance="+distance);
					
					if(distance<=bi.r+bj.r)
					{
						ball_touched=true;
						
						System.out.println("ball touched");
						
						b1_No=i;
						b2_No=j;
						
						this.ballCollision(balls.get(i), balls.get(j));	
						
						
					}
				}
				
				
			}
			
			if(ball_touched=true)
			{
				break;
			}
			
			ball_touched=false;
							
		}		
		
	}
	
	public void ballCollision(Ball b1,Ball b2)
	{
		int vf1x=(2*b2.mass*b2.vx+(b1.mass-b2.mass)*b1.vx)/(b1.mass+b2.mass);
		int vf2x=(2*b1.mass*b1.vx+(b2.mass-b1.mass)*b2.vx)/(b1.mass+b2.mass);
		int vf1y=(2*b2.mass*b2.vy+(b1.mass-b2.mass)*b1.vy)/(b1.mass+b2.mass);
		int vf2y=(2*b1.mass*b1.vy+(b2.mass-b1.mass)*b2.vy)/(b1.mass+b2.mass);
		
		b1.vx=vf1x;
		b2.vx=vf2x;
		b1.vy=vf1y;
		b2.vy=vf2y;
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			this.checkballTouch();
			if(ball_touched)
			{
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
		}
		
		
	}	
	

}













