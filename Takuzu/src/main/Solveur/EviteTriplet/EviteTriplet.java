package main.Solveur.EviteTriplet;

import main.Solveur.Solveur;
import main.Takuzu;

public class EviteTriplet implements Solveur {

    @Override
    public boolean resoudre(Takuzu tak) {
        int[] voisin1 = {0, 0};
        int[] voisin2 = {0, 0};
        int valVoisin1, valVoisin2;

        int column = tak.getGrille().getHEIGHT();
        int row = tak.getGrille().getWIDTH();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //On cherche une case vide pour appliquer l'algorithme
                if (tak.getValue(i, j) == -1) {

                    for (int x = 0; x < 6; x++) {
                        //On determine les voisins à tester
                        voisin1 = donneVoisin1(x);
                        voisin2 = donneVoisin2(x);

                        //On cherche la valeur des voisins
                        valVoisin1 = tak.getValue(i + voisin1[0], j + voisin1[1]);
                        valVoisin2 = tak.getValue(i + voisin2[0], j + voisin2[1]);

                        //On réalise ce test pour éviter les valeurs qui sortent du tableau et qui donneront un résultat faussé
                        if (valVoisin1 == -1 || valVoisin2 == -1){
                            //On passe directement à la suite
                            continue;
                        }

                        //On compare les valeurs, si elles correspondent, alors on mets l'inverse sur la case vide
                        if (valVoisin1 == valVoisin2) {

                            //On aurait pu mettre valVoisin2, on s'en fout c'est les memes
                            tak.playInverse(i, j, valVoisin1);
                            /*
                            System.out.println(i + " " + j);
                            System.out.println(voisin1[0] + " " + voisin1[1]);
                            System.out.println(voisin2[0] + " " + voisin2[1]);

                            */
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    /**
     *
     * Gros code pas optimisé à l'écriture, sert tout simplement à obtenir les voisins de la case pour comparer les nombres.
     * Grosso modo on cherche une case vide et on vérifie ses voisins à ses deux extremités, voici plusieurs cas possible :
     *  0 0 *
     *  0 0 *
     *  0 * 0
     *
     *  De plus on vérifie également en haut et en bas...
     *
     *   *       0              0
     *   0  ET   0   ET Enfin   *
     *   0       *              0
     *
     *   En gros ce code sert à déterminer pour x quel voisin donner de sorte à passer sur tous les cas possible
     *   PS : On pourrait utiliser une map pour rendre ca déjà bien plus propre...
     */

    private int[] donneVoisin1(int x) {
        //Ligne Colonne
        int[] voisin1 = {0, 0};

        if (x == 0) {
            voisin1[0] = 0;
            voisin1[1] = 1;
        }
        else if (x == 1) {
            voisin1[0] = 0;
            voisin1[1] = -1;
        }
        else if (x == 2){
            voisin1[0] = 1;
            voisin1[1] = 0;
        }
        else if (x == 3){
            voisin1[0] = -1;
            voisin1[1] = 0;
        }
        else if (x == 4){
            voisin1[0] = 0;
            voisin1[1] = 1;
        }
        else {
            voisin1[0] = 1;
            voisin1[1] = 0;
        }
        return voisin1;

    }

    private int[] donneVoisin2(int x) {

        int[] voisin2 = {0, 0};

        if (x == 0) {
            voisin2[0] = 0;
            voisin2[1] = 2;
        }
        else if (x == 1) {
            voisin2[0] = 0;
            voisin2[1] = -2;
        }
        else if (x == 2){
            voisin2[0] = 2;
            voisin2[1] = 0;
        }
        else if (x == 3){
            voisin2[0] = -2;
            voisin2[1] = 0;
        }
        else if (x == 4){
            voisin2[0] = 0;
            voisin2[1] = -1;
        }
        else {
            voisin2[0] = -1;
            voisin2[1] = 0;
        }
        return voisin2;

    }



}
