package com.test;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		FindBestI f=new FindBestI(120);

	}

}

class FindBestI
{
	float velocity;
	int bestI=1;
	int real_time_interval;
	int real_velocity;
	float expected_time_interval;
	float fractional_uncertainty;
	public FindBestI(int velocity)
	{
		this.velocity=(float) velocity;
		
		while(bestI<15)
		{
			expected_time_interval=(bestI*100.00f)/velocity;
			System.out.println("expected_time_interval is:"+ expected_time_interval);
			real_time_interval=(bestI*100)/velocity;
			System.out.println("real_time_interval is " +real_time_interval);
			real_velocity=bestI*100/real_time_interval;
			System.out.println("the velocity is "+real_velocity);
			fractional_uncertainty=Math.abs((float)velocity-(float)real_velocity)/(float)velocity;
			
			System.out.println("fractional_uncertainty is"+ fractional_uncertainty);
			
			if(fractional_uncertainty>0.02f)
			{
				bestI++;
			}
			else
			{
				System.out.println("best i is "+ bestI);
				System.out.println("the velocity is "+real_velocity);
				break;
			}
		}
	}
	
	public void iFind()
	{
		
	}
}