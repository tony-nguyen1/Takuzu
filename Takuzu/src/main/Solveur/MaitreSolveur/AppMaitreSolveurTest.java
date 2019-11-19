package main.Solveur.MaitreSolveur;

import main.Solveur.Solveur;
import main.Takuzu;

public class AppMaitreSolveurTest {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(14);
        //takuzu.preRemplissagePourBenchMarkEntreNous();

        takuzu.affichage();

        /*System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer2();
        rep.affichage();*/

        long startTime = System.currentTimeMillis();
        boolean bool = takuzu.seResoudre(new MaitreSolveur());
        long endTime = System.currentTimeMillis();

        takuzu.affichage();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(bool);
    }
}
