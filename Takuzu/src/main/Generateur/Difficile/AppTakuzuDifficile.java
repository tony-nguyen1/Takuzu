package main.Generateur.Difficile;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppTakuzuDifficile {
    public static void  main (String[] argv) {
        GenerateurTakuzuDifficile g = new GenerateurTakuzuDifficile(6, 1);

        Takuzu takuzu = g.generer();
        System.out.println("Takuzu générer :");
        takuzu.affichage();

        takuzu.seResoudre(new MaitreSolveur());
        takuzu.affichage();
    }
}
