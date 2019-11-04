package main;

import java.util.ArrayList;

public class Takuzu {
    private Grille grille;

    public Takuzu(int i) {
        grille = new Grille(i, i);
    }

    //Nouveau constructeur que j'ai ajouté pour pouvoir utiliser une grille non vide >_>
    public Takuzu(Grille grid){
        this.grille = grid;
    }

    public void play(int ligne, int colonne, int value)
    {
        grille.setValue(ligne, colonne, value);
    }

    public Grille getGrille() { return grille; }

    //Reimplémentation de la solution de dépannage xddd
    public int getValue(int ligne, int colonne) {
        if (ligne < 0 || ligne >= getHeightGrille() || colonne < 0 || colonne >= getWidthGrille()) {
            return -1;
        }
        return grille.getValue(ligne, colonne);
    }

    public int getHeightGrille() { return grille.getHEIGHT(); }

    public int getWidthGrille() { return grille.getWIDTH(); }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //                            ICI les règles du jeu


    /**
     *
     * Sert à verifier qu'il n'y a pas plus de 0 que de 1
     *
     */
    public boolean checkRowBalance(int y) {
        int nb0 = 0, nb1 = 0;
        for (int i = 0; i < getWidthGrille(); i++) {

            if (getValue(y, i) == 0) {
                nb0++;
            }
            if (getValue(y, i) == 1) {
                nb1++;
            }
        }
        if (nb0 > getWidthGrille() / 2 || nb1 > getWidthGrille() / 2) {
            return false;
        }
        return true;
    }


