package main.Solveur.Hypotheses;


import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppHypotheses {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(14);
        takuzu.preRemplissagePourBenchMarkEntreNous();

        System.out.println("Dans le main");
        takuzu.affichage();
/*
        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer2();
        rep.affichage();
*/
        long startTime = System.currentTimeMillis();
        takuzu.seResoudre(new Hypotheses());
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        /*System.out.println("Réponse\n");
        Takuzu.getPreRemplissageAnswer().affichage();;
        System.out.println(Takuzu.getPreRemplissageAnswer().estGagnant());*/


        System.out.println("Fin du programme");
    }
}
