package com.genspark.entities;

import com.genspark.utils.GameGrid;

public class Goblin extends Humanoid
{
	@Override
	public String toString() { return "G";}
	
	public void update() {
	}
	public void tick(GameGrid gameGrid) {
		Human closest = gameGrid.getClosestHuman(this);
		System.out.println("Angle Theta is: " + Math.atan2(closest.getY() - this.getY(), closest.getX() - this.getX()))
		// wip
	}
}
