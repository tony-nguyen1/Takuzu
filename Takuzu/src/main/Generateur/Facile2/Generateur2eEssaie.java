package main.Generateur.Facile2;

import main.Generateur.Facile.GenerateurTakuzuFacile;
import main.Generateur.Generateur;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class Generateur2eEssaie implements Generateur {
    private Random rand;
    private Solveur lesSolveursSimples;
    private int taille;

    public Generateur2eEssaie(int taille) {
        this.rand = new Random();
        this.lesSolveursSimples = new Hypotheses();
        this.taille = taille;
    }

    @Override
    public Takuzu generer() {
        Takuzu unTakuzu = new Takuzu(taille);
        unTakuzu.play0(0,0);

        unTakuzu.seResoudre(lesSolveursSimples);

        Generateur g = new GenerateurTakuzuFacile(20, unTakuzu);

        return g.generer();
    }
}
