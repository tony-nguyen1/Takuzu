package main.Solveur.Hypotheses;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.ArrayDeque;
import java.util.Deque;
@SuppressWarnings("Duplicates")
public class Hypotheses implements Solveur {
    private Solveur lesSolveursSimples;
    private Deque<Takuzu> backupTakuzu;
    private Deque<int[]> backupHypotheses;
    private Deque<Takuzu> path;


    public Hypotheses() {
        this.lesSolveursSimples = new MaitreSolveur();
        this.backupTakuzu = new ArrayDeque<>();
        this.backupHypotheses = new ArrayDeque<>();
        this.path = new ArrayDeque<>();
    }

    public void afficherChemin() {
        while (!path.isEmpty()) {
            path.pollLast().affichage();
        }
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        System.out.println("Dans Hypotheses");
        long startTime = System.currentTimeMillis();

        Takuzu clone = takuzu.cloneTakuzu();
        backupTakuzu.add(clone);

        Takuzu takuzuCourant;

        boolean sortie = false;
        int cpt = 0;

        while (!backupTakuzu.isEmpty() && !sortie)
        {
            takuzuCourant = backupTakuzu.pollFirst();
            //System.out.println("J'essaie de le résoudre normalement");
            takuzuCourant.seResoudre(lesSolveursSimples);
            path.addFirst(takuzuCourant.cloneTakuzu());

            if (takuzuCourant.estValide())
            {
                if (takuzuCourant.estTotalementRemplit()) {//takuzuCourant est gagnant
                    System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant); //Maintenant le takuzu de base, passé en paramètre est gagnant
                    sortie = true;
                    System.out.println("Compteur : " + backupTakuzu.size());
                } else {//valide mais pas gagnant
                    //System.out.println("Pas encore gagnant");
                    faireHypothese(takuzuCourant);
                }
            } else {//takuzuCourant est invalide
                //"faire l'inverse"
                if (/*échouer à*/!faireHypotheseInverse()) { return false; }
            }

            cpt++;
        }

        reset();

        long endTime = System.currentTimeMillis();
        System.out.println("tour de boucle : " + cpt);
        System.out.println("Hypotheses execution time: " + (endTime-startTime) + "ms");
        return sortie;
    }

    private int[] faireUneHypotheseAux(Takuzu takuzu) {
        int[] coordonee;

        coordonee = takuzu.trouverMeilleurCase();
        takuzu.play0(coordonee[0],coordonee[1]);

        return coordonee;
    }

    private void faireHypothese(Takuzu unTakuzu) {
        //System.out.println("Fait une hypothèse");
        //faire une sauvegarde
        Takuzu takuzuSuivant = unTakuzu.cloneTakuzu();
        backupTakuzu.addFirst(unTakuzu);
        path.addFirst(takuzuSuivant.cloneTakuzu());

        //faire une hypothèses
        int[] infoHypothese = faireUneHypotheseAux(takuzuSuivant);
        backupTakuzu.addFirst(takuzuSuivant);
        backupHypotheses.addFirst(infoHypothese);
        path.addFirst(takuzuSuivant.cloneTakuzu());
    }

    private boolean faireHypotheseInverse() {
        //takuzuPrecedent est le Takuzu juste en dessous dans la pile, celui qu'on a sauvegarder avant de faire l'hypothèse
        Takuzu takuzuPrecedent = backupTakuzu.pollFirst();
        int[] infoHypothese = backupHypotheses.pollFirst();
        path.pollFirst();path.pollFirst();

        //System.out.println("Revient en arrière car hypothèses conduit à takuzu invalide");

        //l'hypothèse était de mettre un 0, donc on fait l'inverse (car l'hypothèse était fausse)
        if (takuzuPrecedent != null) //il n'y a plus de takuzu, on a tout essayé, le takuzu de base était non-valide
            takuzuPrecedent.play1(infoHypothese[0],infoHypothese[1]);//au même endroit
        else
            return false;

        backupTakuzu.addFirst(takuzuPrecedent);
        return true;
    }

    private void reset() { backupTakuzu.clear(); backupHypotheses.clear(); }
}
