package main.Solveur.PatternCroix3;

import main.Solveur.Solveur;
import main.Takuzu;

public class PatternCroix3 implements Solveur {
    private boolean didSomething;

    public PatternCroix3() {
        didSomething = false;
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        int h, b, g, d, height, width;

        height = takuzu.getHeightGrille();
        width = takuzu.getWidthGrille();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (takuzu.getValue(j,i) == -1)
                {
                    if (i==0)
                        h = -1;
                    else
                        h = takuzu.getValue(j,i-1);

                    if (i==height-1)
                        b = -1;
                    else
                        b = takuzu.getValue(j,i+1);


                    if (j==0)
                        g = -1;
                    else
                        g = takuzu.getValue(j-1,i);

                    if (j==width-1)
                        d = -1;
                    else
                        d = takuzu.getValue(j+1,i);

                    croix3(h,b,g,d,j,i,takuzu);
                }
            }
        }
        if (didSomething)
        {
            didSomething = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @pré-requis la case centrale est toujours vide
     *
     */
    private void croix3(int haut, int bas, int gauche, int droite, int x, int y, Takuzu takuzu)
    {
        if (haut == bas & haut != -1 & bas != -1)
        {
            takuzu.playInverse(x,y,haut);
            didSomething = true;

        }
        if (gauche == droite & gauche != -1 & droite != -1) {
            takuzu.playInverse(x,y,gauche);
            didSomething = true;
        }
    }
}
