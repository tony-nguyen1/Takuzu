package main.Solveur.BacktrackIntelligent;

import main.Solveur.Backtrack.Backtrack;
import main.Takuzu;

public class AppBacktrackIntelligent {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(8);
        takuzu.play0(0, 0);
        takuzu.affichage();

        BacktrackIntelligent b = new BacktrackIntelligent();
        b.resoudre(takuzu);

        b.getGagnant().affichage();

    }
}