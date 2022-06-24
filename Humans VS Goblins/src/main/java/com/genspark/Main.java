package com.genspark;

import com.genspark.entities.Humanoid;
import com.genspark.utils.Directions;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;
import com.genspark.utils.GameGrids;

import java.util.Scanner;

import static com.genspark.utils.Directions.EAST;
import static com.genspark.utils.Directions.NONE;
import static com.genspark.utils.Directions.NORTH;
import static com.genspark.utils.Directions.SOUTH;
import static com.genspark.utils.Directions.WEST;

public class Main
{
	static boolean running = true;
	static GameGrids gameGrids = new GameGrids();
	static Scanner scanner;
	static Humanoid mainChar;
	static Humanoid enemy;
	
	public static void main(String[] args)
	{
		try
		{
			scanner = new Scanner(System.in);
			enemy = gameGrids.addGoblin(1, 1);
			mainChar = gameGrids.addHuman(3, 3);
			gameGrids.addGoblin(5, 5);
			gameGrids.addGoblin(1, 5);
			gameGrids.addGoblin(5, 1);
			repaintGame();
			run();
		}
		catch (ExceptionTileInUse e)
		{
			e.printStackTrace();
		}
	}
	
	public static void run()
	{
		try
		{
			while (running)
			{
				Scanner scanner = new Scanner(System.in);
				scanner.useDelimiter("");
				char chara = scanner.next().charAt(0);
				Directions direction;
				switch(chara) {
					case 'W':
						direction = NORTH;
					case 'S':
						direction = SOUTH;
					case 'D':
						direction = EAST;
					case 'A':
						direction = WEST;
					default:
						direction = NONE;
				} // input to direction enum
				tickGame(direction);
				repaintGame();
				
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private static void repaintGame()
	{
		System.out.println(gameGrids);
		System.out.println("Controls: WASD");
	}
	private static void tickGame(Directions direction)
	{
		// move main character before ticking other humanoids.
		gameGrids.tickHumanoids();
	}
}
