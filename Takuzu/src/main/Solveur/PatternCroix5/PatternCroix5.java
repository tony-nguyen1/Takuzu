package main.Solveur.PatternCroix5;

import main.Solveur.Solveur;
import main.Takuzu;

public class PatternCroix5 implements Solveur {
    @Override
    public boolean resoudre(Takuzu takuzu) {
        int h1, h2, b1, b2, g1, g2, d1, d2, height, width;
        //ces variables contiennent les valeurs des cases proches de 1 case

        height = takuzu.getHeightGrille();
        width = takuzu.getWidthGrille();

        //on regarde les cases 1 par 1
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (takuzu.getValue(j,i) == -1) {
                    // si les indices fait que la croix dépasse du tableau, on simule des cases vides
                    //sinon on met la bonne valeur
                    if (i == 0) {
                        h1 = -1;
                        h2 = -1;
                    } else if (i == 1) {
                        h1 = takuzu.getValue(j, i-1);
                        h2 = -1;
                    } else {
                        h1 = takuzu.getValue(j, i-1);
                        h2 = takuzu.getValue(j, i-2);
                    }

                    if (i == height -1)
                    {
                        b1 = -1;
                        b2 = -1;
                    }
                    else if (i == height -1 -1) {
                        b1 = takuzu.getValue(j, i + 1);
                        b2 = -1;
                    }
                    else {
                        b1 = takuzu.getValue(j, i+1);
                        b2 = takuzu.getValue(j,i+2);
                    }


                    if (j == 0) {
                        g1 = -1;
                        g2 = -1;
                    }
                    else if (j == 1) {
                        g1 = takuzu.getValue(j - 1, i);
                        g2 = -1;
                    }
                    else {
                        g1 = takuzu.getValue(j-1,i);
                        g2 = takuzu.getValue(j-2,i);
                    }

                    if (j == width -1) {
                        d1 = -1;
                        d2 = -1;
                    }
                    else if (j == width -1 -1) {
                        d1 = takuzu.getValue(j + 1, i);
                        d2 = -1;
                    }
                    else {
                        d1 = takuzu.getValue(j + 1, i);
                        d2 = takuzu.getValue(j + 2, i);
                    }

                    croix5(h1,h2,b1,b2,g1,g2,d1,d2,j,i,takuzu);
                }
            }
        }
        //Comme tout à l'heure, j'ai changé pour un null (Boolean => Takuzu)
        return false;
    }

    /**
     *
     * Regarde autour de la case x y dans les 4 sens avec une distance de 2 cases et joue une valeur sûr à 100% si il peut
     *
     * @pré-requis la case centrale est toujours vide
     *
     */
    private void croix5(int haut1, int haut2, int bas1, int bas2, int gauche1, int gauche2, int droite1, int droite2, int x, int y, Takuzu takuzu)
    {
        croix5Aux(haut1,haut2,x,y,takuzu);
        croix5Aux(bas1,bas2,x,y,takuzu);
        croix5Aux(gauche1,gauche2,x,y,takuzu);
        croix5Aux(droite1,droite2,x,y,takuzu);
    }

    /**
     *
     * Regarde si 2 cases consécutives ont la même valeur, si c'est le cas, joue la bonne valeur
     *
     * @param valInterieur la valeur directement adjacentes à la case x y
     * @param valExterieur la valeur de la case juste après
     * @param x indice de la case
     * @param y indice de la case
     */
    private void croix5Aux(int valInterieur, int valExterieur, int x, int y, Takuzu takuzu)
    {
        if (valInterieur == valExterieur & valInterieur != -1 & valExterieur != -1) {
            takuzu.playInverse(x,y,valInterieur);
        }
    }
}
