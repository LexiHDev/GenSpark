package com.genspark.utils;

import com.genspark.entities.Humanoid;

public class Combat
{
	public static int getRandomDamage(int minRange, int maxRange)
	{
		return (int) (Math.random() * maxRange + minRange/2);
	}
	public static boolean isHumanoid(Object obj) { return obj instanceof Humanoid;}
}
