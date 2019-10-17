package main.Solveur.PatternCroix5;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppCroix5test {

    public static void main(String[] args)
    {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage();

        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer();
        rep.affichage();

        Solveur sol = new PatternCroix5();
        sol.resoudre(takuzu);
        sol.resoudre(takuzu);
        sol.resoudre(takuzu);

        takuzu.affichage();
    }
}

