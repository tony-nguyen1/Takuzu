package main.Solveur.PatternCroix3;


import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution {
    public static void main(String[] args) {
        System.out.println("Grille facilité de 0");
        Takuzu takuzu = new Takuzu(4);
        takuzu.play0(1,0);
        takuzu.play0(1,2);
        takuzu.play0(0,1);
        takuzu.play0(2,1);
        takuzu.affichage();

        Solveur solveur = new PatternCroix3();
        solveur.resoudre(takuzu);

        takuzu.affichage();





    }
}
