package main.Generateur.Facile;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;
import main.Takuzu;

public class AppTestFacile {
    public static void main(String[] args) throws OddDimensionsGrilleException {
        Generateur g = new GenerateurTakuzuFacile();

        Takuzu takuzuFacile = g.generer(1,1);//FIXME pk j'ai due mettre (1,1) ? Régler le pb avec l'interface

        takuzuFacile.affichage();
        //des fois la grille générer est particulierement simple ...
    }
}
