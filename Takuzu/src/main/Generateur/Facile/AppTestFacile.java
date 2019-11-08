package main.Generateur.Facile;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;
import main.Takuzu;

public class AppTestFacile {
    public static void main(String[] args) throws OddDimensionsGrilleException {
        Generateur g = new GenerateurTakuzuFacile();

        Takuzu takuzuFacile = g.generer();

        takuzuFacile.affichage();
        //des fois la grille générer est particulierement simple ...
    }
}
