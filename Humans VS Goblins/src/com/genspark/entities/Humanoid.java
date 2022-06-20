package com.genspark.entities;

import com.genspark.utils.Combat;
import com.genspark.utils.GameGrid;

public class Humanoid
{
	private int y;
	private int x;
	public int dmg = 0;
	public int health = 0;
	public int finalDmg = 0;
	public int finalHealth = 0;
	
	
	@Override
	public String toString()
	{
		return "?";
	}
	
	public void attackHumanoid(Humanoid humanoid)
	{
		humanoid.finalHealth -= Combat.getRandomDamage(this.finalDmg, this.finalDmg);
	}
	
	public void tick(GameGrid gameGrid) {
	
	}
	
	public boolean reqContainsHumanoid(GameGrid gameGrid, Character direction, int[] coordinates)
	{
		switch (direction)
		{
			case ('N'):
				if (Combat.isHumanoid(gameGrid.getTileInfo(coordinates[0] - 1, coordinates[1])))
				{
					return false;
				}
				break;
			case ('S'):
				if (Combat.isHumanoid(gameGrid.getTileInfo(coordinates[0] + 1, coordinates[1])))
				{
					return false;
				}
			case ('W'):
				if (Combat.isHumanoid(gameGrid.getTileInfo(coordinates[0], coordinates[1] - 1)))
				{
					return false;
				}
			case ('E'):
				if (Combat.isHumanoid(gameGrid.getTileInfo(coordinates[0], coordinates[1] + 1)))
				{
					return false;
				}
		}
		return false;
	}
	
	public void setYX(int y, int x) {
		setY(y);
		setX(x);
	}
	
	public int[] getYX() {
		return new int[]{x, y};
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
}
