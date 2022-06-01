package com.dragonquest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
        System.out.println("You are in a land full of dragons. In front of you,\n" +
            "you see two caves. In one cave, the dragon is friendly\n" +
            "and will share his treasure with you. The other dragon\n" +
            "is greedy and hungry and will eat you on sight.\n" +
            "Which cave will you go into? (1 or 2)\n");
        
        Scanner scanner = new Scanner(System.in);
        int in = scanner.nextInt();
        scanner.close();
        if (in == 1) {
           System.out.println("You approach the cave...\n" +
               "It is dark and spooky...\n" +
               "A large dragon jumps out in front of you! He opens his jaws and...\n" +
               "Gobbles you down in one bite!\n");
        } else if (in == 2) {
            System.out.println("You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Greets you warmly!\n" +
                "\"Welcome, take this treasure!\"\n" +
                "He gives you 3 emeralds and the elusive rainbow gem."
            );
        }
        return;
    }
}
