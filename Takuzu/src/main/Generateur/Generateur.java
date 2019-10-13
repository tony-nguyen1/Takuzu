package main.Generateur;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Grille;

public interface Generateur {
    Grille generer(int taille) throws OddDimensionsGrilleException;
}
