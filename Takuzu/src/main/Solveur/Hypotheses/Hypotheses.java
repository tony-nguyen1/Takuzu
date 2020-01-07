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
    public static long t_trouveMeilleurEmplacement = 0;
    public static long t_faireHypothese = 0;
    public static long t_faireHypotheseInverse = 0;
    public static long t_resolution = 0;
    public static long t_verification = 0;

    public Hypotheses() {
        this.lesSolveursSimples = new MaitreSolveur();
        this.backupTakuzu = new ArrayDeque<>();
        this.backupHypotheses = new ArrayDeque<>();
        this.path = new ArrayDeque<>();
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        //System.out.println("Dans Hypotheses");
        long startTime = System.currentTimeMillis();

        backupTakuzu.add(takuzu.cloneTakuzu());

        Takuzu takuzuCourant;

        boolean trouveGagnant = false;
        int cpt = 0;

        while (!backupTakuzu.isEmpty() && !trouveGagnant)
        {
            takuzuCourant = backupTakuzu.pollFirst();
            //takuzuCourant.affichage();
            //path.addFirst(takuzuCourant.cloneTakuzu());

            long debut = System.currentTimeMillis();
            takuzuCourant.seResoudre(lesSolveursSimples);
            long fin = System.currentTimeMillis();
            t_resolution += fin - debut;
            //System.out.println("résolution simple");
            //takuzuCourant.affichage();
            //path.addFirst(takuzuCourant.cloneTakuzu());
            long deb = System.currentTimeMillis();
            boolean valide = takuzuCourant.estValide();
            long fi = System.currentTimeMillis();
            t_verification += fi-deb;
            if (valide)
            {
                //System.out.println("valide");

                if (takuzuCourant.estTotalementRemplit()) {//takuzuCourant est gagnant
                    //System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant); //Maintenant le takuzu de base, passé en paramètre est gagnant
                    //takuzuCourant.affichage();
                    trouveGagnant = true;
                } else {//valide mais pas gagnant
                    //System.out.println("pas gagnant");
                    faireHypothese(takuzuCourant);
                }
            } else {//takuzuCourant est invalide
                //"faire l'inverse"
                //System.out.println("pas valide ");
                //path.pollFirst();
                if (/*échouer à*/!faireHypotheseInverse()) { return false; }
            }

            cpt++;
            //System.out.println("Taile de la pile : " + backupTakuzu.size());
        }

        reset();


        /*long endTime = System.currentTimeMillis();
        System.out.println("tour de boucle : " + cpt);
        System.out.println("temps verification: " + t_verification);
        System.out.println("temps résolution: " + t_resolution);
        System.out.println("temps faireHypotheseInverse(): " + t_faireHypotheseInverse);
        System.out.println("temps faireHypothese(): " + t_faireHypothese);
        System.out.println("temps trouverMeilleurEmplacement(): " + t_trouveMeilleurEmplacement);
        System.out.println("Hypotheses execution time: " + (endTime-startTime) + "ms");
         */
        return trouveGagnant;
    }

    private void faireHypothese(Takuzu unTakuzu) {
        long startTime = System.currentTimeMillis();
        //faire une sauvegarde
        Takuzu takuzuSuivant = unTakuzu.cloneTakuzu();
        backupTakuzu.addFirst(unTakuzu);

        //faire une hypothèses
        Case infoHypothese = trouverMeilleurEmplacement(takuzuSuivant);//takuzu.trouver1erCaseVide();
        takuzuSuivant.play0(infoHypothese.getLigne(),infoHypothese.getColonne());

        //System.out.println("après hypothèse");
        //takuzu.affichage();

        backupTakuzu.addFirst(takuzuSuivant);
        backupHypotheses.addFirst(infoHypothese);
        long endTime = System.currentTimeMillis();
        t_faireHypothese += (endTime-startTime);
    }

    private boolean faireHypotheseInverse() {
        long startTime = System.currentTimeMillis();
        //takuzuPrecedent est le Takuzu juste en dessous dans la pile, celui qu'on a sauvegarder avant de faire l'hypothèse
        Takuzu takuzuPrecedent = backupTakuzu.pollFirst();
        Case infoHypothese = backupHypotheses.pollFirst();

        //l'hypothèse était de mettre un 0, donc on fait l'inverse (car l'hypothèse était fausse)
        if (takuzuPrecedent != null) //il n'y a plus de takuzu, on a tout essayé, le takuzu de base était non-valide
            takuzuPrecedent.play1(infoHypothese.getLigne(),infoHypothese.getColonne());//au même endroit
        else
            return false;

        //System.out.println("retour arrière et inversion dernière hypotheses");
        //takuzuPrecedent.affichage();

        backupTakuzu.addFirst(takuzuPrecedent);
        long endTime = System.currentTimeMillis();
        t_faireHypotheseInverse += (endTime-startTime);
        return true;
    }

    private void reset() { backupTakuzu.clear(); backupHypotheses.clear(); }

    private Case trouverMeilleurEmplacement(Takuzu unTakuzu) {
        long startTime = System.currentTimeMillis();
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
            int caseBas, caseHaut;

            //boucle de recherce stratégique, on essaie de placer la future hypothes entre 2 cases déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceLigne = 0; !trouverMeilleurCase & indiceLigne < taille; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne, bestCol) == -1) {

                    caseBas = unTakuzu.getValue(indiceLigne, bestCol-1);
                    caseHaut = unTakuzu.getValue(indiceLigne, bestCol+1);

                    if (caseBas != -1 && caseHaut != -1) {
                        coord = new Case(indiceLigne, bestCol);
                        trouverMeilleurCase = true;
                    }
                }
            }

            //boucle de recherce stratégique, on essaie de placer la future hypothes à côté d'une case déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceLigne = 0; !trouverMeilleurCase & indiceLigne < taille; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne, bestCol) == -1) {

                    caseBas = unTakuzu.getValue(indiceLigne, bestCol-1);
                    caseHaut = unTakuzu.getValue(indiceLigne, bestCol+1);

                    if (caseBas != -1 || caseHaut != -1) {
                        coord = new Case(indiceLigne, bestCol);
                        trouverMeilleurCase = true;
                    }
                }
            }

            //recherche d'une case vide sur la meilleur colonne
            for (int indiceLigne = 0; !trouverMeilleurCase & indiceLigne < taille; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne, bestCol) == -1) {
                    coord = new Case(indiceLigne, bestCol);
                    trouverMeilleurCase = true;
                }
            }

        }
        else {
            int caseGauche, caseDroite;

            //boucle de recherce stratégique, on essaie de placer la future hypothes entre 2 cases déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceCol = 0; !trouverMeilleurCase & indiceCol < taille; indiceCol++) {
                if (unTakuzu.getValue(bestLigne, indiceCol) == -1) {

                    caseGauche = unTakuzu.getValue(bestLigne-1, indiceCol);
                    caseDroite = unTakuzu.getValue(bestLigne+1, indiceCol);

                    if (caseGauche != -1 && caseDroite != -1) {
                        coord = new Case(bestLigne, indiceCol);
                        trouverMeilleurCase = true;
                    }
                }
            }

            //boucle de recherce stratégique, on essaie de placer la future hypothes à côté d'une cases déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceCol = 0; !trouverMeilleurCase & indiceCol < taille; indiceCol++) {
                if (unTakuzu.getValue(bestLigne, indiceCol) == -1) {

                    caseGauche = unTakuzu.getValue(bestLigne-1, indiceCol);
                    caseDroite = unTakuzu.getValue(bestLigne+1, indiceCol);

                    if (caseGauche != -1 || caseDroite != -1) {
                        coord = new Case(bestLigne, indiceCol);
                        trouverMeilleurCase = true;
                    }
                }
            }

            //recherche d'une case vide sur la meilleur ligne
            for (int indiceCol = 0; !trouverMeilleurCase & indiceCol < taille; indiceCol++) {
                if (unTakuzu.getValue(bestLigne, indiceCol) == -1)
                {
                    coord = new Case(bestLigne,indiceCol);
                    trouverMeilleurCase = true;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        t_trouveMeilleurEmplacement += (endTime-startTime);
        return coord;
    }
}
