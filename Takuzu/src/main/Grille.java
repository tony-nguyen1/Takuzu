package main;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static main.IHM.IHM.special;
//TODO faire listCaseVide, listUneCollone, listUneLigne
public class Grille {
    private int[][] grille;
    private final int HEIGHT;
    private final int WIDTH;

    public Grille(int height, int width) {
        grille = new int[height][width];
        fill(-1);
        HEIGHT = height;
        WIDTH = width;
    }


    public int[][] getGrille() {
        return grille;
    }

    @Override
    public String toString() {
        String res = "";
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (grille[x][y]>=0) {
                    res = res.concat(String.valueOf(grille[x][y])).concat(" ");
                }else{
                    res = res.concat("* ");
                }
            }
            res = res.concat("\n");
        }
        return "Grille{\n" + res + "}\n";
    }

    public void affichage() {
        System.out.println(toString());
    }

    public void affichageGraphique() {
        special(this.grille);
    }


    public void setValue(int hauteur, int largeur, int value) {
        grille[hauteur][largeur] = value;
    }

    public int getValue(int hauteur, int largeur) {
        return grille[hauteur][largeur];
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

    public boolean isColumnFull(int x)
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            if (grille[i][x] == -1)
                return false;
        }
        return true;
    }

    public ArrayList<Integer> columnFilled()
    {
        ArrayList<Integer> numList = new ArrayList<>(this.HEIGHT);

        for (int x = 0; x < HEIGHT; x++)
        {
            if (this.isColumnFull(x))
                numList.add(x);
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

    public boolean equals2Column(int i, int ii)
    {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        for (int[] uneLigne : grille) {
            s1.append(uneLigne[i]);
        }

        for (int[] uneLigne : grille) {
            s2.append(uneLigne[ii]);
        }

        return s1.toString().equals(s2.toString());
    }

    //TODO pour Mathieu
    //C'est pour refactor des trucs après ...
    /**
     * Crée une liste de toutes les cases vides.
     * La linkedList contient toutes les coordonnées.
     * La arrayList contient la coordonnée x et y.
     *
     * @return les coordonnées de toutes les cases vides, return null si il n'y en a pas
     */
    public LinkedList<ArrayList<Integer>> listCaseVide() { return null; }
    /**
     * Crée une nouvelle liste et y recopie toutes les valeurs d'une colonne;
     * @param uneColonne
     * @return
     */
    public LinkedList<Integer> listUneCollone(int uneColonne) { return null; }
    /**
     * Crée une nouvelle liste et y recopie toutes les valeurs d'une ligne;
     * @param uneLigne
     * @return
     */
    public LinkedList<Integer> listUneLigne(int uneLigne) { return null; }

}
