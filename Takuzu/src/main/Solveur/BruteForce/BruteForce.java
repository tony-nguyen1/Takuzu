package main.Solveur.BruteForce;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

public class BruteForce implements Solveur {

    /**
     * Résout entierrement un Takuzu. Utilises d'abord MaitreSolveur. Si cela ne suffit pas, commence à faire des hypothèses.
     *
     * @return
     */
    @Override
    public boolean resoudre(Takuzu takuzu) {

        //Utilisation des autres Solveur.
        Solveur lesAutresSolveur = new MaitreSolveur();



        return false;
    }
}
