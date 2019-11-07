package main.Solveur.EviteTriplet;

import main.Solveur.Backtrack.Backtrack;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppEviteTriplet {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(10);
        //takuzu.preRemplissage3();
        takuzu.play1(1,0);
        takuzu.play1(6,0);
        takuzu.play0(7,0);
        takuzu.play1(8,0);
        takuzu.play1(9,0);

        takuzu.affichage();

        Solveur e = new EviteTriplet();

        while (e.resoudre(takuzu)){
            System.out.println("ok");
        }


        takuzu.affichage();
    }
}
