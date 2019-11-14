package main.IHM2;


import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

    public class App {
        /*static int[][] grille;

        public static void special(int[][] g){
            grille = g;
            String args = "";
        }*/
        public static void main(String[] args) {
            Takuzu takuzu = new Takuzu(6);
            takuzu.preRemplissage();
            //takuzu.seResoudre(new MaitreSolveur());
            //takuzu.affichage();
            Fenetre fen = new Fenetre();
        }
    }

