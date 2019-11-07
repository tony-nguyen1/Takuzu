package main.Solveur.MaitreSolveur;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppMaitreSolveurTest {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage2();

        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer2();
        rep.affichage();

        Solveur sol = new MaitreSolveur();
        sol.resoudre(takuzu);
        takuzu.seResoudre(sol);


        takuzu.affichage();
    }
}
