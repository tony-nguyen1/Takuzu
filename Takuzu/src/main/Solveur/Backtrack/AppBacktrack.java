package main.Solveur.Backtrack;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppBacktrack {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(6);
        takuzu.play0(0, 0);
        takuzu.affichage();
        System.out.println(takuzu.estValide());

        Solveur solveur = new Backtrack(takuzu);
        //FIXME takuzu = solveur.resoudre();
        takuzu.affichage();


    }
}