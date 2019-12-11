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
       takuzu.play0(0,0);
       takuzu.play0(0,1);
       takuzu.play1(0,2);

       takuzu.play0(1,0);
        takuzu.play0(1,1);
        takuzu.play1(1,2);

       takuzu.affichage();
       System.out.println(takuzu.equals2Row(0,1));
       System.out.println(takuzu.equals2Row(0,2));

       System.out.println(takuzu.checkRowUnicite(0));
        System.out.println(takuzu.checkRowUnicite(1));

       System.out.println("valide ?"+takuzu.estValide());

    }

}
