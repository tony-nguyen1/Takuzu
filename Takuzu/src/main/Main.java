package main;

//TODO
// -tests
// -Résolveur

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.MaitreSolveur.MaitreSolveur;

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();
       takuzu.seResoudre(new MaitreSolveur());
       takuzu.affichage();

       System.out.println("\nRéponse");
       Takuzu rep = Takuzu.getPreRemplissageAnswer();

       rep.affichage();
    }
}
