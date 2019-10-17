package main.Solveur;

import main.Solveur.PatternCroix3.PatternCroix3;
import main.Solveur.PatternCroix5.PatternCroix5;
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

        Solveur sol = new PatternCroix5();
        sol.resoudre(takuzu);
        sol.resoudre(takuzu);

        Solveur sol2 = new PatternCroix3();
        sol2.resoudre(takuzu);
        sol2.resoudre(takuzu);

        System.out.println("\nAprès résolution");
        takuzu.affichage();

        Takuzu tak = new Takuzu(6);
        tak.preRemplissage2();

        tak.affichage();
        sol.resoudre(tak);
        sol2.resoudre(tak);
        sol.resoudre(tak);
        sol2.resoudre(tak);

        System.out.println("\nAprès résolution");
        tak.affichage();

        System.out.println("\nRéponse");
        Takuzu.getPreRemplissageAnswer2().affichage();
    }
}
