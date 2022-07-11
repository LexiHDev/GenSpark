package com.genspark.utils;

import com.genspark.entities.Goblin;
import com.genspark.entities.Human;
import com.genspark.entities.Humanoid;
import com.genspark.tiles.Grass;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameGrids
{
	int width;
	int height;
	public Humanoid[][] characterGrid = new Humanoid[1][1];
	public Object[][] tileGrid = new Object[1][1];
	public GameGrids(int width, int height)
	{
		this.width = width;
		this.height = height;
		tileGrid = Arrays.stream(tileGrid)
			.map(horizontalSlice -> Arrays.stream(horizontalSlice)
				.map(tile -> new Grass())
				.toArray(Object[]::new))
			.toArray(Object[][]::new);
	}
	
	public Object getTileInfo(int y, int x)
	{
		return characterGrid[y][x];
	}
	
	public GameGrids()
	{
		width = 10;
		height = 15;
		characterGrid = new Humanoid[width][height];
		tileGrid = new Object[width][height];
		tileGrid = Arrays.stream(tileGrid)
			.map(horizontalSlice -> Arrays.stream(horizontalSlice)
				.map(obj -> obj = new Grass())
				.toArray(Object[]::new))
			.toArray(Object[][]::new);
	}
	
	private boolean hasHumanoid(int y, int x)
	{
		return characterGrid[y][x] != null;
	}
	
	private void inBounds(int y, int x)
	{
		if (characterGrid.length < y || characterGrid[0].length < x)
			throw new IndexOutOfBoundsException(String.format("[%s, %s] out of bounds.", y, x));
	}
	
	
	public Humanoid[] humanoids()
	{
		return Arrays.stream(characterGrid).flatMap(Arrays::stream)
			.filter(Objects::nonNull)
			.toArray(Humanoid[]::new);
	}
	
	public Human getClosestHuman(Goblin goblin)
	{
		double dist = Integer.MAX_VALUE;
		Human closest = null;
		
		
		int gy = goblin.getY();
		int gx = goblin.getX();
		
		return (Human) Arrays.stream(humanoids()).reduce(new Humanoid(Integer.MAX_VALUE, Integer.MAX_VALUE), (closestHumanoid, humanoid) -> {
			if (!(humanoid instanceof Human)) return closestHumanoid;
			int clX = closestHumanoid.getY();
			int clY = closestHumanoid.getX();
			
			int hy = humanoid.getY();
			int hx = humanoid.getX();
			
			double hYDist = gy - hy; // effectively this makes a triangle so that we can calculate distance
			double hXDist = gx - hx; // https://www.mathsisfun.com/pythagoras.html
			
			double clYDist = gy - clY;
			double clXDist = gx - clX;
			
			
			double hdistance = Math.sqrt(Math.pow(hXDist, 2) + Math.pow(hYDist, 2));
			double cldistance = Math.sqrt(Math.pow(clXDist, 2) + Math.pow(clYDist, 2)); // a^2 + b^2 = c^2 gives us the slope length.
			
			if (cldistance > hdistance)
			{
				return humanoid;
			}
			else return closestHumanoid;
		});
	}
	
	public void removeHumanoid(Humanoid humanoid)
	{
		try
		{
			characterGrid =
				Arrays.stream(characterGrid)
					.map(slice -> Arrays.stream(slice)
						.map(point -> {
							if (point != humanoid)
							{
								return point;
							}
							return null;
						}).toArray(Humanoid[]::new))
					.toArray(Humanoid[][]::new);
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
		try
		{
			
			if (!hasHumanoid(y, x))
			{
				this.characterGrid[humanoid.getY()][humanoid.getX()] = null;
				this.characterGrid[y][x] = humanoid;
				humanoid.setXY(x, y);
			}
		}
		catch (Exception e)
		{
			if (e.getClass().getName().equals("java.lang.ArrayIndexOutOfBoundsException")) return;
			e.printStackTrace();
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
		
		Optional<String> stringOptional = IntStream.range(0, width - 1)
			.mapToObj(i -> new AbstractMap.SimpleImmutableEntry<>(characterGrid[i], tileGrid[i])) // zip horizontal arrays
			.map(simpleImmutableEntry -> {
					var humanoidEntry = simpleImmutableEntry.getKey();
					var tileEntry = simpleImmutableEntry.getValue();
					return IntStream.range(0, Math.min(humanoidEntry.length, tileEntry.length))
						.mapToObj(i -> {
							String humanoid;
							if (humanoidEntry[i] == null) humanoid = null;
							else humanoid = humanoidEntry[i].toString();
							String tile = tileEntry[i].toString();
							return new ArrayList<>(Arrays.asList(humanoid, tile));
						})
						.reduce(new ArrayList<>(List.of("\n| ")), (acc, ele) -> {
							if (ele.get(0) == null) acc.add(" " + ele.get(1) );
							else acc.add(" " + ele.get(0));
							return acc;
						});
				}
			).map(arrayList -> String.join("", arrayList))
			.reduce((acc, ele) -> {
				if (acc.endsWith("+")) return acc + ele;
				else return acc + ele + "|";
			});
		
		
		assert stringOptional.isPresent();
		ret.append(stringOptional.get());
		
	
//
//		for (int horizontalSlice = 0; horizontalSlice < height; horizontalSlice++)
//		{
//			ret.append('\n'); // new line
//			ret.append("| "); // add left border.
//			for (int verticalSlice = 0; verticalSlice < width; verticalSlice++)
//			{
//				if (characterGrid[verticalSlice][horizontalSlice] instanceof Humanoid) // Check for a humanoid
//				{ // with
//					ret.append(characterGrid[verticalSlice][horizontalSlice]).append(" "); // character & space between next visual element
//				}
//				else
//					ret.append(tileGrid[verticalSlice][horizontalSlice]).append(" "); // tile & space between next visual element
//			}
//			ret.append("|"); // add right border.
//		}
		ret.append('\n'); // new line
		ret.append(hBorder); // add bottom border.
		return ret.toString();
	}
	
}
