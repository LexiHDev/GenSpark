package com.genspark.utils;

import com.genspark.entities.Goblin;
import com.genspark.entities.Human;
import com.genspark.entities.Humanoid;
import com.genspark.tiles.Grass;
import com.genspark.utils.ExceptionUtils.FailedToMoveToTile;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GameGrid
{
	int width;
	int height;
	public Object[][] characterGrid = new Object[1][1];
	public Object[][] tileGrid = new Object[1][1];
	public GameGrid(int width, int height)
	{
		this.width = width;
		this.height = height;
		for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
		{
			for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
			{
				characterGrid[verticalSlice][horizontalSlice] = new Grass();
			}
		}
	}
	public Object getTileInfo(int y, int x)
	{
		return characterGrid[y][x];
	}
	
	public GameGrid()
	{
		width = 15;
		height = 15;
		characterGrid = new Object[width][height];
		tileGrid = new Object[width][height];
		for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
		{
			for (int horizontalSlice = 0; horizontalSlice < width; horizontalSlice++)
			{
				tileGrid[verticalSlice][horizontalSlice] = new Grass();
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
		return (Humanoid[]) Arrays.stream(characterGrid).flatMap(Arrays::stream).filter(obj -> obj instanceof Humanoid).toArray();
	}

	public Human getClosestHuman(Goblin goblin) {
		double dist = Integer.MAX_VALUE;
		Human closest = null;
		
		for (Humanoid humanoid : humanoids()) {
			if (!(humanoid instanceof Human)) { // check if human
				continue;
			}
			
			int gy = goblin.getY();
			int gx = goblin.getX();
			
			int hy = humanoid.getY();
			int hx = humanoid.getX();
			
			double ydist = gy - hy; // effectively this makes a triangle so that we can calculate distance
			double xdist = gx - hx; // https://www.mathsisfun.com/pythagoras.html
			
			double distance = Math.sqrt(xdist*xdist + ydist*ydist); // a^2 + b^2 = c^2 gives us the slope length.
			
			if (dist > distance) {
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
			humanoid.tick(this);
		}
	}
	
	public void addHumanoid(int y, int x, Humanoid humanoid) throws FailedToMoveToTile
	{
		inBounds(y, x);
		if (hasHumanoid(y, x))
		{
			throw new FailedToMoveToTile(String.format("Failed To Add Humanoid at: [%s, %s].", y, x));
		} else
		characterGrid[y][x] = humanoid;
		humanoid.setYX(y, x);
	}
	
	
	public void moveHumanoidTo(int y, int x, Humanoid humanoid) throws FailedToMoveToTile
	{
		inBounds(y, x);
		if (hasHumanoid(y, x)){
			throw new FailedToMoveToTile(String.format("Failed to move to Tile at [%s, %s].",y ,x));
		} else
			this.characterGrid[humanoid.getY()][humanoid.getX()] = null;
			this.characterGrid[y][x] = humanoid;
			humanoid.setYX(y, x);
		
	}
	
	@SuppressWarnings("unused")
	public void addHuman(int y, int x) throws FailedToMoveToTile
	{
		addHumanoid(y, x, new Human());
	}
	@SuppressWarnings("unused")
	public void addHuman(int y, int x, Human human) throws FailedToMoveToTile
	{
		addHumanoid(y, x, human);
	}
	
	@SuppressWarnings("unused")
	public void addGoblin(int y, int x) throws FailedToMoveToTile
	{
		addHumanoid(y, x, new Goblin());
	}
	@SuppressWarnings("unused")
	public void addGoblin(int y, int x, Goblin goblin) throws FailedToMoveToTile
	{
		addHumanoid(y, x, goblin);
	}
	
	public void PrintGrid()
	{
		StringBuilder hBorder = new StringBuilder();
		IntStream intStream = IntStream.range(0, width * 2 + 1);
		hBorder.append("+");
		intStream.forEach(value -> hBorder.append("-"));
		hBorder.append("+");
		System.out.print(hBorder);
//		for (Object[] objects : worldGrid)
		for (int verticalSlice = 0; verticalSlice < height; verticalSlice++)
		{
			System.out.println();
			System.out.print("| ");
			for (int horizontalSlice = 0; horizontalSlice < characterGrid[0].length; horizontalSlice++)
			{
				if (characterGrid[verticalSlice][horizontalSlice] instanceof Humanoid)
				{
					System.out.print(characterGrid[verticalSlice][horizontalSlice] + " ");
				}
				else System.out.print(tileGrid[verticalSlice][horizontalSlice] + " ");
			}
			System.out.print("|");
		}
		System.out.println();
		System.out.println(hBorder);
	}
	
}
