package com.genspark;

import com.genspark.entities.Humanoid;
import com.genspark.utils.Directions;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;
import com.genspark.utils.GUI;
import com.genspark.utils.GameGrids;
import com.genspark.utils.Movement;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	static boolean running = true;
	static GameGrids gameGrids = new GameGrids();
	static Scanner scanner;
	static Humanoid mainChar;
	static Humanoid enemy;
	static GUI gui;
	public static Directions direction = Directions.NONE;
	public static ArrayList<String> messages = new ArrayList<>();
	static boolean playing = true;
	
	public static void main(String[] args)
	{
		try
		{
			gui = new GUI(gameGrids, direction, playing);
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
				OutputStream.nullOutputStream().write(direction.toString().getBytes(StandardCharsets.UTF_8));
				// this is necessary otherwise direction will not pull any updates.
				if (direction != Directions.NONE) {
					tickGame(direction);
					repaintGame();
					direction = Directions.NONE;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void repaintGame()
	{
		// System.out.println(gameGrids); // used for text GUI
		// System.out.println("Controls: WASD");
		gui.repaint();
	}
	
	private static void tickGame(Directions direction)
	{
		// move main character before ticking other humanoids.
		int[] xy = mainChar.getXY();
		int[] dir = Movement.directionToVector2d(direction);
		int[] newXY = new int[]{xy[0] + dir[0], xy[1] + dir[1]};
		try {
			gameGrids.moveHumanoidTo(newXY, mainChar);
			
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			return;
		}
		gameGrids.tickHumanoids();
	}
}
