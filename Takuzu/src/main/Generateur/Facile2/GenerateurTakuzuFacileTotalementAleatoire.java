package main.Generateur.Facile2;

import main.Generateur.Generateur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class GenerateurTakuzuFacileTotalementAleatoire implements Generateur {

    private int abs;
    private int ord;
    private Random rand;
    private Solveur lesSolveursSimples;
    private int taille;

    public GenerateurTakuzuFacileTotalementAleatoire(int abs, int ord, Random rand, Solveur lesSolveursSimples, int taille) {
        this.abs = abs;
        this.ord = ord;
        this.rand = rand;
        this.lesSolveursSimples = lesSolveursSimples;
        this.taille = taille;
    }

    @Override
    public Takuzu generer() {
        Takuzu unTakuzuVide = new Takuzu(taille);


        return null;
    }
}
