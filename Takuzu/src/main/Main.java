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

    public static void main(String[] args) {
        String path = System.getProperty("user.dir")+"/../../../";

        int i = Integer.parseInt(args[0]);

        long startTime, endTime;

        Takuzu tak, takCopie;
        Solveur hyp = new Hypotheses();
        Solveur croix = new PatternCroix();
        Solveur equilibre = new Equilibre();
        Solveur maitreS = new MaitreSolveur();
        Solveur backtrack = new BacktrackIntelligent();

        System.out.println("Grille facile");
        for (int nb = 1; nb <= 30; nb++) {
            tak = Takuzu.load(path + "/Takuzu/Ressources/BanqueTakuzu/Taille_"+i+"/takuzuFacile_taille_"+i+"_nb_"+nb+".txt");

            tak.affichage();

            System.out.println("hyp");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(hyp);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("croix");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(croix);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("equilibre");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(equilibre);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("maitreS");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(maitreS);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            /*takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(backtrack);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());*/
        }

        System.out.println("Grille difficile");
        for (int nb2 = 1; nb2 <= 30; nb2++) {
            tak = Takuzu.load(path + "/Takuzu/Ressources/BanqueTakuzu/Taille_"+i+"/takuzuDifficile_taille_"+i+"_nb_"+nb2+".txt");

            tak.affichage();

            System.out.println("hyp");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(hyp);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("croix");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(croix);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("equilibre");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(equilibre);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            System.out.println("maitreS");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(maitreS);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());

            /*System.out.println("backtrack");
            takCopie = tak.cloneTakuzu();
            startTime = System.currentTimeMillis();
            takCopie.seResoudre(backtrack);
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println(takCopie.estTotalementRemplit());
            System.out.println(takCopie.estGagnant());*/
        }

    }
}
