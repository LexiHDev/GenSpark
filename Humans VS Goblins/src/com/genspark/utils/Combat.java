package com.genspark.utils;

public class Combat
{
	public static int getRandomDamage(int minRange, int maxRange)
	{
		return (int) (Math.random() * maxRange + minRange/2);
	}
}
