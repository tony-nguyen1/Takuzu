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
        unTakuzu.play0(0,0);//TODO modifier ça


        /*unTakuzu.play0(0,1);//TODO modifier ça


        unTakuzu.play1(0,2);//TODO modifier ça*/

        /*mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());
        mettreUneCaseRandom(unTakuzu, unTakuzu.getHeightGrille(), unTakuzu.getWidthGrille());//TODO modifier ça*/





        unTakuzu.seResoudre(monSolveur);

        Generateur g = new GenerateurTakuzuFacile(20, unTakuzu);

        return g.generer();
    }

    //TODO
    private void mettreUneCaseRandom(Takuzu unTakuzu, int hauteur, int largeur) {
        int abscisse, ordonnee, valeur;
        do {
            abscisse = rand.nextInt(largeur);
            ordonnee = rand.nextInt(hauteur);
            valeur = unTakuzu.getValue(ordonnee, abscisse);
        } while (valeur != -1);//mais on supprime une case remplit, pas une case vide

        if (rand.nextInt(2) == 1) { unTakuzu.play1(abscisse, ordonnee); }
        else { unTakuzu.play0(abscisse, ordonnee); }
    }
}
