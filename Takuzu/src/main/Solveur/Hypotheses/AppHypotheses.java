package main.Solveur.Hypotheses;


import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppHypotheses {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(14);
        takuzu.preRemplissagePourBenchMarkEntreNous();

        System.out.println("Dans le main");
        takuzu.affichage();

        takuzu.seResoudre(new Hypotheses());
        takuzu.affichage();

        System.out.println("Fin du programme");
    }
}
