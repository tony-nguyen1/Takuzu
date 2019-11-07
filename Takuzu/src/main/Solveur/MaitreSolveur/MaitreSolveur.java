package main.Solveur.MaitreSolveur;

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.PatternCroix.PatternCroix;
import main.Solveur.Solveur;
import main.Takuzu;

public class MaitreSolveur implements Solveur {

    int cpt = 0;
    /**
     * Applique les différents Solveur sauf ceux utilisant le brute force, jusqu'à ce qu'ils soient TOUS bloquer.
     *
     * @return true à chaque fois
     */
    @Override
    public boolean resoudre(Takuzu takuzu) {
        Solveur maCroix, equilibreur;
        boolean maCroixATravailler, equilibreurATravailler, continuer;

        maCroix = new PatternCroix();
        equilibreur = new Equilibre();

        maCroixATravailler = false;
        equilibreurATravailler = false;
        continuer = false;

        do {
            cpt++;

            maCroixATravailler =  takuzu.seResoudre(maCroix);
            equilibreurATravailler = takuzu.seResoudre(equilibreur);

            //ça a créer de nouvelles possiblités (peut-être)
            continuer = maCroixATravailler || equilibreurATravailler;
        } while(continuer);
        System.out.println(cpt);
        return true;
    }
}
