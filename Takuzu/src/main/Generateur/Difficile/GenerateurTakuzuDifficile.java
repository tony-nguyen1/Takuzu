package main.Generateur.Difficile;

import main.Generateur.Generateur;
import main.Takuzu;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.round;

public class GenerateurTakuzuDifficile implements Generateur {

    private int taille;
    private int nbHypotheses;
    private Random random;
    private Takuzu takuzu;

    public GenerateurTakuzuDifficile(Takuzu unTakuzu) {
        this.taille = taille;
        this.nbHypotheses = round(unTakuzu.nbCaseRemplie()*25/100);
        this.random = new Random();
        this.takuzu = unTakuzu;
    }

    @Override
    public Takuzu generer() {
        long startTime = System.currentTimeMillis();
        System.out.println("Dans Generateur Avec Hypotheses");

        Takuzu unTakuzuFacile = takuzu.cloneTakuzu();

        ArrayList<int[]> listCaseOccupe = listToutesCasesOccupe(unTakuzuFacile);

        int i = 0;
        while (i < nbHypotheses) {
            int[] coord = choisisElementHasard(listCaseOccupe);
            unTakuzuFacile.suppr(coord[0], coord[1]);
            System.out.println(coord[0] + " " + coord[1]);
            i++;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Generateur Takuzu avec hypotheses generer() execution time: " + (endTime-startTime) + "ms");

        return unTakuzuFacile;
    }

    private ArrayList<int[]> listToutesCasesOccupe(Takuzu unTakuzu) {
        ArrayList<int[]> listCaseOccupe = new ArrayList<>();

        for (int i = 0; i < unTakuzu.getTailleGrille(); i++) {
            for (int j = 0; j < unTakuzu.getTailleGrille(); j++) {
                if (unTakuzu.getValue(i,j) != -1) {
                    listCaseOccupe.add(new int[]{i,j});
                }
            }
        }
        return listCaseOccupe;
    }

    private int[] choisisElementHasard(ArrayList<int[]> uneListe) {
        int n = random.nextInt(uneListe.size());
        return uneListe.remove(n);
    }
}
