package main;

//TODO
// -tests
// -Résolveur

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.PatternCroix.PatternCroix;

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();
       takuzu.seResoudre(new PatternCroix());
       takuzu.affichage();
    }
}
