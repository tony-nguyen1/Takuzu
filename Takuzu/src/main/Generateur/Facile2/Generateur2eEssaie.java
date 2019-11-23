package main.Generateur.Facile2;

import main.Generateur.Facile.GenerateurTakuzuFacile;
import main.Generateur.Generateur;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class Generateur2eEssaie implements Generateur {
    private Random rand;
    private Solveur monSolveur;
    private int taille;

    public Generateur2eEssaie(int taille) {
        this.rand = new Random();
        this.monSolveur = new Hypotheses();
        this.taille = taille;
    }

    @Override
    public Takuzu generer() {
        System.out.println("Dans Generateur Facile aléatoire");
        Takuzu unTakuzu = new Takuzu(taille);
        unTakuzu.play0(0,0);

        unTakuzu.seResoudre(monSolveur);

        Generateur g = new GenerateurTakuzuFacile(20, unTakuzu);

        return g.generer();
    }
}
