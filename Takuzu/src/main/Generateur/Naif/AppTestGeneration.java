package main.Generateur.Naif;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;

public class AppTestGeneration {

    public static void main (String[] args) throws OddDimensionsGrilleException {
        Generateur generateur = new GenerateurNaifCarre();
        generateur.generer(4);
    }
}
