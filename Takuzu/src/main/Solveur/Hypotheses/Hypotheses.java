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
        Takuzu foo, oof;
        int[] infoHypothese;

        while (!backupTakuzu.isEmpty())
            {
            foo = backupTakuzu.pollFirst();

            lesSolveursSimples.resoudre(takuzu);

            if (foo.estGagnant()) {
                return true;
            }
            else if (foo.estValide()) {//mais pas gagnant
                oof = foo.cloneTakuzu();
                infoHypothese = faireUneHypothese(oof);
                backupTakuzu.addFirst(oof);
                backupHypotheses.addFirst(infoHypothese);
            }
            else {//foo est invalide
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

        if (valeur == 0)
        {
            takuzu.play0(coordonee[0],coordonee[1]);
        }
        if (valeur == 1)
        {
            takuzu.play1(coordonee[0],coordonee[1]);
        }
        return new int[]{valeur,coordonee[0],coordonee[1]};
    }
}
