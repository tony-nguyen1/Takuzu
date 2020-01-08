package main.Generateur.Difficile;

import main.Generateur.Facile.GenerateurTakuzuFacile;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppTakuzuDifficile {
    public static void  main (String[] argv) {
        String path = System.getProperty("user.dir");
        Takuzu tak = Takuzu.load(path + "/Takuzu/Ressources/takuzu_taille_20.txt");
        //Takuzu tak = new Takuzu(14);
        //tak.preRemplissagePourBenchMarkEntreNous();

        GenerateurTakuzuDifficile g = new GenerateurTakuzuDifficile(tak);

        Takuzu takuzu = g.generer();
        System.out.println("Takuzu généré :");
        takuzu.affichage();

        long startTime = System.currentTimeMillis();
        takuzu.seResoudre(new Hypotheses());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
