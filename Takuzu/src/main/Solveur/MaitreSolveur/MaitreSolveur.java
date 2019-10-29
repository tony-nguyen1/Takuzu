package main.Solveur.MaitreSolveur;

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
        Solveur petiteCroix, grandeCroix;
        boolean petiteCroixATravailler, grandeCroixATravailler, continuer;

        petiteCroix = new PatternCroix3();
        grandeCroix = new PatternCroix5();

        petiteCroixATravailler = false;
        grandeCroixATravailler = false;
        continuer = false;

        do {
            petiteCroixATravailler = petiteCroix.resoudre(takuzu);
            grandeCroixATravailler = grandeCroix.resoudre(takuzu);

            //ça a créer de nouvelles possiblités (peut-être)
            continuer = petiteCroixATravailler || grandeCroixATravailler;
        } while(continuer);

        return true;
    }
}
