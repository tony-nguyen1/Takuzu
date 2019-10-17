package main.Solveur.PatternCroix3;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution2 {
    public static void main(String[] args) {
        System.out.println("Grille facilité de 1");
        Takuzu tak = new Takuzu(4);
        tak.play1(1, 0);
        tak.play1(1, 2);
        tak.play1(0, 1);
        tak.play1(2, 1);
        tak.affichage();

        Solveur sol = new PatternCroix3();
        sol.resoudre(tak);

        tak.affichage();
    }
}
