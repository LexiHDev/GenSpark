package com.genspark.entities;

import com.genspark.utils.Combat;

public class Humanoid
{
	int dmg = 0;
	int health = 0;
	int finalDmg = 0;
	int finalHealth = 0;
	
	
	@Override
	public String toString()
	{
		return "?";
	}
	
	public void attackHumanoid(Humanoid humanoid)
	{
		humanoid.health -= Combat.getRandomDamage(this.dmg, this.dmg);
	}
	
}
