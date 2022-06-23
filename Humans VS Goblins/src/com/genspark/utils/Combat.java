package com.genspark.utils;

import com.genspark.entities.Humanoid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.genspark.utils.Directions.EAST;
import static com.genspark.utils.Directions.NORTH;
import static com.genspark.utils.Directions.SOUTH;
import static com.genspark.utils.Directions.WEST;

public class Combat
{
	public static int getRandomDamage(int minRange, int maxRange)
	{
		return (int) (Math.random() * maxRange + minRange / 2);
	}
	public static boolean isHumanoid(Object obj) { return obj instanceof Humanoid; }
	public static int[] getDirectionFromDeg(int deg)
	{
		Map<Integer, int[]> hashMap = new HashMap<>();
		hashMap.put(0, Movement.directionToVector2d(SOUTH));
		hashMap.put(90, Movement.directionToVector2d(EAST));
		hashMap.put(180, Movement.directionToVector2d(NORTH));
		hashMap.put(-90, Movement.directionToVector2d(WEST));
		
		int dist;
		dist = Math.abs(Arrays.copyOf(hashMap.keySet().toArray(), hashMap.keySet().size(), Integer[].class)[0] - deg);
		
		int value = 0;
		for (Integer integer : hashMap.keySet())
		{
			int curDist = Math.abs(integer - deg);
			
			if (curDist < dist)
			{
				value = integer;
				dist = curDist;
			}
		}
		
		return hashMap.get(value);
	}
	
	public static void battleHumanoids(Humanoid h1, Humanoid h2)
	{
		while (h1.health > 0 && h2.health < 0)
		{
			h1.attackHumanoid(h2);
			if (h2.health > 0) h2.attackHumanoid(h1);
		}
		System.out.printf("\n%s: %s\n%s: %s\n", h1.name, h1.health, h2.name, h2.health);
	}
}
