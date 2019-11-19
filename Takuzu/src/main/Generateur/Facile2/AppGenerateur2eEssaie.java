package main.Generateur.Facile2;

import main.Generateur.Generateur;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppGenerateur2eEssaie {
    public static void main(String[] args) {
        Generateur g = new Generateur2eEssaie(14);

        long startTime = System.currentTimeMillis();
        Takuzu takuzuFacile = g.generer();
        long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        takuzuFacile.affichage();


        takuzuFacile.seResoudre(new MaitreSolveur());
        takuzuFacile.affichage();
    }
}
