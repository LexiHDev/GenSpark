package com.genspark;

import com.genspark.entities.Humanoid;
import com.genspark.utils.Directions;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;
import com.genspark.utils.GameGrids;
import com.genspark.utils.Movement;

import java.util.Arrays;
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
				char chara = Character.toLowerCase(scanner.next().charAt(0));
				Directions direction;
				switch(chara) {
					case 'w':
						direction = NORTH;
						break;
					case 's':
						direction = SOUTH;
						break;
					case 'd':
						direction = EAST;
						break;
					case 'a':
						direction = WEST;
						break;
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
		int[] xy = mainChar.getXY();
		int[] dir = Movement.directionToVector2d(direction);
		int[] newXy = new int[]{xy[0] + dir[0], xy[1] + dir[1]};
		try {
			gameGrids.moveHumanoidTo(newXy, mainChar);
			
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			return;
		}
		gameGrids.tickHumanoids();
	}
}
