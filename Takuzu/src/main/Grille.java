package main;

import java.util.Arrays;

import static main.IHM.IHM.special;

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
        System.out.println(toString());
    }

    public void affichageGraphique() {
        special(this.grille);
    }


    public void setValue(int x, int y, int value){
        grille[y][x] = value;
    }

    public int getValue(int x, int y){ return grille[y][x]; }

    
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
