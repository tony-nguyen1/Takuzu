package main.Solveur.Hypotheses;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
@SuppressWarnings("Duplicates")
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
        backupTakuzu.add(takuzu.cloneTakuzu());
        Takuzu takuzuCourant, takuzuSuivant, takuzuPrecedent;
        int[] infoHypothese;
        boolean sortie;
        int cpt;

        cpt = 0;
        sortie = false;

        System.out.println("Dans la fonction");

        while (!backupTakuzu.isEmpty() && !sortie)
        {
            takuzuCourant = backupTakuzu.pollFirst();
            //System.out.println("J'essaie de le résoudre normalement");
            takuzuCourant.seResoudre(lesSolveursSimples);
            //takuzu.affichage();
            cpt++;

            if (takuzuCourant.estValide())
            {
                if (takuzuCourant.estTotalementRemplit()) {
                    System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant);
                    sortie = true;
                } else {//mais pas gagnant
                    //System.out.println("Pas encore gagnant");
                    //System.out.println("Fait une hypothèse");
                    takuzuSuivant = takuzuCourant.cloneTakuzu();
                    backupTakuzu.addFirst(takuzuCourant);

                    infoHypothese = faireUneHypothese(takuzuSuivant);
                    backupTakuzu.addFirst(takuzuSuivant);
                    backupHypotheses.addFirst(infoHypothese);
                }
            } else {//takuzuCourant est invalide
                //"faire l'inverse"
                //mais takuzuPrecedent est le Takuzu juste en dessous dans la pile est celle où on a fait l'hypothèse
                //System.out.println("Revient en arrière car hypothèses conduit à takuzu invalide");
                takuzuPrecedent = backupTakuzu.pollFirst();
                infoHypothese = backupHypotheses.pollFirst();

                //si l'hypothèse était de mettre un 0, on fait l'inverse (car l'hypothèse était fausse)
                takuzuPrecedent.play1(infoHypothese[0],infoHypothese[1]);//au même endroit

                backupTakuzu.addFirst(takuzuPrecedent);
            }
        }
        System.out.println("En dehors de la boucle");
        System.out.println("tour de boucle : " + cpt);
        return sortie;
    }

    private int[] faireUneHypothese(Takuzu takuzu) {
        int[] coordonee;

        coordonee = takuzu.trouver1erCaseVide();
        takuzu.play0(coordonee[0],coordonee[1]);

        return coordonee;
    }
}
