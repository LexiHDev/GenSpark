package com.genspark;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;


public class Main
{
	static public boolean guessInput(int hiddenNum, String username, InputStream sysIn, PrintStream sysOut) {
		if (sysOut == null) {
			sysOut = System.out;
		}
		
		Scanner scanner = new Scanner(sysIn);
		
		int guess = -5;
		int guesses = 0;
		
		boolean tryAgain = false;
		
		while (guess != hiddenNum && guesses < 6)
		{
			try
			{
				guess = scanner.nextInt();
			}
			catch (Exception e)
			{
				println("User put invalid input, please try again.\n",sysOut);
				guess = -1;
				scanner.next();
				continue;
			}
			
			
			
			guesses++;
			if (guess < hiddenNum)
			{
				println("Your guess is too low.\n", sysOut);
			}
			else if (guess > hiddenNum)
			{
				println("Your guess is too high.\n", sysOut);
			}
			else
			{
				println("Good job, %s! You guessed my number in %s guesses!\n", sysOut, username, guesses);
			}
		}
		return tryAgain;
	}
	public static void main(String[] args)
	{
		println("Hello! What is your name?\n", System.out);
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		println("Well, %s, I am thinking of a number between 1 and 20.\n", System.out, username);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		int thinkingOf = rand.nextInt(20);
		thinkingOf++;
		
		guessInput(thinkingOf, username, System.in, System.out);

		
	}
	
	
	private static void println(String str, PrintStream outputStream, Object... args)
	{
		outputStream.printf(str, args);
	}
	
}
