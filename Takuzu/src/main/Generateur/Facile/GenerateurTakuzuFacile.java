package main.Generateur.Facile;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class GenerateurTakuzuFacile implements Generateur {
    @Override
    public Takuzu generer() throws OddDimensionsGrilleException { //FIXME faire un parcours total de toutes les cases pour être sûr de bien enlever tous ce qui faut.
        int abscisse, ordonnee, valeurEnlever;
        boolean reussitEnleverValeur;
        Takuzu takuzuResolut, foo;
        Random rand;
        Solveur lesSolveursSimples;

        takuzuResolut = Takuzu.getPreRemplissageAnswer2();
        rand = new Random();
        lesSolveursSimples = new MaitreSolveur();

        reussitEnleverValeur = true;

        while(reussitEnleverValeur) {
            //On enlève une case au hasard
            do {
                abscisse = rand.nextInt(takuzuResolut.getWidthGrille());
                ordonnee = rand.nextInt(takuzuResolut.getHeightGrille());
                valeurEnlever = takuzuResolut.suppr(ordonnee, abscisse);
            } while (valeurEnlever == -1);//mais on supprime une case remplit, pas une case vide

            //Puis on essaye de résoudre SANS faire d'hypothèses
            foo = takuzuResolut.cloneTakuzu();
            lesSolveursSimples.resoudre(foo);

            if (foo.estGagnant()) {
                //on continue
                reussitEnleverValeur = true;
            } else {
                //les solveurs simples n'arrivent pas à résoudre
                //la valeur qu'on a enlever entraine des hypothèses, elle est trop importante
                //donc on doit la remettre
                if (valeurEnlever == 0)
                    takuzuResolut.play0(ordonnee, abscisse);
                else
                    takuzuResolut.play1(ordonnee, abscisse);
                reussitEnleverValeur = false;
            }
        }
        return takuzuResolut;
    }
}
