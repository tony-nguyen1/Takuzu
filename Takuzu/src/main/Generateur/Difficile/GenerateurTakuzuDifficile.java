package main.Generateur.Difficile;

import main.Generateur.Facile2.Generateur2eEssaie;
import main.Generateur.Generateur;
import main.Takuzu;

import java.util.ArrayList;
import java.util.Random;

public class GenerateurTakuzuDifficile implements Generateur {

    private int taille;
    private int nbHypotheses;
    private Random random;

    public GenerateurTakuzuDifficile(int taille, int nombreHypotheses) {
        this.taille = taille;
        this.nbHypotheses = nombreHypotheses;
        this.random = new Random();
    }

    @Override
    public Takuzu generer() {
        Generateur2eEssaie generateur = new Generateur2eEssaie(taille);

        Takuzu unTakuzuFacile = generateur.generer();
        unTakuzuFacile.affichage();

        ArrayList<int[]> listCaseOccupe = listToutesCasesOccupe(unTakuzuFacile);

        int i = 0;
        while (i < nbHypotheses) {
            int[] coord = choisisElementHasard(listCaseOccupe);
            unTakuzuFacile.suppr(coord[0], coord[1]);
            System.out.println(coord[0] + " " + coord[1]);
            i++;
        }

        return unTakuzuFacile;
    }

    private ArrayList<int[]> listToutesCasesOccupe(Takuzu unTakuzu) {
        ArrayList<int[]> listCaseOccupe = new ArrayList<>();

        for (int i = 0; i < unTakuzu.getHeightGrille(); i++) {
            for (int j = 0; j < unTakuzu.getWidthGrille(); j++) {
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
