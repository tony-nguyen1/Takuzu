package takuzu.Solveur.Backtrack;

import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

public class BacktrackDecorator implements Solveur {

    Backtrack backtrack = new Backtrack();

    @Override
    public boolean resoudre(Takuzu takuzu) {
        Boolean retour = backtrack.resoudre(takuzu);
        return retour;
    }
}
