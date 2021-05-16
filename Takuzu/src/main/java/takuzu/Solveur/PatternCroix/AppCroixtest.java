package takuzu.Solveur.PatternCroix;

import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

public class AppCroixtest {

    public static void main(String[] args)
    {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage();

        System.out.println("Grille au début");
        takuzu.affichage();

        System.out.println("\nRéponse");
        Takuzu.getPreRemplissageAnswer().affichage();

        Solveur sol = new PatternCroix();

        long startTime = System.currentTimeMillis();
        boolean bool = takuzu.seResoudre(sol); takuzu.seResoudre(sol);
        long endTime = System.currentTimeMillis();

        takuzu.affichage();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(bool);
    }
}

