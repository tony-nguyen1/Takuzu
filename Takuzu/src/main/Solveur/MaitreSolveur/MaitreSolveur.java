package main.Solveur.MaitreSolveur;

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.PatternCroix3.PatternCroix3;
import main.Solveur.PatternCroix5.PatternCroix5;
import main.Solveur.Solveur;
import main.Takuzu;

public class MaitreSolveur implements Solveur {
    /**
     * Applique les différents Solveur sauf ceux utilisant le brute force, jusqu'à ce qu'ils soient TOUS bloquer.
     *
     * @return true à chaque fois
     */
    @Override
    public boolean resoudre(Takuzu takuzu) {
        Solveur petiteCroix, grandeCroix, equilibreur;
        boolean petiteCroixATravailler, grandeCroixATravailler, equilibreurATravailler, continuer;

        petiteCroix = new PatternCroix3();
        grandeCroix = new PatternCroix5();
        equilibreur = new Equilibre();

        petiteCroixATravailler = false;
        grandeCroixATravailler = false;
        equilibreurATravailler = false;
        continuer = false;

        do {
            petiteCroixATravailler = petiteCroix.resoudre(takuzu);
            grandeCroixATravailler = grandeCroix.resoudre(takuzu);
            equilibreurATravailler = equilibreur.resoudre(takuzu);

            //ça a créer de nouvelles possiblités (peut-être)
            continuer = petiteCroixATravailler || grandeCroixATravailler || equilibreurATravailler;
        } while(continuer);

        return true;
    }
}
