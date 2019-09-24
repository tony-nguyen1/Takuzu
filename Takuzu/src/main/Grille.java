package main;

import java.util.Arrays;

public class Grille {
    private int[][] grille;
    private final int HEIGHT;
    private final int WIDTH;

    public Grille(int width, int height) {
        grille = new int[width][height];
        fill(-1);
        HEIGHT = height;
        WIDTH = width;
    }

    @Override
    public String toString() {
        String res = "";
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (grille[x][y]>=0) {
                    res+=grille[x][y]+" ";                    
                }else{
                    res+="* ";
                }
            }
            res+="\n";
        }
        return "Grille{\n" + res + "}\n";
    }

    public void affichage() {
        //TODO
        System.out.println(toString());
    }


    public void setValue(int x, int y, int value){
        grille[x][y] = value;
    }

    public int getValue(int x, int y){
        return grille[x][y];
    }

    
    public int getHEIGHT() {
        return this.HEIGHT;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public void fill(int value){
        for (int[] ligne : grille) {
            Arrays.fill(ligne, value);
        }
    }
}