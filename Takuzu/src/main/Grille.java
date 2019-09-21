package main;

import java.util.ArrayList;

public class Grille {
    private ArrayList<ArrayList<Integer>> grille;
    private int longueur;

    public Grille(int j) {
        this.longueur = j;
        grille = new ArrayList<>(longueur);

        ArrayList<Integer> remplissage, ligne;

        remplissage= new ArrayList<>(longueur);
        for (int i = 0; i < longueur; i++) {remplissage.add(-1);}

        for (int i = 0; i < longueur; i++) {
            ligne = new ArrayList<>(longueur);
            ligne.addAll(remplissage); //le nouveau array doit être remplit sinonn il est considéré vide et ne peut pas être géré correctement
            grille.add(ligne);
        }
    }

    @Override
    public String toString() {
        return "Grille{" + grille + '}';
    }

    public void affichage() {
        for (int i = 0; i < longueur; i++) { System.out.println(grille.get(i)); }
    }

    public ArrayList<ArrayList<Integer>> getGrille() {
        return grille;
    }

    public ArrayList<Integer> getCollonne(int y)
    {
        ArrayList<Integer> a = new ArrayList<>(longueur);

        for (int i = 0; i < longueur; i++)
            a.add(grille.get(i).get(y));
        return a;
    }


    public ArrayList<Integer> getLigne(int x)
    {
        ArrayList<Integer> a = new ArrayList<>(longueur);
        a.addAll(this.grille.get(x));
        return a;
    }


    public void ecrireValeur(int x, int y, int i)
    {
        /*ArrayList<Integer> unAbscisse = this.grille.get(x);
        unAbscisse.set(y,i);*/
        this.grille.get(y).set(x,i);
    }

}
