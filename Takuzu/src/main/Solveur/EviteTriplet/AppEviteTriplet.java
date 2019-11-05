package main.Solveur.EviteTriplet;

import main.Solveur.Backtrack.Backtrack;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppEviteTriplet {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage3();
        takuzu.affichage();

        Solveur e = new EviteTriplet();

        while (e.resoudre(takuzu)){
            System.out.println("ok");
        }


        takuzu.affichage();


    }
}
