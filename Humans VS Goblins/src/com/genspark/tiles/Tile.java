package com.genspark.tiles;

public class Tile
{
	char rep = '?';
	boolean collision = false;
	
	@Override
	public String toString()
	{
		return String.valueOf(rep);
	}
}
