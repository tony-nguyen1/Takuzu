package main.Solveur.PatternCroix5;

import main.Grille;
import main.Solveur.Solveur;
import main.Takuzu;

public class PatternCroix5 implements Solveur {

    private Takuzu takuzu;

    public PatternCroix5(Takuzu takuzu) {
        this.takuzu = takuzu;
    }

    @Override
    public boolean resoudre() {
        Grille grille = takuzu.getGrille();
        int h1, h2, b1, b2, g1, g2, d1, d2, height, width;

        height = grille.getHEIGHT();
        width = grille.getWIDTH();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (grille.getValue(j,i) == -1) {
                    if (i == 0) {
                        h1 = -1;
                        h2 = -1;
                    } else if (i == 1) {
                        h1 = grille.getValue(j, i-1);
                        h2 = -1;
                    } else {
                        h1 = grille.getValue(j, i-1);
                        h2 = grille.getValue(j, i-2);
                    }

                    if (i == height -1)
                    {
                        b1 = -1;
                        b2 = -1;
                    }
                    else if (i == height -1 -1) {
                        b1 = grille.getValue(j, i + 1);
                        b2 = -1;
                    }
                    else {
                        b1 = grille.getValue(j, i+1);
                        b2 = grille.getValue(j,i+2);
                    }


                    if (j == 0) {
                        g1 = -1;
                        g2 = -1;
                    }
                    else if (j == 1) {
                        g1 = grille.getValue(j - 1, i);
                        g2 = -1;
                    }
                    else {
                        g1 = grille.getValue(j-1,i);
                        g2 = grille.getValue(j-2,i);
                    }

                    if (j == width -1) {
                        d1 = -1;
                        d2 = -1;
                    }
                    else if (j == width -1 -1) {
                        d1 = grille.getValue(j + 1, i);
                        d2 = -1;
                    }
                    else {
                        d1 = grille.getValue(j + 1, i);
                        d2 = grille.getValue(j + 2, i);
                    }

                    croix5(h1,h2,b1,b2,g1,g2,d1,d2,j,i);
                }
            }
        }
        return true;
    }

    /**
     * @pré-requis la case centrale est toujours vide
     *
     */
    private void croix5(int haut1, int haut2, int bas1, int bas2, int gauche1, int gauche2, int droite1, int droite2, int x, int y)
    {
        croix5Aux(haut1,haut2,x,y);
        croix5Aux(bas1,bas2,x,y);
        croix5Aux(gauche1,gauche2,x,y);
        croix5Aux(droite1,droite2,x,y);
    }

    private void croix5Aux(int valInterieur, int valExterieur, int x, int y)
    {
        if (valInterieur == valExterieur & valInterieur != -1 & valExterieur != -1) {
            playInverse(x,y,valInterieur);
        }
    }

    private void playInverse(int x, int y, int cmp)
    {
        if (cmp == 1)
            takuzu.play0(x, y);
        else if (cmp == 0)
            takuzu.play1(x,y);
    }
}
