package com.genspark.entities;

public class Human extends Humanoid
{
	int dmg = 0;
	int health = 0;
	int finalDmg = 0;
	int finalHealth = 0;
	
	@Override
	public String toString() { return "H"; }
	
	public void updateFinals() {
		this.finalDmg = dmg;
		this.finalHealth = health;
	}
	
	public Human() {
		this.dmg = 5;
		this.health = 5;
		updateFinals();
	}
	
	
	public Human(int dmg, int health) {
		this.dmg = dmg;
		this.health = health;
		updateFinals();
	}
	
}
