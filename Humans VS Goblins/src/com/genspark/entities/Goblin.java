package com.genspark.entities;

import com.genspark.utils.Combat;
import com.genspark.utils.ExceptionUtils.ExceptionTileInUse;
import com.genspark.utils.GameGrids;

import java.util.Arrays;
import java.util.List;

public class Goblin extends Humanoid
{
	List<String> goblinNames = Arrays.asList("Boggart", "Bugbear", "Cackler", "Croaker", "Flayer", "Gloam", "Grendel", "Gribble", "Growl", "Grumble", "Grunt", "Hisser", "Moaner", "Nighthag", "Prowler", "Scrag","Skulker","Snarl","Snarl","Sneer","Snerd","Snide","Snook","Snork","Snot","Snott","Snotty","Snout","Splug","Tatterdemalion","Trapper","Trickster","Twig","Twiggy","Twinkle","Vandal","Wart","Wheezer","Whimple","Whine","Whinny","Whisker","Whisp","Whistler","Wink","Winkle","Wretch","Wrinkler");
	
	@Override
	public String toString() { return "G";}
	
	
	
	public Goblin() {
		name = goblinNames.get((int) (Math.random() * goblinNames.size()));
	}
	
	public void tick(GameGrids gameGrids) {
		Human closest = gameGrids.getClosestHuman(this);
		if (closest == null) return;
		if (closest.getY() - this.getY() >= 1 && closest.getX() >= 1) {
			Combat.battleHumanoids(this, closest);
			return;
		}
		double angle = Math.toDegrees(Math.atan2(closest.getY() - this.getY(), closest.getX() - this.getX())) % 360;
		int[] direction;
		direction = Combat.getDirectionFromDeg((int) angle);
		int[] newPos = this.getXY();
		newPos[0] += direction[0];
		newPos[1] += direction[1];
		
		try
		{
			gameGrids.moveHumanoidTo(newPos, this);
		}
		catch (ExceptionTileInUse e)
		{
			e.printStackTrace();
		}
		
	}
}
