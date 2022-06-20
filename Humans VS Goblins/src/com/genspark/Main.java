package com.genspark;

import com.genspark.utils.GameGrid;

import java.util.Scanner;

public class Main
{
	boolean running = true;
	GameGrid gameGrid = new GameGrid();
	static Scanner scanner;
	
	public void main(String[] args)
	{
		scanner = new Scanner(System.in);
		run();
	}
	
	public void run()
	{
		try
		{
			while (running)
			{
				tickGame();
				repaintGame();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private void repaintGame()
	{
		this.gameGrid.PrintGrid();
	}
	private void tickGame()
	{
	
	}
}
