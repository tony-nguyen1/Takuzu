package takuzu.Solveur.Equilibre;

import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

public class Equilibre implements Solveur {

    @Override
    public boolean resoudre(Takuzu takuzu) {
        boolean didSomething = false;
        int taille = takuzu.getTailleGrille();

        for (int val = 0; val < 2; val++) {

            for (int i = 0; i < taille; i++) {

                if (takuzu.compteValeurLigne(i, val) == taille / 2) {
                    didSomething = takuzu.remplirLigneDe(i, Takuzu.inverseVal(val));
                }
                if (takuzu.compteValeurColonne(i, val) == taille / 2) {
                    didSomething = takuzu.remplirColonneDe(i, Takuzu.inverseVal(val));
                }
            }

        }
        return didSomething;
    }

}
