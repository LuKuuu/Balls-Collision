package com.test;

import com.main.Ball;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyPanelArray mpa=new MyPanelArray();
		mpa.printArray();
		
	}
}

class MyPanelArray
{
	int[][] array;
	int xl;
	int yl;
	
	public MyPanelArray()
	{
		this.xl=100;
		this.yl=100;
		array=new int[xl][yl];
		
		
		for(int i=0;i<xl;i++)
		{
			for(int j=0;j<yl;j++)
			{
				if(i==0||i==xl-1||j==0||j==yl-1)
				{
					array[i][j]=-1;
				}
			}
		}
	}
	
	public void printArray()
	{
		for(int i=0;i<xl;i++)
		{
			for(int j=0;j<yl;j++)
			{
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	

}
