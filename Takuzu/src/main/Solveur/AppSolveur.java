package main.Solveur;

import main.Solveur.PatternCroix.PatternCroix;
import main.Takuzu;
@SuppressWarnings("Duplicates")
public class AppSolveur {

    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage();
        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer();
        rep.affichage();

        Solveur sol = new PatternCroix();
        sol.resoudre(takuzu);
        sol.resoudre(takuzu);


        System.out.println("\nAprès résolution");
        takuzu.affichage();

        Takuzu tak = new Takuzu(6);
        tak.preRemplissage2();



        System.out.println("\nAprès résolution");
        tak.affichage();

        System.out.println("\nRéponse");
        Takuzu.getPreRemplissageAnswer2().affichage();
    }
}
