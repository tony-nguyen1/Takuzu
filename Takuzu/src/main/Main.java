package main;

import main.IHM.IHM;
import java.util.ArrayList;

//TODO
// -tests
// -Resolveur
//      -> pattern de résolution en croix 3x3
//      -> pattern de résolution en croix 5x5

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();

       takuzu.affichage();

       System.out.println("\nRéponse");
       Takuzu rep = Takuzu.getPreRemplissageAnswer();
        rep.affichage();

        System.out.println("Grille row ok ?");
        System.out.println(rep.checkAllRowAll());

        System.out.println("Grille column ok ?");
        System.out.println(rep.checkAllColumnAll());

        System.out.println("Takuzu gagnant ?");
        System.out.println(rep.estGagnant());

        /*
        System.out.println("\ncheckRowCoupleOnly");
        for (int i = 0; i < 6; i++) { System.out.println(rep.checkRowCoupleOnly(i)); }

        System.out.println("\ncheckRowBalance");
        for (int i = 0; i < 6; i++) { System.out.println(rep.checkRowBalance(i)); }

        ArrayList<Integer> a = new ArrayList<>();
        a.add(0);a.add(1);a.add(2);a.add(3);a.add(4);a.add(5);
        System.out.println("\ncheckRowUnicite");
        for (int i = 0; i < 6; i++) { System.out.println(rep.checkRowUnicite(i, a)); }
        */
    }
}
