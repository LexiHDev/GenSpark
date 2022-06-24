package com.genspark.utils;

import com.genspark.entities.Goblin;
import com.genspark.entities.Human;
import com.genspark.entities.Humanoid;
import com.genspark.tiles.Grass;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GameGrids
{
	int width;
	int height;
	public Object[][] characterGrid = new Object[1][1];
	public Object[][] tileGrid = new Object[1][1];
	public GameGrids(int width, int height)
	{
		this.width = width;
		this.height = height;
		for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
		{
			for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
			{
				characterGrid[horizontalSlice][verticalSlice] = new Grass();
			}
		}
	}
	public Object getTileInfo(int y, int x)
	{
		return characterGrid[y][x];
	}
	
	public GameGrids()
	{
		width = 10;
		height = 15;
		characterGrid = new Object[width][height];
		tileGrid = new Object[width][height];
		for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
		{
			for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
			{
				tileGrid[horizontalSlice][verticalSlice] = new Grass();
			}
		}
	}
	
	private boolean hasHumanoid(int y, int x)
	{
		return characterGrid[y][x] instanceof Humanoid;
	}
	
	private void inBounds(int y, int x)
	{
		if (characterGrid.length < y || characterGrid[0].length < x)
			throw new IndexOutOfBoundsException(String.format("[%s, %s] out of bounds.", y, x));
	}
	
	
	public Humanoid[] humanoids()
	{
		return Arrays.stream(characterGrid).flatMap(Arrays::stream)
			.filter(obj -> obj instanceof Humanoid)
			.toArray(Humanoid[]::new);
	}
	
	public Human getClosestHuman(Goblin goblin)
	{
		double dist = Integer.MAX_VALUE;
		Human closest = null;
		
		for (Humanoid humanoid : humanoids())
		{
			if (!(humanoid instanceof Human))
			{ // check if human
				continue;
			}
			
			int gy = goblin.getY();
			int gx = goblin.getX();
			
			int hy = humanoid.getY();
			int hx = humanoid.getX();
			
			double ydist = gy - hy; // effectively this makes a triangle so that we can calculate distance
			double xdist = gx - hx; // https://www.mathsisfun.com/pythagoras.html
			
			double distance = Math.sqrt(xdist * xdist + ydist * ydist); // a^2 + b^2 = c^2 gives us the slope length.
			
			if (dist > distance)
			{
				dist = distance;
				closest = (Human) humanoid; // we know for a fact that this is a human because of line 72.
			}
		}
		
		return closest;
	}
	
	public void tickHumanoids()
	{
		for (Humanoid humanoid : humanoids())
		{
			try
			{
				
				humanoid.tick(this);
				
			}
			catch (Exception e)
			{
				System.out.print(e.getClass().getName());
			}
		}
	}
	
	public void removeHumanoid (Humanoid humanoid) {
		try
		{
			characterGrid = Arrays.stream(characterGrid).map(slice -> Arrays.stream(slice).map(point ->{
				if (point != humanoid) {
					return point;
				}
				return null;
			}).toArray(Object[]::new)).toArray(Object[][]::new);
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	
	public void addHumanoid(int y, int x, Humanoid humanoid) throws ExceptionTileInUse
	{
		inBounds(y, x);
		if (hasHumanoid(y, x))
		{
			throw new ExceptionTileInUse(String.format("Failed To Add Humanoid at: [%s, %s].", y, x));
		}
		else
			characterGrid[y][x] = humanoid;
		
		humanoid.setXY(x, y);
	}
	
	
	public void moveHumanoidTo(int y, int x, Humanoid humanoid) throws ExceptionTileInUse
	{
		inBounds(y, x);
		if (hasHumanoid(y, x))
		{
			throw new ExceptionTileInUse(String.format("Failed to move to Tile at [%s, %s].", y, x));
		}
		else
		{
			this.characterGrid[humanoid.getY()][humanoid.getX()] = null;
			this.characterGrid[y][x] = humanoid;
			humanoid.setXY(x, y);
		}
	}
	public void moveHumanoidTo(int[] vector2d, Humanoid humanoid)
	{
		int x = vector2d[0];
		int y = vector2d[1];
		inBounds(y, x);
		if (!hasHumanoid(y, x))
		{
			this.characterGrid[humanoid.getY()][humanoid.getX()] = null;
			this.characterGrid[y][x] = humanoid;
			humanoid.setXY(x, y);
		}
/*
		else
		{
			// throw new ExceptionTileInUse(String.format("Failed to move to Tile at [%s, %s].", y, x));
			// We want to instead do nothing
		    // This would be a good spot for a logger maybe...
		}
*/
	}
	
	@SuppressWarnings("unused")
	public Human addHuman(int y, int x) throws ExceptionTileInUse
	{
		Human human = new Human();
		addHumanoid(y, x, human);
		return human;
	}
	@SuppressWarnings("unused")
	public Human addHuman(int y, int x, Human human) throws ExceptionTileInUse
	{
		addHumanoid(y, x, human);
		return human;
	}
	
	@SuppressWarnings("unused")
	public Goblin addGoblin(int y, int x) throws ExceptionTileInUse
	{
		Goblin goblin = new Goblin();
		addHumanoid(y, x, goblin);
		return goblin;
	}
	@SuppressWarnings("unused")
	public Goblin addGoblin(int y, int x, Goblin goblin) throws ExceptionTileInUse
	{
		addHumanoid(y, x, goblin);
		return goblin;
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		StringBuilder hBorder = new StringBuilder();
		IntStream intStream = IntStream.range(0, width * 2 + 1);
		hBorder.append("+"); // add left corner
		intStream.forEach(value -> hBorder.append("-")); // line for horizontal border based on width
		hBorder.append("+"); // add right corner
		ret.append(hBorder); // add top border
		for (int horizontalSlice = 0; horizontalSlice < height; horizontalSlice++)
		{
			ret.append('\n'); // new line
			ret.append("| "); // add left border.
			for (int verticalSlice = 0; verticalSlice < width; verticalSlice++)
			{
				if (characterGrid[verticalSlice][horizontalSlice] instanceof Humanoid) // Check for a humanoid
				{ // with
					ret.append(characterGrid[verticalSlice][horizontalSlice]).append(" "); // character & space between next visual element
				}
				else
					ret.append(tileGrid[verticalSlice][horizontalSlice]).append(" "); // tile & space between next visual element
			}
			ret.append("|"); // add right border.
		}
		ret.append('\n'); // new line
		ret.append(hBorder); // add bottom border.
		return ret.toString();
	}
	
}
