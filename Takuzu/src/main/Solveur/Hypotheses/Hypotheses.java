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
    private Takuzu takuzuInitiale;


    public Hypotheses() {
        this.rand = new Random();
        this.lesSolveursSimples = new MaitreSolveur();
        this.backupTakuzu = new ArrayDeque<>();
        this.backupHypotheses = new ArrayDeque<>();
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        takuzuInitiale = takuzu;
        backupTakuzu.add(takuzu.cloneTakuzu());
        Takuzu takuzuCourant, takuzuSuivant, takuzuPrecedent;
        int[] infoHypothese;
        boolean sortie;

        sortie = false;

        System.out.println("Dans la fonction");

        while (!backupTakuzu.isEmpty() && !sortie)
        {
            takuzuCourant = backupTakuzu.pollFirst();
            takuzuCourant.affichage();
            System.out.println("J'essaie de le résoudre normalement");
            lesSolveursSimples.resoudre(takuzu);

            if (takuzuCourant.estGagnant()) {
                System.out.println("J'ai trouvé le gagnant");
                System.out.println("Voici le gagant -->");
                takuzuCourant.affichage();
                sortie = true;
            }
            else {
                System.out.println("Pas encore gagnant");

                if (takuzuCourant.estValide()) {//mais pas gagnant
                    System.out.println("Fait une hypothèse");
                    takuzuSuivant = takuzuCourant.cloneTakuzu();
                    backupTakuzu.addFirst(takuzuCourant);

                    infoHypothese = faireUneHypothese(takuzuSuivant);
                    backupTakuzu.addFirst(takuzuSuivant);
                    backupHypotheses.addFirst(infoHypothese);
                }
                else {//takuzuCourant est invalide
                    //"faire l'inverse"
                    //mais takuzuPrecedent est le Takuzu juste en dessous dans la pile est celle où on a fait l'hypothèse
                    System.out.println("Revient en arrière car hypothèses conduit à takuzu invalide");
                    takuzuPrecedent = backupTakuzu.pollFirst();
                    infoHypothese = backupHypotheses.pollFirst();

                    /*if (infoHypothese[0] == 0) {//si l'hypothèse était de mettre un 0, on fait l'inverse (car l'hypothèse était fausse)*/
                        takuzuPrecedent.play1(infoHypothese[0],infoHypothese[1]);//au même endroit
                    /*}
                    else {
                        takuzuPrecedent.play0(infoHypothese[1], infoHypothese[2]);
                    }*/

                    backupTakuzu.addFirst(takuzuPrecedent);
                }
            }
        }
        System.out.println("En dehors de la boucle");
        return sortie;
    }

    private int[] trouverAleaCaseVide(Takuzu takuzu) {
        /*int abs, ord, valCase;

        do {
            abs = rand.nextInt(takuzu.getWidthGrille());
            ord = rand.nextInt(takuzu.getHeightGrille());

            valCase = takuzu.getValue(ord, abs);
        } while (valCase != -1);

        return new int[]{ord, abs};*/
        int val;

        for (int ord = 0; ord < takuzu.getHeightGrille(); ord++) {
            for (int abs = 0; abs < takuzu.getWidthGrille(); abs++) {
                val = takuzu.getValue(ord, abs);

                if (val == -1) {
                    return new int[]{ord, abs};
                }
            }
        }
        return null;
    }

    private int[] faireUneHypothese(Takuzu takuzu) {
        int valeur;
        int[] coordonee;

        valeur = 0;//rand.nextInt(2); //0 <= valeur < 2 //FIXME arrêter le hasard
        coordonee = trouverAleaCaseVide(takuzu);

        //valeur = 0 ou valeur = 1   sûr à 100%
        /*if (valeur == 0)
        {*/
            takuzu.play0(coordonee[0],coordonee[1]);
        /*}
        else //if (valeur == 1)
        {
            takuzu.play1(coordonee[0],coordonee[1]);
        }*/
        return coordonee;
    }
}
