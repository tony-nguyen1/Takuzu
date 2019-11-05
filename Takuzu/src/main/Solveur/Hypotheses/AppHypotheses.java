package main.Solveur.Hypotheses;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import main.Solveur.Solveur;
import main.Takuzu;

public class AppHypotheses {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.preRemplissage3();

        takuzu.affichage();
/*
        System.out.println("\nRéponse");
        Takuzu rep = Takuzu.getPreRemplissageAnswer2();
        rep.affichage();
*/
        Solveur sol = new Hypotheses();
        sol.resoudre(takuzu);

        System.out.println("Je suis dehors");
        System.out.println("Voilà où on peut arriver sans faire d'hypothèses :");
        takuzu.affichage();
    }
}
