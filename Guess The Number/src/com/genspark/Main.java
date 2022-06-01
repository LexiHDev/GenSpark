package com.genspark;

import java.util.Random;
import java.util.Scanner;

public class Main
{
	
	public static void main(String[] args)
	{
		println("Hello! What is your name?\n");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		println("Well, %s, I am thinking of a number between 1 and 20.\n", username);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		int thinkingOf = rand.nextInt(20);
		thinkingOf++;
		
		int guess = -5;
		int guesses = 0;
		while (guess != thinkingOf && guesses < 6)
		{
			try
			{
				guess = scanner.nextInt();
			}
			catch (Exception e)
			{
				println("User put invalid input, please try again.\n");
				guess = -1;
				scanner.next();
				continue;
			}
			
			
			
			guesses++;
			if (guess < thinkingOf)
			{
				println("Your guess is too low.\n");
			}
			else if (guess > thinkingOf)
			{
				println("Your guess is too high.\n");
			}
			else
			{
				println("Good job, %s! You guessed my number in %s guesses!\n", username, guesses);
			}
		}
	}
	
	
	private static void println(String str, Object... args)
	{
		System.out.printf(str, args);
	}
	
}
