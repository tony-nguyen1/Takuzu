package main.Solveur.BruteForce;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

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

        Takuzu tak0, tak1;
        int numLigne, numColonne;

        //Utilisation des autres Solveur.
        Solveur lesAutresSolveur = new MaitreSolveur();
        lesAutresSolveur.resoudre(takuzu);

        if (takuzu.estGagnant())
            return true;
        //else on continue


        //là on est bloquer donc on commence à faire des hypothèses.
        //hypothèse
        numLigne = -1;
        numColonne = -1;
        tak0 = takuzu.cloneTakuzu();

        for (int i = 0; i < takuzu.getHeightGrille(); i++)
        {
            for (int j = 0; j < takuzu.getWidthGrille(); j++)
            {
                if (takuzu.getValue(j,i) == -1) {
                    tak0.play0(i,j); //hypothèse : à cette case, il faut un 0 pour nous débloquer.
                    numLigne = i;
                    numColonne = j;
                    break;
                }
            }
        }

        //on reessaye de résoudre en utilisant les choses simples
        lesAutresSolveur.resoudre(tak0);


        if (tak0.estGagnant())
            return true;//on a fait la bonne hypothèses
        //else on a fait la mauvaise hypothèses
        tak1 = takuzu.cloneTakuzu();
        tak1.play1(numLigne,numColonne);
        lesAutresSolveur.resoudre(tak0);
        //et là, normalement, ça marche si on a besoin que d'1 seul hyppothèses.

        return false;
    }
}
