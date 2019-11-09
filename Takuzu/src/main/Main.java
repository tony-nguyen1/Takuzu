package main;

//TODO
// -tests
// -Résolveur

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.MaitreSolveur.MaitreSolveur;

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(14);
       takuzu.preRemplissagePourBenchMarkEntreNous();


       takuzu.affichage();
    }
}
