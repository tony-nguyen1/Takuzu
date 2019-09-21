package main;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("\n");

        Grille g = new Grille(6);

        g.ecrireValeur(0,5,9);

        System.out.println("ligne " + g.getLigne(5));
        System.out.println("collonne" + g.getCollonne(0) + "\n");

        g.affichage();

        System.out.println();
        Takuzu t = new Takuzu();

        t.mettreUn(6,6);
        t.mettreZero(0,9);
        t.mettreUn(0,5);
        t.affichage();
    }
}
