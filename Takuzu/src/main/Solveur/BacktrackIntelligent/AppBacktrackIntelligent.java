package main.Solveur.BacktrackIntelligent;

import main.Solveur.Backtrack.Backtrack;
import main.Solveur.Backtrack.BacktrackDecorator;
import main.Solveur.Hypotheses.Hypotheses;
import main.Takuzu;

public class AppBacktrackIntelligent {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(10);
        //takuzu.preRemplissagePourBenchMarkEntreNous();
        takuzu.affichage();


        long startTime = System.currentTimeMillis();
        takuzu.seResoudre(new BacktrackIntelligent());
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        takuzu.affichage();
    }
}