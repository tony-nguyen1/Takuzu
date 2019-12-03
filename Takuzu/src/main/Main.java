package main;

//TODO
// -tests
// -générateur
// -IHM

import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.PatternCroix.PatternCroix;

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();
       //takuzu.seResoudre(new BacktrackIntelligent());

       takuzu.affichage();//takuzu.affichageGraphique();
        System.out.println(takuzu.trouverMeilleurCase());
    }
}
