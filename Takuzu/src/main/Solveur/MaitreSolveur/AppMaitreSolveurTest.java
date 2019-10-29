package main.Solveur.MaitreSolveur;

import main.Solveur.PatternCroix3.PatternCroix3;
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

        Solveur s1 = new PatternCroix3();

        s1.resoudre(takuzu);

        takuzu.affichage();
    }
}