    //La meme fonction qu'en haut, mais en plus simple et plus pratique à utiliser
    public boolean checkRowUnicite(int ligne) {
        for (int i = 0; i < this.getHeightGrille(); i++) {
            if (i == ligne) {
                continue;
            }
            if (this.getGrille().equals2Row(ligne, i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @pré-requis la colonne x est déjà remplis
     * Compare la colonne x avec les autres colonne remplit
     *
     * @param x the column that we compare to others column
     * @return true si la colonne est unique, faux sinon
     */

    //Grosso merdo j'ai refait la fonction, elle est meilleure dans le sens ou elle verifie également les takuzus non complet
    public boolean checkColumnBalance(int x){
        int nb0 = 0, nb1 = 0;
        for (int i = 0; i < getWidthGrille(); i++){
            //ligne , colonne
            if (getValue(i, x) == 0){
                nb0++;
            }
            if (getValue(i, x) == 1){
                nb1++;
            }
        }
        if (nb0 > getWidthGrille()/2 || nb1 > getWidthGrille()/2){
            return false;
        }
        return true;
    }


    //La meme fonction qu'en haut, mais en plus simple et plus pratique à utiliser
    public boolean checkColumnUnicite(int ligne) {
        for (int i = 0; i < this.getWidthGrille(); i++) {
            if (i == ligne) {
                continue;
            }
            if (this.getGrille().equals2Column(ligne, i)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Verifie si le Takuzu est valide ou non
     *
     * @return vrai si valide
     */
    public boolean estValide() {
        for (int i = 0; i < getHeightGrille(); i++) {
            for (int j = 0; j < getWidthGrille(); j++) {
                //Si la valeur est nulle autant la sauter xddd
                if (getValue(i, j) == -1) {
                    continue;
                }

                if (getValue(i, j) == getValue(i + 1, j) && getValue(i, j) == getValue(i - 1, j)) {
                    return false;
                }
                if (getValue(i, j) == getValue(i, j + 1) && getValue(i, j) == getValue(i, j - 1)) {
                    return false;
                }
            }
        }

        for (int i = 0; i < getHeightGrille(); i++) {
            if (grille.isRowFull(i))
                if (!checkRowUnicite(i)) {
                    return false;
                }
        }

        for (int i = 0; i < getWidthGrille(); i++) {
            if (grille.isColumnFull(i))
                if (!checkColumnUnicite(i)) {
                    return false;
                }
        }

        for (int i = 0; i < getWidthGrille(); i++){
            if (!checkColumnBalance(i)){
                return false;
            }
            //Vu qu'un Takuzu est un carré, je me permet de faire ca
            if (!checkRowBalance(i)){
                return false;
            }
        }

        return true;
    }


    public boolean estGagnant() {
        return estValide() && estTotalementRemplit();
    }

    //                         FIN des règles du jeu
    ////////////////////////////////////////////////////////////////////////////
    public void affichage() {
        System.out.println("Takuzu:\n");
        grille.affichage();
    }

    public void affichageGraphique()
    {
        grille.affichageGraphique();
    }

    //Faudra optimiser ca, bref, ca sert a faire une deep copy
    public Takuzu cloneTakuzu() {
        Grille grilleBis = new Grille(this.getWidthGrille(), getHeightGrille());
        for (int i = 0; i < this.getHeightGrille(); i++) {
            for (int j = 0; j < this.getWidthGrille(); j++) {
                grilleBis.setValue(i, j, this.getValue(i, j));
            }
        }
        return new Takuzu(grilleBis);
    }

    public void play0(int ligne, int colonne) {
        grille.setValue(ligne, colonne, 0);
    }

    public void play1(int ligne, int colonne) {
        grille.setValue(ligne, colonne, 1);
    }

    public int suppr(int ligne, int colonne) {
        int val = grille.getValue(ligne,colonne);
        grille.setValue(ligne,colonne,-1);
        return val;
    }

    /**
     * @pré-requis this doit avoir une grille "vide" et de taille 6x6.
     */
    public void preRemplissage() {
        //1er ligne
        play0(0,0);
        play0(1,0);
        play1(4,0);
        play1(5,0);

        //2e ligne
        play1(2,1);
        play1(5,1);

        //3e ligne
        play1(0,2);
        play0(3,2);

        //4e ligne
        play1(0,3);
        play0(3,3);
        play0(5,3);

        //5e ligne
        play1(1,4);

        //6e ligne
        play1(0,5);
        play1(1,5);
        play0(4,5);
        play0(5,5);
    }

    public static Takuzu getPreRemplissageAnswer()
    {
        Takuzu reponse = new Takuzu(6);

        reponse.preRemplissage();

        //1er ligne
        reponse.play1(2,0);
        reponse.play0(3,0);

        //2e ligne
        reponse.play0(0,1);
        reponse.play0(1,1);
        reponse.play1(3,1);
        reponse.play0(4,1);

        //3e ligne
        reponse.play1(1,2);
        reponse.play0(2,2);
        reponse.play1(4,2);
        reponse.play0(5,2);

        //4e ligne
        reponse.play0(1,3);
        reponse.play1(2,3);
        reponse.play1(4,3);

        //5e ligne
        reponse.play0(0,4);
        reponse.play0(2,4);
        reponse.play1(3,4);
        reponse.play0(4,4);
        reponse.play1(5,4);

        //6e ligne
        reponse.play0(2,5);
        reponse.play1(3,5);

        return reponse;
    }

    public void preRemplissage2() {
        //1er ligne
        play0(0,0);
        play0(0,2);

        //2e ligne
        play0(1,0);

        //3e ligne
        play0(2,2);
        play0(2,5);

        //4e ligne
        play1(3,1);

        //5e ligne
        play1(4,1);
        play0(4,3);
        play0(4,4);

        //6e ligne
        play1(5,3);
    }

    public static Takuzu getPreRemplissageAnswer2() {
        Takuzu reponse = new Takuzu(6);

        reponse.preRemplissage2();

        //1er ligne
        reponse.play1(0,1);
        reponse.play0(0,3);
        reponse.play1(0,4);
        reponse.play1(0,5);

        //2e ligne
        reponse.play0(1,1);
        reponse.play1(1,2);
        reponse.play1(1,3);
        reponse.play0(1,4);
        reponse.play1(1,5);

        //3e ligne
        reponse.play1(2,0);
        reponse.play0(2,1);
        reponse.play1(2,3);
        reponse.play1(2,4);

        //4e ligne
        reponse.play1(3,0);
        reponse.play0(3,2);
        reponse.play0(3,3);
        reponse.play1(3,4);
        reponse.play0(3,5);


        //5e ligne
        reponse.play0(4,0);
        reponse.play1(4,2);
        reponse.play1(4,5);

        //6e ligne
        reponse.play1(5,0);
        reponse.play0(5,1);
        reponse.play1(5,2);
        reponse.play0(5,4);
        reponse.play0(5,5);

        return reponse;
    }

    public void playInverse(int ligne, int colonne, int inverse)
    {
        if (inverse == 1)
            grille.setValue(ligne, colonne, 0);
        else if (inverse == 0)
            grille.setValue(ligne, colonne, 1);
        else
            System.err.println(inverse + "pas bonne valeur");
    }

    public boolean estTotalementRemplit() {
        return grille.estTotalementRemplit();
    }

    public void metDansPremierCaseVide(int val)
    {
        for (int i = 0; i < grille.getHEIGHT(); i++)
        {
            for (int j = 0; j < grille.getWIDTH(); j++)
            {
                if (grille.getValue(j,i) == -1) {
                    if (val == 1)
                        play1(i,j);
                    else if (val == 0)
                        play0(i,j);
                    break;
                }
            }
        }
    }

    public boolean remplirLigneDe(int numLigne, int val) {
        return grille.remplirLigneDe(numLigne,val);
    }

    public boolean remplirColonneDe(int numColonne, int val) {
        return grille.remplirColonneDe(numColonne,val);
    }
}
