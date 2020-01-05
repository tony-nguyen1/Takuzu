package main;

//TODO
// -tests
// -générateur
// -IHM

import main.Generateur.Difficile.GenerateurTakuzuDifficile;
import main.Generateur.Facile.GenerateurTakuzuFacile;
import main.Generateur.Generateur;
import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.Equilibre.Equilibre;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.PatternCroix.PatternCroix;
import main.Solveur.Solveur;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.dir");//+"/../../../";

        int i = 16;//Integer.parseInt(args[0]);

        Takuzu tak, takCopie;
        Solveur hyp = new Hypotheses();
        Solveur croix = new PatternCroix();
        Solveur equilibre = new Equilibre();
        Solveur maitreS = new MaitreSolveur();
        Solveur backtrack = new BacktrackIntelligent();

        System.out.println("Grille facile");
        for (int nb = 1; nb <= 30; nb++) {
            tak = Takuzu.load(path + "/Takuzu/Ressources/BanqueTakuzu/Taille_"+i+"/takuzuFacile_taille_"+i+"_nb_"+nb+".txt");

            takCopie = tak.cloneTakuzu();
            takCopie.seResoudre(hyp);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            takCopie = tak.cloneTakuzu();
            takCopie.seResoudre(croix);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            takCopie = tak.cloneTakuzu();
            takCopie.seResoudre(equilibre);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            takCopie = tak.cloneTakuzu();
            takCopie.seResoudre(maitreS);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            takCopie = tak.cloneTakuzu();
            takCopie.seResoudre(backtrack);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());
        }

        /*System.out.println("Grille difficile");
        for (int nb = 1; nb <= 30; nb++) {
            tak = Takuzu.load(path + "/Takuzu/Ressources/BanqueTakuzu/Taille_" + i + "/takuzuDifficile_taille_" + i + "_nb_" + nb + ".txt");
            tak.seResoudre(hyp);
            System.out.println(tak.estTotalementRemplit());
            System.out.println(tak.estGagnant());
        }*/
    }
}
