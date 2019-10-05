package main.Solveur;

import main.Takuzu;

public class AppTestResolution3 {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage();

        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer();
        //rep.affichage();

        Solveur solveur = new PatternCroix3(takuzu);
        solveur.resoudre();

        takuzu.affichage();
    }
}
