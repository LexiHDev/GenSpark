package com.genspark;

import com.genspark.entities.Human;
import com.genspark.utils.Grid;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.PrintGrid();
    
        Human human = new Human();
        System.out.println();
        System.out.println(human);
    }
}
