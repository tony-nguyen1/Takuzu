package main.Solveur.PatternCroix3;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution2 {
    public static void main(String[] args) {
        System.out.println("Grille facilité de 1");
        Takuzu tak = new Takuzu(3);
        tak.play1(1, 0);
        tak.play1(1, 2);
        tak.play1(0, 1);
        tak.play1(2, 1);
        tak.affichage();

        Solveur sol = new PatternCroix3(tak);
        sol.resoudre();

        tak.affichage();

        System.out.println(tak.getGrille().getGrille()[0][0]);
        System.out.println(tak.getGrille().getGrille()[0][1]);
        System.out.println(tak.getGrille().getGrille()[0][2]);

        System.out.println(tak.getGrille().getGrille()[1][0]);
        System.out.println(tak.getGrille().getGrille()[1][1]);
        System.out.println(tak.getGrille().getGrille()[1][2]);

        System.out.println(tak.getGrille().getGrille()[2][0]);
        System.out.println(tak.getGrille().getGrille()[2][1]);
        System.out.println(tak.getGrille().getGrille()[2][2]);
    }
}
