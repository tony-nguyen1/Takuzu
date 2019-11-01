package main.Solveur.Hypotheses;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppHypotheses {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage2();

        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer2();
        rep.affichage();

        Solveur sol = new Hypotheses();
        sol.resoudre(takuzu);

        takuzu.affichage();
    }
}
