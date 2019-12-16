package main;

//TODO
// -tests
// -générateur
// -IHM

import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.PatternCroix.PatternCroix;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage3();
       takuzu.affichage();

       Takuzu takuzuBis = new Takuzu(10);
       takuzuBis.preRemplissage4();

        System.out.println(takuzuBis.equals(takuzu));

        /*
       takuzu.save("./Ressources/mon-fichier.txt");

       Takuzu takuzuCharge = Takuzu.load("./Ressources/mon-fichier.txt");
       takuzuCharge.affichage();
        */
    }

}
