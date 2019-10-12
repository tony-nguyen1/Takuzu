package main.Generateur.Naif;

import main.Generateur.Generateur;

public class AppTestGeneration {

    public static void main (String args[]){
        Generateur generateur = new GenerateurNaifCarre();
        generateur.generer(4);
    }
}
