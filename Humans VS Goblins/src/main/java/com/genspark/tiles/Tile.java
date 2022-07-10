package com.genspark.tiles;

import java.awt.Image;

public class Tile
{
	char rep = '?';
	boolean collision = false;
	
	@Override
	public String toString()
	{
		return String.valueOf(rep);
	}
	public Image toPixelArt()
	{
		return null;
	}
}
