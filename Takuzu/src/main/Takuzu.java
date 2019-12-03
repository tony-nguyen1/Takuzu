package main;

import main.Generateur.Generateur;
import main.Solveur.Solveur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Takuzu {
    private int[][] grille;
    private final int TAILLE;

    public Takuzu(int i) {
        grille = new int[i][i];
        fill(-1);
        TAILLE = i;
    }

    public void play0(int ligne, int colonne){
        this.setValue(ligne, colonne, 0);
    }

    public void play1(int ligne, int colonne) {
        this.setValue(ligne, colonne, 1);
    }

    public int suppr(int ligne, int colonne) {
        int val = this.getValue(ligne,colonne);
        this.setValue(ligne,colonne,-1);
        return val;
    }

    public int getTailleGrille() { return TAILLE; }

    public int[][] getGrille() {
        return grille;
    }

    public int compteValeurLigne(int i, int val) {
        int compteurValeur = 0;
        for (int j = 0; j < TAILLE; j++) {
            if (grille[i][j] == val)
                compteurValeur++;
        }
        return compteurValeur;
    }

        public int compteValeurColonne(int i, int val) {
            int compteValeur = 0;
            for (int j = 0; j < TAILLE; j++) {
                if (grille[j][i] == val)
                    compteValeur++;
            }
            return compteValeur;
        }

        public static int inverseVal(int i){
        if (i == 0)
            return 1;
        else if(i == 1)
            return 0;
        else
            return -1;
        }


    /**
     * Sert à verifier qu'il n'y a pas plus de 0 que de 1 dans la ligne y
     */
    public boolean checkRowBalance(int y) {
        int nb0 = 0, nb1 = 0;
        for (int i = 0; i < getTailleGrille(); i++) {

            if (getValue(y, i) == 0) {
                nb0++;
            }
            if (getValue(y, i) == 1) {
                nb1++;
            }
        }
        return nb0 <= getTailleGrille() / 2 && nb1 <= getTailleGrille() / 2;
    }


    /**
     * @param ligne que l'on va comparer avec les autres
     * @return vrai si la ligne ne correspond à aucune autre ligne, faux sinon
     */
    public boolean checkRowUnicite(int ligne) {
        for (int i = 0; i < this.getTailleGrille(); i++) {
            if (i == ligne) {
                continue;
            }
            if (this.equals2Row(ligne, i)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Sert à verifier qu'il n'y a pas plus de 0 que de 1 dans la colonne y
     */
    public boolean checkColumnBalance(int x){
        int nb0 = 0, nb1 = 0;
        for (int i = 0; i < getTailleGrille(); i++){
            //ligne , colonne
            if (getValue(i, x) == 0){
                nb0++;
            }
            if (getValue(i, x) == 1){
                nb1++;
            }
        }
        return nb0 <= getTailleGrille() / 2 && nb1 <= getTailleGrille() / 2;
    }


    /**
     * Compare la colonne x avec les autres colonne remplit
     *
     * @return vrai si la colonne est unique, faux sinon
     */
    public boolean checkColumnUnicite(int ligne) {
        for (int i = 0; i < this.getTailleGrille(); i++) {
            if (i == ligne) {
                continue;
            }
            if (equals2Column(ligne, i)) {
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

        for (int i = 0; i < getTailleGrille(); i++){
            if (!checkRowBalance(i)){
                return false;
            }

            if (!checkColumnBalance(i)){
                return false;
            }

        }

        for (int i = 0; i < getTailleGrille(); i++) {
            for (int j = 0; j < getTailleGrille(); j++) {
                //Si la valeur est nulle autant la sauter
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


        for (int i = 0; i < getTailleGrille(); i++) {
            if (isRowFull(i))
                if (!checkRowUnicite(i)) {
                    return false;
            }
            if (isColumnFull(i))
                if (!checkColumnUnicite(i)) {
                    return false;
                }
        }
        return true;
    }


    /**
     *
     * @return vrai si le takuzu est valide et complet
     */
    public boolean estGagnant() {
        return estTotalementRemplit() && estValide();
    }


    /**
     * Affiche une sortie console de this
     */
    public void affichage() {
        System.out.println("Takuzu:\n");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String res = "";
        for (int x = 0; x < TAILLE; x++) {
            for (int y = 0; y < TAILLE; y++) {
                if (grille[x][y]>=0) {
                    res = res.concat(String.valueOf(grille[x][y])).concat(" ");
                }else{
                    res = res.concat("* ");
                }
            }
            res = res.concat("\n");
        }
        return "Grille{\n" + res + "}\n";
    }

    /**
     * Crée une deep copie de this.
     * @return un Takuzu
     */
    public Takuzu cloneTakuzu() {
        Takuzu TakuzuBis = new Takuzu(getTailleGrille());
        for (int i = 0; i < this.getTailleGrille(); i++) {
            for (int j = 0; j < this.getTailleGrille(); j++) {
                TakuzuBis.setValue(i, j, this.getValue(i, j));
            }
        }
        return TakuzuBis;
    }


    /**
     * @param ligne
     * @param colonne
     * @param inverse
     * Sert à jouer l'inverse du nombre, est utilisé dans certaines fonctions
     */
    public void playInverse(int ligne, int colonne, int inverse)
    {
        if (inverse == 1)
            setValue(ligne, colonne, 0);
        else if (inverse == 0)
            setValue(ligne, colonne, 1);
        else
            System.err.println(inverse + "pas bonne valeur");
    }

    /**
     * @param y La ligne que l'on va tester
     * @return vrai si la ligne ne contient pas de case vide (-1)
     */

    public boolean isRowFull(int y){
        for(int i = 0; i < TAILLE; i++)
        {
            if (grille[y][i] == -1)
                return false;
        }
        return true;
    }

    /**
     *
     * @param x la colonne que l'on va tester
     * @return vrai si la colonne ne contient pas de case vide (-1)
     */

    public boolean isColumnFull(int x)
    {
        for (int i = 0; i < TAILLE; i++)
        {
            if (grille[i][x] == -1)
                return false;
        }
        return true;
    }


    /**
     *
     * @return false si la grille contient des -1
     */
    public boolean estTotalementRemplit() {
            for (int i = 0; i < TAILLE; i++) {
                for (int j = 0; j < TAILLE; j++) {
                    if (grille[i][j] == -1) return false;
                }
            }

            return true;
        }


    /**
     *
     * @param numLigne la ligne qui va être remplie
     * @param val la valeur avec laquelle on va remplir la ligne
     * @return vrai si la ligne a été modifié, faux sinon
     */
    @SuppressWarnings("Duplicates")
    public boolean remplirLigneDe(int numLigne, int val) {
        boolean didSomething = false;
        for (int i = 0; i < TAILLE; i++) {
            if (grille[numLigne][i] == -1) {
                setValue(numLigne, i, val);
                didSomething = true;
            }
        }
        return didSomething;
    }

    @SuppressWarnings("Duplicates")
    public boolean remplirColonneDe(int numColonne, int val) {
        boolean didSomething = false;
        for (int i = 0; i < TAILLE; i++) {
            if (grille[i][numColonne] == -1) {
                setValue(i, numColonne, val);
                didSomething = true;
            }
        }
        return didSomething;
    }

    public boolean seResoudre(Solveur unSolveur) { return unSolveur.resoudre(this); }

    public static Takuzu genererUnTakuzu(Generateur unGenerateur) {
        return unGenerateur.generer();
    }

    /**
     *
     * @return la position de la première case vide trouvée, sinon retourne null dans le où il y'en a pas
     */

    public int[] trouver1erCaseVide() {
        for (int ord = 0; ord < TAILLE; ord++) {
            for (int abs = 0; abs < TAILLE; abs++) {

                if (this.getValue(ord, abs) == -1) {
                    return new int[]{ord, abs};
                }
            }
        }
        return null;
    }


    public int trouverMeilleurCaseColonne() {
        ArrayList<Integer> liste = new ArrayList();

        int i = 0, plusPetit, plusPetiteColonne = 0;

        //On compte le nombre de case vide pour chaque ligne
        for (int ord = 0; ord < TAILLE; ord++) {
            for (int abs = 0; abs < TAILLE; abs++) {
                if (this.getValue(abs, ord) == -1) {
                    i++;
                }
            }
            //On les ajoute
            liste.add(i);

            //Si on a i == 1 alors on sait qu'on doit jouer sur cette ligne
            //Donc ca ne sert à rien de continuer à chercher
            if (i == 1)
                break;

            //On réinitialise à zéro la valeur de i
            i = 0;
        }

        //On remplit la valeur plusPetit avec la toute première de la liste
        plusPetit = liste.get(0);

        //On cherche la plus petite valeur parmi la liste
        while (!liste.isEmpty()){

            //S'il s'avère que la première ligne a 0 emplacement dispo, alors on prend la suivante
            if (plusPetit == 0){
                plusPetit = liste.get(0);
                plusPetiteColonne = i;
            }

            if (plusPetit > liste.get(0) && liste.get(0) != 0) {
                plusPetiteColonne = i;
            }
            liste.remove(0);

            i++;
        }
        return plusPetiteColonne;
    }

    public int trouverMeilleurCaseLigne() {
        ArrayList<Integer> liste = new ArrayList();

        int i = 0, plusPetit, plusPetiteLigne = 0;

        for (int ord = 0; ord < TAILLE; ord++) {
            for (int abs = 0; abs < TAILLE; abs++) {
                if (this.getValue(ord, abs) == -1) {
                    i++;
                }
            }
            liste.add(i);


            if (i == 1)
                break;

            i = 0;
        }
        plusPetit = liste.get(0);

        while (!liste.isEmpty()){

            if (plusPetit == 0){
                plusPetit = liste.get(0);
                plusPetiteLigne = i;
            }

            if (plusPetit > liste.get(0) && liste.get(0) != 0) {
                plusPetiteLigne = i;
            }
            liste.remove(0);

            i++;
        }

        return plusPetiteLigne;
    }

    public int [] trouverMeilleurCase() {
        //int ligne = trouverMeilleurCaseLigne();
        int colonne = trouverMeilleurCaseColonne();
/*

        if (ligne > colonne) {
            for (int i = 0; i < TAILLE; i++) {

                if (grille[ligne][i] == -1) {
                    return new int[]{ligne, i};
                }
            }
        } else {

 */
        for (int i = 0; i < TAILLE; i++) {


                if (grille[i][colonne] == -1) {
                    return new int[]{i, colonne};
                }
            }



        return null;
    }


    /**
     *
     * @param takuzuRempli le takuzu déjà rempli que l'on va utiliser pour prendre les valeurs
     * @return faux si le takuzuRempli diffère de this dans les cases remplis (c'est à dire autre que -1), sinon retourne vrai
     */
    public boolean remplirLaDifference(Takuzu takuzuRempli) {

            //Verification de la taille des deux takuzus
            if (takuzuRempli.getTailleGrille() != this.TAILLE || takuzuRempli.getTailleGrille() != this.TAILLE) {
                return false;//throw new RuntimeException("Pas la même grille");
            }

            //Retourne faux si une valeur déjà rempli diffère de "takuzuRempli"
            for (int i = 0; i < TAILLE; i++) {
                for (int j = 0; j < TAILLE; j++) {
                    if (this.getValue(i, j) != -1) {
                        if (takuzuRempli.getValue(i, j) != this.getValue(i, j))
                            return false;
                    }
                }
            }

            //Recopie takuzuRempli dans this
            for (int i = 0; i < TAILLE; i++) {
                for (int j = 0; j < TAILLE; j++) {
                    this.setValue(i,j,takuzuRempli.getValue(i,j));
                }
            }
            return true;
        }

    public void fill(int value){
        for (int[] ligne : grille) {
            Arrays.fill(ligne, value);
        }
    }

    public void setValue(int hauteur, int largeur, int value) {
        this.grille[hauteur][largeur] = value;
    }

    /**
     *
     * @param ligne
     * @param colonne
     * @return -1 si la ligne / colonne dépasse le tableau, sinon retourne la valeur se situant à ladite case
     */
    public int getValue(int ligne, int colonne) {
        if (ligne < 0 || ligne >= getTailleGrille() || colonne < 0 || colonne >= getTailleGrille()) {
            return -1;
        }
        return this.grille[ligne][colonne];
    }

    /**
     *
     * @param i l'index d'une ligne de la grille
     * @param ii l'index d'une ligne de la grille
     * @return true ssi la ligne n°i et n°ii sont identiques
     */
    public boolean equals2Row(int i, int ii)
    {
        return java.util.Arrays.equals(grille[i], grille[ii]);
    }

    public boolean equals2Column(int i, int ii)
    {
        ArrayList colonne1 = new ArrayList();
        ArrayList colonne2 = new ArrayList();

        for (int[] ligne : grille){
            colonne1.add(ligne[i]);
        }
        for (int[] ligne : grille){
            colonne2.add(ligne[ii]);
        }
        return colonne1.equals(colonne2);
    }


    /**
     * Des fonctions pour remplir des takuzus
     */
    public void preRemplissage() {
        //1er ligne
        //play0(0,0);
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


    public void preRemplissage3() {
        //1er ligne
        play0(0,1);
        play0(0,5);

        //2e ligne
        play0(1,4);

        //3e ligne
        play1(2,0);
        play1(2,3);

        //4e ligne
        play0(3,5);

        //5e ligne
        play1(4,3);
        play1(4,4);

        //6e ligne
        play1(5,1);
        play1(5,2);
    }

    public void preRemplissage4() {

        play0(0, 1);
        play1(1, 2);
        play0(2, 4);
        play0(2, 7);
        play0(4, 3);
        play1(4, 6);
        play0(5, 1);
        play0(5, 3);
        play1(6, 2);
        play1(6, 6);
        play0(7, 4);
        play1(7, 6);
        play1(7, 7);
    }

    public void preRemplissage5() {
        play0(0, 0);
        play0(0, 1);
        play0(0, 4);
        play1(0, 6);
    }

    public void preRemplissagePourBenchMarkEntreNous() {
        //1er ligne
        play1(0,4);
        play1(0,6);
        play0(0,11);
        play0(0,13);

        //2e ligne
        play0(1,8);
        play0(1,9);

        //3e ligne
        play1(2,1);
        play1(2,11);
        play0(2,4);

        //4e ligne
        play1(3,5);
        play1(3,8);
        play0(3,13);

        //5e ligne
        play0(4,0);
        play0(4,13);

        //6e ligne
        play1(5,10);
        play1(5,12);

        //7e ligne
        play0(6,2);
        play1(6,5);
        play1(6,6);

        //8e ligne
        play0(7,2);
        play0(7,10);
        play1(7,13);

        //9e ligne
        play0(8,5);
        play0(8,7);
        play0(8,8);
        play0(8,13);
        play1(8,11);

        //10e ligne
        play1(9,1);
        play1(9,2);
        play1(9,5);
        play1(9,6);
        play0(9,8);

        //11e ligne

        //12e ligne
        play0(11,0);
        play0(11,1);
        play0(11,12);
        play1(11,4);

        //13e ligne
        play0(12,5);
        play0(12,7);
        play0(12,9);
        play0(12,11);
        play1(12,13);

        //14e ligne
        play1(13,0);
        play1(13,9);
        play1(13,10);
    }

}
