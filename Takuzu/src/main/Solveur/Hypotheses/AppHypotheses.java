package main.Solveur.Hypotheses;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.hypothesesIntelligent.HypothesesIntelligent;
import main.Takuzu;

public class AppHypotheses {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(14);
        takuzu.preRemplissagePourBenchMarkEntreNous();

        Hypotheses hypotheses = new Hypotheses();
        takuzu.seResoudre(hypotheses);

        takuzu.affichage();
        /*System.out.println("Chemin");
        hypotheses.afficherChemin();*/

        System.out.println("Fin du programme");
    }
}
