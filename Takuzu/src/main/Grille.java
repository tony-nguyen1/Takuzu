package main;

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

    public boolean estTotalementRemplit() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (grille[i][j] == -1) return false;
            }
        }

        return true;
    }


    public boolean remplirLigneDe(int numLigne, int val) {
        boolean didSomething = false;
        for (int i = 0; i < WIDTH; i++) {
            if (grille[numLigne][i] == -1) {
                setValue(numLigne, i, val);
                didSomething = true;
            }
        }

        return didSomething;
    }

    public boolean remplirColonneDe(int numColonne, int val) {
        boolean didSomething = false;
        for (int i = 0; i < WIDTH; i++) {
            if (grille[i][numColonne] == -1) {
                setValue(i, numColonne, val);
                didSomething = true;
            }
        }

        return didSomething;
    }

    public int[] trouver1erCaseVide() {
        for (int ord = 0; ord < HEIGHT; ord++) {
            for (int abs = 0; abs < WIDTH; abs++) {

                if (this.getValue(ord, abs) == -1) {
                    return new int[]{ord, abs};
                }
            }
        }
        return null;
    }

    public boolean remplirLaDifference(Grille uneGrille) {

        //1er étape : vérification que "this" est un sous-ensemble de unTakuzu
        //même dimension ?
        if (uneGrille.HEIGHT != this.HEIGHT || uneGrille.WIDTH != this.WIDTH) {
            return false;//throw new RuntimeException("Pas la même grille");
        }

        //même valeur ?
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (this.getValue(i, j) != -1) {
                    if (uneGrille.getValue(i, j) != this.getValue(i, j))
                        return false;
                }
            }
        }

        //2e étape
        //identique donc on peut recopier les cases de unTakuzu dans this
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                this.setValue(i,j,uneGrille.getValue(i,j));
            }
        }
        return true;
    }
}
