package main.Generateur;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Takuzu;

public interface Generateur {
    Takuzu generer(int taille, int difficulte) throws OddDimensionsGrilleException;
}
