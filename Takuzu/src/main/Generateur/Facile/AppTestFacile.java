package main.Generateur.Facile;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Generateur.Generateur;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

public class AppTestFacile {
    public static void main(String[] args) {
        Generateur g = new GenerateurTakuzuFacile(20, Takuzu.getPreRemplissageAnswer2());

        Takuzu takuzuFacile = g.generer();
        System.out.println("Takuzu générer :");
        takuzuFacile.affichage();

        takuzuFacile.seResoudre(new MaitreSolveur());
        takuzuFacile.affichage();
    }
}
