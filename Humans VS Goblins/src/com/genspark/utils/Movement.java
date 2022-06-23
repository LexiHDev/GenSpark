package com.genspark.utils;

public class Movement
{
	public static int[] directionToVector2d(Directions direction) {
		switch(direction) {
			case NORTH:
				return new int[]{-1, 0};
			case SOUTH:
				return new int[]{1, 0};
			case EAST:
				return new int[]{0, 1};
			case WEST:
				return new int[]{0, -1};
			default:
				return new int[]{0, 0};
		}
	}
}
