package main.Solveur.Backtrack;

import main.Grille;
import main.Solveur.Solveur;
import main.Takuzu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Backtrack implements Solveur {

    Takuzu takuzu;

    public Backtrack(Takuzu takuzu){
        this.takuzu = takuzu;
    }

    @Override
    public boolean resoudre() {
        //On recup les infos sur la grille
        Grille grille = takuzu.getGrille();
        //Hauteur et Largeur n'a aucun sens, ecrivez des trucs censés bordel
        int row = grille.getHEIGHT();
        int line = grille.getWIDTH();

        //On va sauvegarder toutes les précédentes grilles
        Deque<Grille> backupGrilles = new LinkedList<>();
        //Et on va sauvegarder si un choix est possible ou non dans les cases (histoire de retourner en arrière plus rapidement)
        Deque<Boolean> choixPossible = new LinkedList<>();
        //Ainsi que la position ou l'on a eu le choix histoire de pas recommencer à 0 à chaque fois
        Deque<Integer[]> position = new LinkedList<>();

        //On commence à la position (0, 0) du coup
        for (int i = 0; i < row; i++){
            for (int j = 0; j < line; j++){
                //Si la case est vide
                if (grille.getValue(i, j) == -1){

                    //On cree une liste que l'on va ajouter sur la liste Position
                    Integer[] pos= {i, j};
                    position.add(pos);

                    grille.setValue(i, j, 0);
                    if (grille.isValide()) {
                        //On ajoute la grille sur la liste des grilles qui nous mènent à ce chemin
                        backupGrilles.add(grille);
                        //On teste si ca marche avec 1 du coup
                        grille.setValue(i, j, 1);
                        if (grille.isValide()) {
                            choixPossible.add(true);
                        }
                        grille.setValue(i, j, 0);
                    }

                    //Si ca marche pas avec 0, on teste avec 1
                    else {
                        grille.setValue(i, j, 0);
                        if (grille.isValide()) {
                            backupGrilles.add(grille);
                            //et on teste pas s'il il y'a un choix car c'est certain que non
                            choixPossible.add(false);
                        }
                        else {
                            //Si la grille ne fonctionne ni avec 0, ni avec 1, alors on met un -1 et on supprime la position ajouté au préalable
                            grille.setValue(i, j, -1);
                            position.poll();
                            //On retourne en arrière jusqu'à ce que l'on a un choix ?
                        }
                    }


                }
            }
        }

        return false;
    }
}
