package main.Generateur.Naif;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;
import main.Grille;
import main.Solveur.Backtrack.Backtrack;
import main.Takuzu;

import java.util.Random;

public class GenerateurNaifCarre implements Generateur {

    //Premier jet d'un générateur avec niveau de difficulté modulable
    @Override
    public Takuzu generer(int taille, int difficulte) {
        Grille grid = new Grille(taille, taille);
        Random rand = new Random();

        int cote1 = rand.nextInt(taille);
        int cote2 = rand.nextInt(taille);
        int chiffre = rand.nextInt(2);
        grid.setValue(cote1, cote2, chiffre);

        Takuzu tak = new Takuzu(grid);

        Backtrack backtrack = new Backtrack();
        backtrack.resoudre(tak);
        tak = backtrack.getGagnant();



        int dif = (difficulte * taille);

        while (dif > 0) {

            cote1 = rand.nextInt(taille);
            cote2 = rand.nextInt(taille);
            if (tak.getValue(cote1, cote2) != -1) {
                tak.play(cote1, cote2, -1);
                dif--;
            }

        }

        return tak;
    }


}
