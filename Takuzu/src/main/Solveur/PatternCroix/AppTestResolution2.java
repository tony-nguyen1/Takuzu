package main.Solveur.PatternCroix;

import main.Solveur.PatternCroix.PatternCroix;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution2 {
    public static void main(String[] args) {
        System.out.println("Grille facile de 1");
        Takuzu tak = new Takuzu(4);
        tak.play1(1, 0);
        tak.play1(1, 2);
        tak.play1(0, 1);
        tak.play1(2, 1);
        tak.affichage();

        tak.seResoudre(new PatternCroix());

        tak.affichage();
    }
}
