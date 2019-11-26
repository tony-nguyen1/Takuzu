package main.Solveur.hypothesesIntelligent;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;
import sun.awt.image.ImageWatched;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

@SuppressWarnings("Duplicates")
public class HypothesesIntelligent implements Solveur {
    private Solveur lesSolveursSimples;
    private Deque<Takuzu> backupTakuzu;
    private Deque<int[]> backupHypotheses;


    public HypothesesIntelligent() {
        this.lesSolveursSimples = new MaitreSolveur();
        this.backupTakuzu = new ArrayDeque<>();
        this.backupHypotheses = new ArrayDeque<>();
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        System.out.println("Dans HypothesesIntelligent");
        long startTime = System.currentTimeMillis();

        backupTakuzu.add(takuzu.cloneTakuzu());

        Takuzu takuzuCourant;

        boolean sortie = false;
        int cpt = 0;

        while (!backupTakuzu.isEmpty() && !sortie)
        {
            takuzuCourant = backupTakuzu.pollFirst();
            //System.out.println("J'essaie de le résoudre normalement");
            takuzuCourant.seResoudre(lesSolveursSimples);
            //takuzu.affichage();

            if (takuzuCourant.estValide())
            {
                if (takuzuCourant.estTotalementRemplit()) {//takuzuCourant est gagnant
                    System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant); //Maintenant le takuzu de base, passé en paramètre est gagnant
                    sortie = true;
                } else {//valide mais pas gagnant
                    //System.out.println("Pas encore gagnant");
                    faireHypothese(takuzuCourant);
                    //break;
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
        System.out.println("HypothesesIntelligent execution time: " + (endTime-startTime) + "ms");
        return sortie;
    }

    private int[] faireUneHypotheseAux(Takuzu unTakuzu) {
        int[] coordonee = trouverMeilleurEmplacement(unTakuzu);
        unTakuzu.play0(coordonee[0],coordonee[1]);

        return coordonee;
    }

    private int[] trouverMeilleurEmplacement(Takuzu unTakuzu) {
        long startTime = System.currentTimeMillis();
        //System.out.println("Dans trouverMeilleurEmplacement");
        //unTakuzu.affichage();
        LinkedList<LinkedList<int[]>> coordDeToutesLesCasesVides;
        int[] nbCasesVidesParLigne, nbCasesVidesParColonne;
        int numLigne, numCol, bestLigne, bestCol, maxNbCasesVidesLignes, maxNbCasesVidesColonnes, Largeur, Hauteur;

        nbCasesVidesParLigne = new int[unTakuzu.getHeightGrille()];//indice du array : indice de la ligne/colonne
        nbCasesVidesParColonne = new int[unTakuzu.getWidthGrille()];//array[i] : nb de cases vides sur la i-ème ligne/colonne

        coordDeToutesLesCasesVides = unTakuzu.rechercheCasesVide();

        //comptage du nombre de cases vides par colonnes et par lignes
        for (LinkedList<int[]> uneLigne : coordDeToutesLesCasesVides) {
            for (int[] coord : uneLigne) {
                //System.out.println(coord[0] + " " + coord[1]);

                numLigne = coord[0];
                numCol = coord[1];

                nbCasesVidesParLigne[numLigne]++;
                nbCasesVidesParColonne[numCol]++;
            }
        }

        //System.out.print("nbCasesVidesParColonne : ");
        //for (int val : nbCasesVidesParColonne) { System.out.print(val); }
        //System.out.print("\n");

        //System.out.print("nbCasesVidesParLigne : ");
        //for (int val : nbCasesVidesParLigne) { System.out.print(val); }
        //System.out.print("\n");

        //recherche de la meilleur ligne à compléter
        //parcour total à la recherche du max
        Hauteur = unTakuzu.getHeightGrille();
        bestLigne = -1; maxNbCasesVidesLignes = -1;
        for (int i = 0; i < Hauteur; i++)
        {
            int val = nbCasesVidesParLigne[i];

            if (val < Hauteur) {//ligne avec trou(s)
                if (val > maxNbCasesVidesLignes) {
                    bestLigne = i;
                    maxNbCasesVidesLignes = val;
                }
            }
        }

        //recherche de la meilleur colonne à compléter
        //parcour total à la recherche du max
        Largeur = unTakuzu.getWidthGrille();
        bestCol = -1; maxNbCasesVidesColonnes = -1;
        for (int i = 0; i < Largeur; i++)
        {
            int val = nbCasesVidesParColonne[i];

            if (val < Largeur) {//colonne avec trou(s)
                if (val > maxNbCasesVidesColonnes) {
                    bestCol = i;
                    maxNbCasesVidesColonnes = val;
                }
            }
        }
        //System.out.println("bestCol :" + bestCol + " " + "bestLigne :" + bestLigne);
        //System.out.println("maxNbCasesVidesColonnes :" + maxNbCasesVidesColonnes + " " + "maxNbCasesVidesLignes :" + maxNbCasesVidesLignes);
        //maintenant on sait quels lignes et colonnes sont les plus remplies

        if (maxNbCasesVidesLignes >= maxNbCasesVidesColonnes) {
            //System.out.println("1er cas");

            //on prend la 1er coordonnée de case qui correspond à la ligne la plus remplit
            long endTime = System.currentTimeMillis();
            //System.out.println("HypothesesIntelligent execution time: " + (endTime-startTime) + "ms");
            return coordDeToutesLesCasesVides.get(bestLigne).get(0);
        }
        //else {
        //System.out.println("2e cas");
        for (LinkedList<int[]> uneLigne : coordDeToutesLesCasesVides) {
            for (int[] uneCoordonnee : uneLigne) {

                //on prend la 1er coordonnée de case qui correspond à la colonne la plus remplit
                if (uneCoordonnee[1] == bestCol) {
                    //System.out.println(uneCoordonnee[0] + " " + uneCoordonnee[1]);
                    long endTime = System.currentTimeMillis();
                    //System.out.println("HypothesesIntelligent execution time: " + (endTime-startTime) + "ms");
                    return uneCoordonnee;
                }
            }
        }
        //}

        return null;
    }

    private void faireHypothese(Takuzu unTakuzu) {
        //System.out.println("Fait une hypothèse");
        //faire une sauvegarde
        Takuzu takuzuSuivant = unTakuzu.cloneTakuzu();
        backupTakuzu.addFirst(unTakuzu);

        //faire une hypothèses
        int[] infoHypothese =  faireUneHypotheseAux(takuzuSuivant);
        backupTakuzu.addFirst(takuzuSuivant);
        backupHypotheses.addFirst(infoHypothese);
    }

    private boolean faireHypotheseInverse() {
        //takuzuPrecedent est le Takuzu juste en dessous dans la pile, celui qu'on a sauvegarder avant de faire l'hypothèse
        Takuzu takuzuPrecedent = backupTakuzu.pollFirst();
        int[] infoHypothese = backupHypotheses.pollFirst();

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
