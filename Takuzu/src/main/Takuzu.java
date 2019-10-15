package main;

import java.util.ArrayList;

public class Takuzu {
    private Grille grille;

    public Takuzu(int i) {
        grille = new Grille(i, i);
        /*
        try {
            grille = new Grille(i, i);
        }
        catch (OddDimensionsGrilleException e)
        {
            System.out.println("Grille creation has failed.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

         */
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
    public int getValue(int x, int y) {
        if (x < 0 || x >= getHeightGrille() || y < 0 || y >= getWidthGrille()) {
            return -1;
        }
        return grille.getValue(x, y);
    }

    public int getHeightGrille() { return grille.getHEIGHT(); }

    public int getWidthGrille() { return grille.getWIDTH(); }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //                            ICI les règles du jeu

    //FIXME
    // 1.changer la strat, systeme de compteur dans Grille qui incrémente une variable
    // 2.retirer la duplication de code avec checkRowBalance(int y)
    /**
     * @pré-requis la ligne x est déjà remplis
     * Compare la ligne x avec les autres lignes remplit
     *
     * @param y the ligne that we compare to others ligne
     * @return true si la ligne a un nombre de 0 et 1 égal, faux sinon
     */
    public boolean checkRowBalance(int y){
        int nb0, nb1;
        nb0 = 0;
        nb1 = 0;

        for (int x = 0; x < grille.getWIDTH(); x++)
        {
            switch (grille.getValue(x,y))
            {
                case 0:
                    nb0++;
                    break;
                case 1:
                    nb1++;
                    break;
                case -1:
                    break;
                default:
                    throw new RuntimeException("Valeur non autorisé trouvé");
            }
        }
        return nb0 == nb1;
    }

    /**
     * @pré-requis la ligne y est déjà remplis
     * Compare la ligne y avec les autres lignes remplit
     *
     * @param y the ligne that we compare to others ligne
     * @return true si la ligne est unique, faux sinon
     */
    public boolean checkRowUnicite(int y, ArrayList<Integer> numList) {
        //copie pour pouvoir la modifier sans modifier numList
        ArrayList<Integer> numListCopy = new ArrayList<>(numList);

        //si y n'est pas la seul ligne remplit
        if (!numListCopy.isEmpty())
        {
            numListCopy.remove(new Integer(y));//on remove y pour pas qu'une ligne se compare avec elle-même
            //comparaison entre ligne d'index y et les autres lignes remplit
            for (Integer integer : numListCopy)
            {
                if (this.grille.equals2Row(y, integer))
                    return false;
            }
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
     * @pré-requis ligne d'index y remplis entièrement
     *
     * @param y la ligne qu'on compare aux autres lignes
     * @return true ssi il n'y a que 2 chiffres identiques consécutifs
     */
    public boolean checkRowCoupleOnly(int y)
    {
        boolean hasNext = true;
        int  previousValue, currentValue, nextValue, indiceX = 1;

        do {
            previousValue = grille.getValue(indiceX-1, y);
            currentValue = grille.getValue(indiceX, y);
            nextValue = grille.getValue(indiceX+1, y);

            //3 valeurs à la suite identique
            if (previousValue == currentValue & currentValue == nextValue) { return false; }

            indiceX++;

            if (indiceX == grille.getWIDTH()-2)  { hasNext = false; }//-1 car on commence à 0 et -1 pour finir juste avant
        } while (hasNext);

        return true;
    }

    /**
     * Regarde toute les colonnes. Parmi celles qui sont remplit, dit c'est elles respectent les 3 règles.
     * @return true, si les colonnes remplit respectent toutes les règles
     */
    public boolean checkAllRowAll()
    {
        ArrayList<Integer> numLigneRemplit = grille.rowFilled();
        boolean balance, unicite, onlyCouple;

        for (Integer num : numLigneRemplit)
        {
            balance = checkRowBalance(num);
            if (!balance) return false;

            unicite = checkRowUnicite(num, numLigneRemplit);
            if (!unicite) return false;
            //numLigneRemplit.remove(new Integer(num)); java.util.ConcurrentModificationException

            onlyCouple = checkRowCoupleOnly(num);
            if (!onlyCouple) return false;
        }

        return true;
    }

    public boolean checkAllColumnAll() {
        ArrayList<Integer> numColumnRemplit = grille.columnFilled();
        boolean balance, unicite, onlyCouple;

        for (Integer num : numColumnRemplit) {
            balance = checkColumnBalance(num);
            if (!balance) return false;

            unicite = checkColumnUnicite(num, numColumnRemplit);
            if (!unicite) return false;

            onlyCouple = checkColumnCoupleOnly(num);
            if (!onlyCouple) return false;
        }

        return true;
    }

    //FIXME
    // 1.changer la strat, systeme de compteur dans Grille qui incrémente une variable
    // 2.retirer la duplication de code avec checkRowBalance(int y)
    /**
     * @pré-requis la colonne x est déjà remplis
     * Compare la colonne x avec les autres colonne remplit
     *
     * @param x the column that we compare to others column
     * @return true si la colonne est unique, faux sinon
     */
    public boolean checkColumnBalance(int x){
        int nb0, nb1;
        nb0 = 0;
        nb1 = 0;

        for (int y = 0; y < grille.getWIDTH(); y++)
        {
            switch (grille.getValue(x,y))
            {
                case 0:
                    nb0++;
                    break;
                case 1:
                    nb1++;
                    break;
                case -1:
                    break;
                default:
                    throw new RuntimeException("Valeur non autorisé trouvé");
            }
        }
        return nb0 == nb1;
    }

    public boolean checkColumnUnicite(int x, ArrayList<Integer> numList) {
        //copie pour pouvoir la modifier sans modifier numList
        ArrayList<Integer> numListCopy = new ArrayList<>(numList);

        //si y n'est pas la seul colonne remplit
        if (!numListCopy.isEmpty())
        {
            numListCopy.remove(new Integer(x));//on remove x pour pas qu'une colonne se compare avec elle-même
            //comparaison entre colonne d'index x et les autres colonnes remplit
            for (Integer integer : numListCopy)
            {
                if (this.grille.equals2Column(x, integer))
                    return false;
            }
        }
        return true;
    }

    public boolean checkColumnCoupleOnly(int x)
    {
        boolean hasNext = true;
        int  previousValue, currentValue, nextValue, indiceY = 1;

        do {
            previousValue = grille.getValue(x, indiceY-1);
            currentValue = grille.getValue(x, indiceY);
            nextValue = grille.getValue(x, indiceY+1);

            //3 valeurs à la suite identique
            if (previousValue == currentValue & currentValue == nextValue) { return false; }

            indiceY++;

            if (indiceY == grille.getHEIGHT()-2)  { hasNext = false; }//-1 car on commence à 0 et -1 pour finir juste avant
        } while (hasNext);

        return true;
    }

    //Verifie si le Takuzu est valide ou non

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


        return true;
    }


    public boolean estGagnant() {
        return checkAllRowAll() && checkAllColumnAll() && grille.columnFilled().size()==grille.getWIDTH() && grille.rowFilled().size()==grille.getHEIGHT();
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
}
