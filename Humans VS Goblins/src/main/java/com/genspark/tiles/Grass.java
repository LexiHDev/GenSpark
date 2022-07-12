package com.genspark.tiles;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;

public class Grass extends Tile
{
	@Override
	public String toString() { return "w"; }
	
	public Image toPixelArt()
	{
		try
		{
			return ImageIO.read(new File("C:\\GenSparkGit\\Humans VS Goblins\\src\\main\\resources\\Grass.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
