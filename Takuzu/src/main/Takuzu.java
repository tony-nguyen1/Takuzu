package main;

import java.util.ArrayList;

public class Takuzu {
    private Grille grille;

    public Takuzu(int i) { grille = new Grille(i,i); }

    private void play(int x, int y, int value)
    {
        grille.setValue(x, y, value);
    }

    public boolean checkColumn(int x){
        throw new RuntimeException("Not implemented yet !");
    }

    /**
     * @pré-requis la ligne x est déjà remplis
     * Compare la ligne x avec les autres lignes remplit
     *
     * @param x the ligne that we compare to others ligne
     * @return true si la ligne a un nombre de 0 et 1 égal, faux sinon
     */
    public boolean checkRowBalance(int x){
        int nb0, nb1;
        nb0 = 0;
        nb1 = 0;

        for (int y = 0; y < grille.getWIDTH(); y++)
        {
            System.out.println("Salut" + y);
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
     * @pré-requis la ligne x est déjà remplis
     * Compare la ligne x avec les autres lignes remplit
     *
     * @param x the ligne that we compare to others ligne
     * @return true si la ligne est unique, faux sinon
     */
    public boolean checkRowUnicite(int x) {
        ArrayList<Integer> numList = this.grille.rowFilled();

        //la comparaison
        if (!numList.isEmpty())
        {
            numList.remove(new Integer(x));//on remove x pour pas qu'une ligne se compare avec elle-même
            for (Integer integer : numList)
            {
                if (!this.grille.equals2Row(x, integer))
                    return false;
            }
        }
        return true;
    }

    public boolean checkAllRowAll()
    {


        return true;
    }

    public void affichage() { 
        System.out.println("Takuzu:\n");
        grille.affichage();
    }

    public void affichageGraphique()
    {
        grille.affichageGraphique();
    }

    public void play0(int x, int y) { grille.setValue(x, y, 0); }
    public void play1(int x, int y) { grille.setValue(x, y, 1); }

    /**
     * this doit avoir une grille vide.
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
