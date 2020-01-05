package main.Generateur.Difficile;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppTakuzuDifficile {
    public static void  main (String[] argv) {
        Takuzu tak = new Takuzu(14);
        tak.preRemplissagePourBenchMarkEntreNous();
        GenerateurTakuzuDifficile g = new GenerateurTakuzuDifficile(tak);

        Takuzu takuzu = g.generer();
        System.out.println("Takuzu générer :");
        takuzu.affichage();

        takuzu.seResoudre(new MaitreSolveur());
        takuzu.affichage();
    }
}
