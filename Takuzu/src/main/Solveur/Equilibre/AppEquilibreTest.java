package main.Solveur.Equilibre;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppEquilibreTest {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(4);
        takuzu.play0(0,0);
        takuzu.play0(0,2);

        takuzu.play0(2,0);

        takuzu.affichage();

        Solveur sol = new Equilibre();

        sol.resoudre(takuzu);

        takuzu.affichage();

        //System.out.println(takuzu.getValue(0,0));
        //System.out.println(takuzu.getValue(0,2));

    }
}
