package com.genspark.entities;

import com.genspark.utils.Combat;
import com.genspark.utils.GameGrids;

public class Humanoid
{
	private int y; // ^ v up / down
	private int x; // <-> left / right
	public int dmg = 0;
	public int health = 0;
	public int finalDmg = 0;
	public int finalHealth = 0;
	public String name = "";
	
	
	@Override
	public String toString()
	{
		return "?";
	}
	
	public void attackHumanoid(Humanoid humanoid)
	{
		humanoid.finalHealth -= Combat.getRandomDamage(this.finalDmg, this.finalDmg);
		System.out.println("%s%1 attacked %s%2. %s%1 dealt %s%3 damage.");
	}
	
	public void tick(GameGrids gameGrids) {
	
	}
	
	public boolean reqContainsHumanoid(GameGrids gameGrids, Character direction, int[] coordinates)
	{
		switch (direction)
		{
			case ('N'):
				if (Combat.isHumanoid(gameGrids.getTileInfo(coordinates[0] - 1, coordinates[1])))
				{
					return false;
				}
				break;
			case ('S'):
				if (Combat.isHumanoid(gameGrids.getTileInfo(coordinates[0] + 1, coordinates[1])))
				{
					return false;
				}
			case ('W'):
				if (Combat.isHumanoid(gameGrids.getTileInfo(coordinates[0], coordinates[1] - 1)))
				{
					return false;
				}
			case ('E'):
				if (Combat.isHumanoid(gameGrids.getTileInfo(coordinates[0], coordinates[1] + 1)))
				{
					return false;
				}
		}
		return false;
	}
	
	public void setXY(int x, int y) {
		setY(y);
		setX(x);
	}
	
	public int[] getXY() {
		return new int[]{x, y};
	}
	public int getX()
	{
		return x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
}
