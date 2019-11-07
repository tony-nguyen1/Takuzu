package main.Solveur.Backtrack;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppBacktrack {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage3();
        takuzu.affichage();

        Backtrack backtrack = new Backtrack();
        backtrack.resoudre(takuzu);

        backtrack.getGagnant().affichage();

    }
}