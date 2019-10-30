package main.Solveur.Equilibre;

import main.Solveur.Solveur;
import main.Takuzu;

public class Equilibre implements Solveur {
    private boolean didSomething;

    public Equilibre() {
        this.didSomething = false;
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {

        resoudParLigne(takuzu);
        resoudParColonne(takuzu);

        if (didSomething) {
            didSomething = false;
            return true;
        }
        return false;
    }

    private void resoudParLigne(Takuzu takuzu) {
        int nbDe0, nbDe1, largeur, valCase, hauteur;

        largeur = takuzu.getWidthGrille();
        hauteur = takuzu.getHeightGrille();

        for (int numLigne = 0; numLigne < hauteur; numLigne++)
        {
            nbDe0 = 0;
            nbDe1 = 0;

            for (int numColonne = 0; numColonne < largeur; numColonne++) {
                //parcours de chaque élément d'une ligne

                valCase = takuzu.getValue(numLigne,numColonne);

                if (valCase == 0) {
                    nbDe0++;
                }
                if (valCase == 1) {
                    nbDe1++;
                }
            }

            if (nbDe0 == largeur/2) {
                didSomething = takuzu.remplirLigneDe(numLigne,1);
            }
            else if (nbDe1 == largeur/2) {
                didSomething = takuzu.remplirLigneDe(numLigne,0);
            }
        }
    }

    private void resoudParColonne(Takuzu takuzu) {
        int nbDe0, nbDe1, largeur, valCase, hauteur;

        largeur = takuzu.getWidthGrille();
        hauteur = takuzu.getHeightGrille();

        for (int numColonne = 0; numColonne < hauteur; numColonne++)
        {
            nbDe0 = 0;
            nbDe1 = 0;

            for (int numLigne = 0; numLigne < largeur; numLigne++) {
                //parcours de chaque élément d'une colonne

                valCase = takuzu.getValue(numLigne,numColonne);

                if (valCase == 0) {
                    nbDe0++;
                }
                if (valCase == 1) {
                    nbDe1++;
                }
            }

            if (nbDe0 == largeur/2) {
                didSomething = takuzu.remplirColonneDe(numColonne,1);
            }
            else if (nbDe1 == largeur/2) {
                didSomething = takuzu.remplirColonneDe(numColonne,0);
            }
        }
    }
}
