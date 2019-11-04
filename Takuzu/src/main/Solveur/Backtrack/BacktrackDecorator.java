package main.Solveur.Backtrack;

import main.Solveur.Solveur;
import main.Takuzu;

public class BacktrackDecorator implements Solveur {

    Backtrack backtrack = new Backtrack();

    @Override
    public boolean resoudre(Takuzu takuzu) {
        Boolean retour = backtrack.resoudre(takuzu);
        return retour;
    }
}
