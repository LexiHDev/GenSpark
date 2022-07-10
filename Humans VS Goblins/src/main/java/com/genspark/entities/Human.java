package com.genspark.entities;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Human extends Humanoid
{
	int finalDmg = 0;
	int finalHealth = 0;
	
	
	@Override
	public String toString() { return "H"; }
	
	public Image toPixelArt()
	{
		try {
			return ImageIO.read(new File("C:\\GenSparkGit\\Humans VS Goblins\\src\\main\\resources\\Human.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	}
	
	public Human() {
		this.dmg = 10;
		this.health = 200;
		updateFinals();
		List<String> humanNames = Arrays.asList("Aaliyah", "Abigail", "Ada", "Adeline", "Agatha", "Aiden", "Alan", "Alana", "Alec", "Alexandra", "Alfred", "Alice", "Alina", "Alison", "Amos", "Amy", "Ana", "Andre", "Andrew", "Angela", "Ann", "Anna", "Anne", "Annette", "Anthony", "April", "Arthur", "Astrid", "Audrey", "August", "Aurora", "Austin", "Barbara", "Benjamin", "Beth", "Bradley", "Brandon", "Brenda", "Brianna", "Brooke", "Cameron", "Carl", "Carmen", "Carol", "Caroline", "Cassandra", "Cecilia", "Charles", "Charlotte", "Chastity", "Chelsea", "Chester", "Chloe", "Christina", "Christine", "Christopher", "Clara", "Clarence", "Claudia", "Clement", "Clifford", "Connie", "Courtney", "Craig", "Curtis", "Cynthia", "Daisy", "Dale", "Damien", "Dana", "Daniel", "Danielle", "Daphne", "David", "Dawn", "Dean", "Deborah", "Denise", "Dennis", "Diana", "Diego", "Dillon", "Dominic", "Donna", "Doris", "Dorothy", "Edgar", "Eleanor", "Elijah", "Elizabeth", "Ellen", "Emily", "Emma", "Eric", "Evan", "Evelyn", "Faith", "Felix", "Fiona", "Florence", "Frances", "Francis", "Fred", "Gabriel", "Gary", "Gemma", "Genevieve", "Geoffrey", "George", "Geraldine", "Gina", "Glenn", "Grace", "Gregory", "Greta", "Harold", "Harper", "Hayden", "Heather", "Helen", "Henry", "Howard", "Ian", "Ingrid", "Irene", "Lexi");
		// Names Generated with GPT-3
		this.name = humanNames.get((int) (Math.random() * humanNames.size()));
	}
	
	
	@SuppressWarnings("unused")
	public Human(int dmg, int health, String name) {
		this.dmg = dmg;
		this.health = health;
		this.name = name;
		updateFinals();
	}
	
}
