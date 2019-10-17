package main.Solveur.BruteForce;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;
@SuppressWarnings("Duplicates")
public class BruteForce implements Solveur {

    /**
     * @pré-requis 1 seul hypothèses (temporaire) //FIXME faire en sorte que ça marche quand il y a plusieurs hypothèses.
     *
     * Résout entierrement un Takuzu. Utilises d'abord MaitreSolveur. Si cela ne suffit pas, commence à faire des hypothèses.
     *
     * @return true si il a terminé le takuzu, faux sinon
     */
    @Override
    public boolean resoudre(Takuzu takuzu) {

        MaitreSolveur lesAutresSolveur = new MaitreSolveur();

        Takuzu taku = resoudre(takuzu,lesAutresSolveur);

        //taku est une copie, reproduire taku sur this.

        return taku.estGagnant();
    }


    private Takuzu resoudre(Takuzu takuzu, MaitreSolveur s)
    {
        s.resoudre(takuzu);
        //Là il est bloqué

        //début des hypothèses.
        Takuzu tak0, tak1;
        tak0 = takuzu.cloneTakuzu();
        tak1 = takuzu.cloneTakuzu();

        takuzu.metDansPremierCaseVide(0);
        takuzu.metDansPremierCaseVide(1);

        s.resoudre(tak0);
        s.resoudre(tak1);
        //Là il est encore bloqué

        if (tak0.estTotalementRemplit()) {
            if (tak0.estGagnant()) {
                return tak0;
            }
            else {
                resoudre(tak0, s);
            }
        }
        else
            {
            if (tak1.estTotalementRemplit()) {
                if (tak1.estGagnant()) {
                    return tak1;
                }
                else {
                    resoudre(tak1, s);
                }
            }
        }
        return null;
    }
}
