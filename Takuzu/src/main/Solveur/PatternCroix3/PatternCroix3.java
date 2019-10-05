package main.Solveur.PatternCroix3;

import main.Grille;
import main.Solveur.Solveur;
import main.Takuzu;

public class PatternCroix3 implements Solveur {

    private Takuzu takuzu;

    public PatternCroix3(Takuzu takuzu) {
        this.takuzu = takuzu;
    }

    @Override
    public boolean resoudre() {
        Grille grille = takuzu.getGrille();
        int h, b, g, d, height, width;

        height = grille.getHEIGHT();
        width = grille.getWIDTH();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (grille.getValue(j,i) == -1)
                {
                    //h = 0; b = 0; g = 0; d = 0;
                    if (i==0)
                        h = -1;
                    else
                        h = grille.getValue(j,i-1);

                    if (i==height-1)
                        b = -1;
                    else
                        b = grille.getValue(j,i+1);


                    if (j==0)
                        g = -1;
                    else
                        g = grille.getValue(j-1,i);

                    if (j==width-1)
                        d = -1;
                    else
                        d = grille.getValue(j+1,i);

                    croix3(h,b,g,d,j,i);
                }
            }
        }
        //System.out.println("compteur = " + cpt);


        /*
        cpt = 0;
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (grille.getValue(j,i) == -1)
                {
                    cpt++;
                }
            }
        }
        System.out.println("compteur = " + cpt);
        */
        return true;
    }

    /**
     * @pré-requis la case centrale est toujours vide
     *
     */
    private void croix3(int haut, int bas, int gauche, int droite, int x, int y)
    {
        if (haut == bas & haut != -1 & bas != -1)
        {
            takuzu.play(x, y, inverse(haut));
        }
        if (gauche == droite & gauche != -1 & droite != -1) {
            takuzu.play(x, y, inverse(gauche));
        }
    }

    private int inverse(int i)
    {
        if (i == 1)
            return 0;
        else if (i == 0)
            return 1;
        return 99;
    }
}
