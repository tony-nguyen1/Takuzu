package main.Solveur.Backtrack;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppBacktrack {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(4);
        takuzu.play0(1, 0);
        takuzu.play0(1, 2);
        takuzu.play0(0, 1);
        takuzu.play0(2, 1);
        takuzu.affichage();

        Solveur solveur = new Backtrack(takuzu);
        solveur.resoudre();

        takuzu.affichage();


    }
}