package main.Solveur.Hypotheses;

import main.Case;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.ArrayDeque;
import java.util.Deque;
@SuppressWarnings("Duplicates")
public class Hypotheses implements Solveur {
    private Solveur lesSolveursSimples;
    private Deque<Takuzu> backupTakuzu;
    private Deque<Case> backupHypotheses;
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

        backupTakuzu.add(takuzu.cloneTakuzu());

        Takuzu takuzuCourant;

        boolean trouveGagnant = false;
        int cpt = 0;

        while (!backupTakuzu.isEmpty() && !trouveGagnant)
        {
            takuzuCourant = backupTakuzu.pollFirst();

            if (takuzuCourant.estValide())
            {
                //System.out.println("valide");
                //takuzuCourant.affichage();
                path.addFirst(takuzuCourant.cloneTakuzu());

                takuzuCourant.seResoudre(lesSolveursSimples);

                //System.out.println("résolution simple");
                //takuzuCourant.affichage();
                path.addFirst(takuzuCourant.cloneTakuzu());

                if (takuzuCourant.estTotalementRemplit()) {//takuzuCourant est gagnant
                    //System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant); //Maintenant le takuzu de base, passé en paramètre est gagnant
                    takuzuCourant.affichage();
                    trouveGagnant = true;
                } else {//valide mais pas gagnant
                    //System.out.println("pas gagnant");
                    faireHypothese(takuzuCourant);
                }
            } else {//takuzuCourant est invalide
                //"faire l'inverse"
                //System.out.print("pas valide ");
                path.pollFirst();
                //takuzuCourant.affichage();
                if (/*échouer à*/!faireHypotheseInverse()) { return false; }
            }

            cpt++;
            System.out.println("Taile de la pile : " + backupTakuzu.size());
        }

        reset();

        long endTime = System.currentTimeMillis();
        System.out.println("tour de boucle : " + cpt);
        System.out.println("Hypotheses execution time: " + (endTime-startTime) + "ms");
        return trouveGagnant;
    }

    private Case faireUneHypotheseAux(Takuzu takuzu) {
        Case coordonee;

        coordonee = trouverMeilleurEmplacement(takuzu);//takuzu.trouver1erCaseVide();
        takuzu.play0(coordonee.getLigne(),coordonee.getColonne());

        //System.out.println("après hypothèse");
        //takuzu.affichage();

        return coordonee;
    }

    private void faireHypothese(Takuzu unTakuzu) {
        //faire une sauvegarde
        Takuzu takuzuSuivant = unTakuzu.cloneTakuzu();
        backupTakuzu.addFirst(unTakuzu);

        //faire une hypothèses
        Case infoHypothese = faireUneHypotheseAux(takuzuSuivant);
        backupTakuzu.addFirst(takuzuSuivant);
        backupHypotheses.addFirst(infoHypothese);
    }

    private boolean faireHypotheseInverse() {
        //takuzuPrecedent est le Takuzu juste en dessous dans la pile, celui qu'on a sauvegarder avant de faire l'hypothèse
        Takuzu takuzuPrecedent = backupTakuzu.pollFirst();
        Case infoHypothese = backupHypotheses.pollFirst();

        //l'hypothèse était de mettre un 0, donc on fait l'inverse (car l'hypothèse était fausse)
        if (takuzuPrecedent != null) //il n'y a plus de takuzu, on a tout essayé, le takuzu de base était non-valide
            takuzuPrecedent.play1(infoHypothese.getLigne(),infoHypothese.getColonne());//au même endroit
        else
            return false;

        //System.out.println("après fausse hypothèse");
        //takuzuPrecedent.affichage();

        backupTakuzu.addFirst(takuzuPrecedent);
        return true;
    }

    private void reset() { backupTakuzu.clear(); backupHypotheses.clear(); }

    private Case trouverMeilleurEmplacement(Takuzu unTakuzu) {
        int bestLigne, bestCol, maxNbSurLigne, maxNbSurCol, nbCaseSurLigne, nbCaseSurCol, taille;

        taille = unTakuzu.getTailleGrille();

        //recherche de la ligne la moins vide avec son nombre de case vide
        bestLigne = 0;
        maxNbSurLigne = 0;
        nbCaseSurLigne = 0;
        //recherche de la colonne la moins vide avec son nombre de case vide
        bestCol = 0;
        maxNbSurCol = 0;
        nbCaseSurCol = 0;
        //parcours totale
        for (int indiceLigne = 0; indiceLigne < taille; indiceLigne++) {
            for (int indiceCol = 0; indiceCol < taille; indiceCol++) {
                if (unTakuzu.getValue(indiceLigne,indiceCol) != -1) {//on fixe le n° de ligne
                    nbCaseSurLigne++;
                }

                if (unTakuzu.getValue(indiceCol,indiceLigne) != -1) {//on fixe le n° de colonne
                    nbCaseSurCol++;
                }
            }

            ////// Comparaison entre l'actuel et l'ancien max
            if (nbCaseSurLigne > maxNbSurLigne && nbCaseSurLigne < taille) {
                maxNbSurLigne = nbCaseSurLigne;
                bestLigne = indiceLigne;
            }

            if (nbCaseSurCol > maxNbSurCol && nbCaseSurCol < taille) {
                maxNbSurCol = nbCaseSurCol;
                bestCol = indiceLigne;//correspond à un n° de col
            }

            nbCaseSurLigne = 0;
            nbCaseSurCol = 0;
        }

        //Qui est la plus remplit entre la meilleure ligne et la meilleur colonne ?
        boolean trouverMeilleurCase = false;
        Case coord = null;
        if (maxNbSurLigne < maxNbSurCol) {
            //recherche d'une case vide sur la meilleur colonne
            for (int indiceLigne = 0; !trouverMeilleurCase & indiceLigne < taille; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne, bestCol) == -1) {
                    coord = new Case(indiceLigne, bestCol);
                    trouverMeilleurCase = true;
                }
            }
        }
        else {
            //recherche d'une case vide sur la meilleur ligne
            for (int indiceCol = 0; !trouverMeilleurCase & indiceCol < taille; indiceCol++) {
                if (unTakuzu.getValue(bestLigne, indiceCol) == -1)
                {
                    coord = new Case(bestLigne,indiceCol);
                    trouverMeilleurCase = true;
                }
            }
        }

        return coord;
    }
}
