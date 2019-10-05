package main;

import java.util.ArrayList;
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

    public boolean isRowFull(int y){
        for(int i = 0; i < HEIGHT; i++)
        {
            if (grille[y][i] == -1)
                return false;
        }
        return true;
    }

    /**
     * Recherche et renvoie les numéros de lignes pleines
     * @return une ArrayList
     */
    public ArrayList<Integer> rowFilled()
    {
        ArrayList<Integer> numList = new ArrayList<>(this.HEIGHT);

        for (int y = 0; y < HEIGHT; y++)
        {
            if (this.isRowFull(y))
                numList.add(y);
        }
        return numList;
    }

    /**
     *
     * @pré-requis les lignes i et ii sont totalement remplis
     * @param i l'index d'une ligne de la grille
     * @param ii l'index d'une ligne de la grille
     * @return true ssi la ligne n°i et n°ii sont identiques
     */
    public boolean equals2Row(int i, int ii)
    {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        for (int val : grille[i]) {
            s1.append(val);
        }

        for (int val : grille[ii]) {
            s2.append(val);
        }

        return s1.toString().equals(s2.toString());
    }
}
