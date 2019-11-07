package main.Solveur.Hypotheses;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Hypotheses implements Solveur {
    private Random rand;
    private Solveur lesSolveursSimples;
    private Deque<Takuzu> backupTakuzu;
    private Deque<int[]> backupHypotheses;


    public Hypotheses() {
        this.rand = new Random();
        this.lesSolveursSimples = new MaitreSolveur();
        this.backupTakuzu = new ArrayDeque<>();
        this.backupHypotheses = new ArrayDeque<>();
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        backupTakuzu.add(takuzu);
        Takuzu takuzuCourant, oof;
        int[] infoHypothese;

        while (!backupTakuzu.isEmpty())
        {
            takuzuCourant = backupTakuzu.pollFirst();
            takuzuCourant.affichage();
            System.out.println("J'essaie de le résoudre normalement");
            lesSolveursSimples.resoudre(takuzu);

            if (takuzuCourant.estGagnant()) {
                System.out.println("J'ai trouvé le gagnant");
                return true;
            }
            else {
                System.out.println("Fait une hypothèse");
                if (takuzuCourant.estValide()) {//mais pas gagnant
                    oof = takuzuCourant.cloneTakuzu();
                    backupTakuzu.addFirst(takuzuCourant);

                    infoHypothese = faireUneHypothese(oof);
                    backupTakuzu.addFirst(oof);
                    backupHypotheses.addFirst(infoHypothese);
                }
                else {//takuzuCourant est invalide
                    //"faire l'inverse"
                    //mais oof est le Takuzu juste en dessous dans la pile est celle où on a fait l'hypothèse
                    oof = backupTakuzu.pollFirst();
                    infoHypothese = backupHypotheses.pollFirst();

                    if (infoHypothese[0] == 0) {//si l'hypothèse était de mettre un 0, on fait l'inverse (car l'hypothèse était fausse)
                        oof.play1(infoHypothese[1],infoHypothese[2]);//au même endroit
                    }
                    else {
                        oof.play0(infoHypothese[1], infoHypothese[2]);
                    }

                    backupTakuzu.addFirst(oof);
                }
            }
        }
        System.out.println("En dehors de la boucle");
        return false;
    }

    private int[] trouverAleaCaseVide(Takuzu takuzu) {
        int abs, ord, valCase;

        do {
            abs = rand.nextInt(takuzu.getWidthGrille());
            ord = rand.nextInt(takuzu.getHeightGrille());

            valCase = takuzu.getValue(ord, abs);
        } while (valCase != -1);

        return new int[]{ord, abs};
    }

    private int[] faireUneHypothese(Takuzu takuzu) {
        int valeur;
        int[] coordonee;

        valeur = rand.nextInt(2); //0 <= valeur < 2
        coordonee = trouverAleaCaseVide(takuzu);

        //valeur = 0 ou valeur = 1   sûr à 100%
        if (valeur == 0)
        {
            takuzu.play0(coordonee[0],coordonee[1]);
        }
        else //if (valeur == 1)
        {
            takuzu.play1(coordonee[0],coordonee[1]);
        }
        return new int[]{valeur,coordonee[0],coordonee[1]};
    }
}
