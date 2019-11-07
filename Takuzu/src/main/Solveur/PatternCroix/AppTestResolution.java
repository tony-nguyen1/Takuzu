package main.Solveur.PatternCroix;


import main.Solveur.PatternCroix.PatternCroix;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppTestResolution {
    public static void main(String[] args) {
        System.out.println("Grille facile de 0");
        Takuzu takuzu = new Takuzu(4);
        takuzu.play0(1,0);
        takuzu.play0(1,2);
        takuzu.play0(0,1);
        takuzu.play0(2,1);
        takuzu.affichage();

        takuzu.seResoudre(new PatternCroix());

        takuzu.affichage();





    }
}
