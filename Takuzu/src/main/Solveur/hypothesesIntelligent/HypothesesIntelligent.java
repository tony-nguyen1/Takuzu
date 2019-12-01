package main.Solveur.hypothesesIntelligent;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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
        System.out.println("Dans Hypotheses");
        long startTime = System.currentTimeMillis();

        Takuzu clone = takuzu.cloneTakuzu();
        backupTakuzu.add(clone);

        Takuzu takuzuCourant;

        boolean sortie = false;
        int cpt = 0;

        while (!backupTakuzu.isEmpty() && !sortie)//tant qu'on a pas trouvé le gagnant
        {
            takuzuCourant = backupTakuzu.pollFirst();
            takuzuCourant.seResoudre(lesSolveursSimples);

            if (takuzuCourant.estValide())
            {
                if (takuzuCourant.estTotalementRemplit()) {//takuzuCourant est gagnant
                    System.out.println("J'ai trouvé le gagnant");
                    takuzu.remplirLaDifference(takuzuCourant); //Maintenant le takuzu de base, passé en paramètre est gagnant
                    sortie = true;
                } else {//valide mais pas gagnant
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

        coordonee = trouverMeilleurEmplacement(takuzu);
        takuzu.play0(coordonee[0],coordonee[1]);

        return coordonee;
    }

    private int[] trouverMeilleurEmplacement(Takuzu unTakuzu) {
        int bestLigne, bestCol, maxNbSurLigne, maxNbSurCol, nb, Largeur, Hauteur;

        Largeur = unTakuzu.getWidthGrille();
        Hauteur = unTakuzu.getHeightGrille();

        //recherche de la ligne la moins vide avec son nombre de case vide
        bestLigne = 0;
        maxNbSurLigne = 0;
        nb = 0;
        for (int indiceLigne = 0; indiceLigne < Hauteur; indiceLigne++) {
            for (int indiceCol = 0; indiceCol < Largeur; indiceCol++) {
                if (unTakuzu.getValue(indiceLigne,indiceCol) != -1) {
                    nb++;
                }
            }

            if (nb > maxNbSurLigne && nb < Largeur) {
                maxNbSurLigne = nb;
                bestLigne = indiceLigne;
            }

            nb = 0;
        }

        //recherche de la colonne la moins vide avec son nombre de case vide
        bestCol = 0;
        maxNbSurCol = 0;
        nb = 0;
        for (int indiceCol = 0; indiceCol < Hauteur; indiceCol++) {
            for (int indiceLigne = 0; indiceLigne < Largeur; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne,indiceCol) != -1)
                    nb++;
            }

            if (nb > maxNbSurCol && nb < Hauteur) {
                maxNbSurCol = nb;
                bestCol = indiceCol;
            }

            nb = 0;
        }

        //Qui est la moins vide entre la meilleure ligne et la meilleur colonne ?
        int caseGauche, caseDroite, caseM;
        if (maxNbSurLigne > maxNbSurCol) {
            //recherche d'une case vide sur la meilleur ligne

            //boucle de recherce stratégique, on essaie de placer la future hypothes entre 2 cases déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceCol = 0; indiceCol < Largeur; indiceCol++) {

                caseM = unTakuzu.getValue(bestLigne, indiceCol);
                caseGauche = unTakuzu.getValue(bestLigne, indiceCol-1);
                caseDroite = unTakuzu.getValue(bestLigne, indiceCol+1);
                //recherce d'un emplacement stratégique qui est à côté d'une autre case remplit
                if ((caseM == -1 &&  caseGauche != -1 && caseDroite != -1))
                {
                    //System.out.println(bestLigne +""+ indiceCol);
                    return new int[]{bestLigne,indiceCol};
                }
            }

            //boucle de recherce stratégique, on essaie de placer la future hypothes à côté d'une case déjà remplit en espérant faire travailler les algo de résolution simple
            for (int indiceCol = 0; indiceCol < Largeur; indiceCol++) {

                caseM = unTakuzu.getValue(bestLigne, indiceCol);
                caseGauche = unTakuzu.getValue(bestLigne, indiceCol-1);
                caseDroite = unTakuzu.getValue(bestLigne, indiceCol+1);

                if ((caseM == -1 &&  caseGauche != -1) || (caseM == -1 && caseDroite != -1))
                {
                    //System.out.println(bestLigne +""+ indiceCol);
                    return new int[]{bestLigne,indiceCol};
                }
            }

            //juste au cas où la colonne est vide
            for (int indiceCol = 0; indiceCol < Largeur; indiceCol++) {
                if (unTakuzu.getValue(bestLigne, indiceCol) == -1)
                {
                    //System.out.println(bestLigne +""+ indiceCol);
                    return new int[]{bestLigne,indiceCol};
                }
            }
        }
        else {
            //recherche d'une case vide sur la meilleur colonne

            //boucle de recherce stratégique, on essaie de placer la future hypothes entre 2 cases déjà remplit pour profiter des algo de résolution simple
            for (int indiceLigne = 0; indiceLigne < Hauteur; indiceLigne++) {

                caseM = unTakuzu.getValue(indiceLigne, bestCol);
                caseGauche = unTakuzu.getValue(indiceLigne-1, bestCol);
                caseDroite = unTakuzu.getValue(indiceLigne+1, bestCol);

                if ((caseM == -1 && caseGauche != -1 && caseDroite != -1)) {
                    //System.out.println(indiceLigne + "" + bestCol);
                    return new int[]{indiceLigne, bestCol};
                }
            }

            //boucle de recherce stratégique, on essaie de placer la future hypothes à côté d'une case déjà remplit pour profiter des algo de résolution simple
            for (int indiceLigne = 0; indiceLigne < Hauteur; indiceLigne++) {

                caseM = unTakuzu.getValue(indiceLigne, bestCol);
                caseGauche = unTakuzu.getValue(indiceLigne-1, bestCol);
                caseDroite = unTakuzu.getValue(indiceLigne+1, bestCol);

                if ((caseM == -1 && caseGauche != -1) || (caseM == -1 && caseDroite != -1)) {
                    //System.out.println(indiceLigne + "" + bestCol);
                    return new int[]{indiceLigne, bestCol};
                }
            }

            //juste au cas où la colonne est vide
            for (int indiceLigne = 0; indiceLigne < Hauteur; indiceLigne++) {
                if (unTakuzu.getValue(indiceLigne, bestCol) == -1) {
                    //System.out.println(indiceLigne + "" + bestCol);
                    return new int[]{indiceLigne, bestCol};
                }
            }
        }

        return null;
    }

    private void faireHypothese(Takuzu unTakuzu) {
        //faire une sauvegarde
        Takuzu takuzuSuivant = unTakuzu.cloneTakuzu();
        backupTakuzu.addFirst(unTakuzu);

        //faire une hypothèses
        int[] infoHypothese = faireUneHypotheseAux(takuzuSuivant);
        backupTakuzu.addFirst(takuzuSuivant);
        backupHypotheses.addFirst(infoHypothese);
    }

    private boolean faireHypotheseInverse() {
        //takuzuPrecedent est le Takuzu juste en dessous dans la pile, celui qu'on a sauvegarder avant de faire l'hypothèse
        Takuzu takuzuPrecedent = backupTakuzu.pollFirst();
        int[] infoHypothese = backupHypotheses.pollFirst();


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
