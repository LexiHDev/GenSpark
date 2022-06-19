package com.genspark.utils;

public class Grid
{
	
	public Object[][] grid = new Object[1][1];
	public Grid(int width, int height)
	{
		for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
		{
			for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
			{
				grid[verticalSlice][horizontalSlice] = new Object();
			}
		}
	}
	public Grid()
	{
		int width = 15;
		int height = 15;
		grid = new Object[width][height];
		for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
		{
			for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
			{
				grid[verticalSlice][horizontalSlice] = new Object();
			}
		}
	}
	public void PrintGrid() {
		for (Object[] objects : grid)
		{
			System.out.println("-");
			for (int horizontalSlice = 0; horizontalSlice < grid[0].length; horizontalSlice++)
			{
				System.out.print(objects[horizontalSlice] + "※");
			}
		}
	}
}
