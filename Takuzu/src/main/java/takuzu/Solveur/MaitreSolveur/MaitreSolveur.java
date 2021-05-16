package takuzu.Solveur.MaitreSolveur;

import takuzu.Solveur.Equilibre.Equilibre;
import takuzu.Solveur.PatternCroix.PatternCroix;
import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

public class MaitreSolveur implements Solveur {

    int cpt = 0;
    /**
     * Applique les différents Solveur sauf ceux utilisant le brute force, jusqu'à ce qu'ils soient TOUS bloquer.
     *
     * @return true ssi les Solveurs PatternCroix et Equilibre ont modifié l'état de takuzu, false sinon
     */
    @Override
    public boolean resoudre(Takuzu takuzu) {
        Solveur maCroix, equilibreur;
        boolean maCroixATravailler, equilibreurATravailler, continuer, didSomething;

        maCroix = new PatternCroix();
        equilibreur = new Equilibre();

        maCroixATravailler = false;
        equilibreurATravailler = false;
        continuer = false;
        didSomething = false;

        do {
            maCroixATravailler =  takuzu.seResoudre(maCroix);
            equilibreurATravailler = takuzu.seResoudre(equilibreur);

            if (cpt == 0) { didSomething = maCroixATravailler || equilibreurATravailler; }

            //ça a créer de nouvelles possiblités (peut-être)
            continuer = maCroixATravailler || equilibreurATravailler;
            cpt++;
        } while(continuer);
        //System.out.println(cpt);
        return didSomething;
    }
}
