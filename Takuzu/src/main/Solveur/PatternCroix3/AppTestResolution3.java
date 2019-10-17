package main.Solveur.PatternCroix3;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution3 {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage();

        takuzu.affichage();

        Solveur solveur = new PatternCroix3();
        solveur.resoudre(takuzu);

        takuzu.affichage();
    }
}
