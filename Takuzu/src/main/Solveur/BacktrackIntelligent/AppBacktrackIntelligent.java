package main.Solveur.BacktrackIntelligent;

import main.Solveur.Backtrack.Backtrack;
import main.Solveur.Backtrack.BacktrackDecorator;
import main.Solveur.Hypotheses.Hypotheses;
import main.Takuzu;

public class AppBacktrackIntelligent {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(14);
        //takuzu.play0(0, 0);
        takuzu.preRemplissagePourBenchMarkEntreNous();
        takuzu.affichage();

        BacktrackIntelligent b = new BacktrackIntelligent();

        long startTime = System.currentTimeMillis();
        b.resoudre(takuzu);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        b.getGagnant().affichage();
        //takuzu.seResoudre(new BacktrackIntelligent());
    }
}