package com.genspark.entities;

import com.genspark.Main;
import com.genspark.utils.Combat;
import com.genspark.utils.GameGrids;

import java.awt.Image;

public class Humanoid
{
	private int y; // ^ v up / down
	private int x; // <-> left / right
	public int dmg = 0;
	public int health = 0;
	public int finalDmg = 0;
	public int finalHealth = 0;
	public String name = "";
	

	public Image toPixelArt() {
		return null;
	}
	
	public void updateFinals()
	{
		this.finalDmg = dmg;
		this.finalHealth = health;
	}
	
	@Override
	public String toString()
	{
		return null;
	}
	
	public void attackHumanoid(Humanoid humanoid)
	{
		int clampedDamage = Combat.getRandomDamage(this.finalDmg - 10, this.finalDmg);
		//noinspection ManualMinMaxCalculation, technically this method is more efficient
		clampedDamage = clampedDamage > this.finalDmg ? this.finalDmg : clampedDamage < 1 ? 1 : clampedDamage;
		humanoid.finalHealth -= clampedDamage;
		Main.messages.add(String.format("\n%1$s attacked %2$s. %s dealt %3$s damage.\n", this.name, humanoid.name, clampedDamage));
	}
	
	public void tick(GameGrids gameGrids)
	{
		if (this.finalHealth <= 0)
		{
			gameGrids.removeHumanoid(this);
		}
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
	
	public void setXY(int x, int y)
	{
		setY(y);
		setX(x);
	}
	
	public int[] getXY()
	{
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
