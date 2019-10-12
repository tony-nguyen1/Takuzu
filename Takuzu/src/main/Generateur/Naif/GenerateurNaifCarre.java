package main.Generateur.Naif;

import main.Generateur.Generateur;
import main.Grille;
import java.util.Random;

public class GenerateurNaifCarre implements Generateur {
//Ce sont juste des tests pour l'instant
    @Override
    public Grille generer(int taille) {
        Grille grid = new Grille(taille, taille);
        Random rand = new Random();

        int cote1 = rand.nextInt(taille);
        int cote2 = rand.nextInt(taille);
        int chiffre = rand.nextInt(2);

        grid.setValue(cote1, cote2, chiffre);

        System.out.println(grid.toString());
        return null;
    }


}
