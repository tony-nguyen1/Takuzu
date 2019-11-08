package main.Solveur.PatternCroix;

import main.Solveur.Solveur;
import main.Takuzu;
@SuppressWarnings("Duplicates")
public class PatternCroix implements Solveur {
    private boolean didSomething;

    public PatternCroix() {
        this.didSomething = false;
    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        int h1, h2, b1, b2, g1, g2, d1, d2, height, width;
        boolean grdCroixATravailler, petiteCroixATravailler;
        //ces variables contiennent les valeurs des cases proches de 1 case

        height = takuzu.getHeightGrille();
        width = takuzu.getWidthGrille();

        //on regarde les cases 1 par 1
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (takuzu.getValue(j,i) == -1) {
                    //on récupère les valeurs des cases autours
                    h1 = takuzu.getValue(j, i-1);
                    h2 = takuzu.getValue(j, i-2);

                    b1 = takuzu.getValue(j, i+1);
                    b2 = takuzu.getValue(j,i+2);

                    g1 = takuzu.getValue(j-1,i);
                    g2 = takuzu.getValue(j-2,i);

                    d1 = takuzu.getValue(j + 1, i);
                    d2 = takuzu.getValue(j + 2, i);

                    grdCroixATravailler = croix5(h1,h2,b1,b2,g1,g2,d1,d2,j,i,takuzu);
                    //si ce pattern n'a rien fait, alors on prit pour que l'autre fait qql chose
                    if (!grdCroixATravailler) {
                        //petiteCroixATravailler =
                        croix3(h1,b1,g1,d1,j,i,takuzu);
                    }
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
     * Regarde autour de la case aux coordonnées (x,y) dans les 4 sens avec une distance de 2 case et joue une valeur, sûr à 100%, uniquement si il le peut.
     *
     * @pré-requis la case centrale est toujours vide
     *
     * @return true ssi il a fait une modification, false sinon
     */
    private boolean croix5(int haut1, int haut2, int bas1, int bas2, int gauche1, int gauche2, int droite1, int droite2, int x, int y, Takuzu takuzu)
    {
        boolean worked1, worked2,worked3,worked4;

        worked1 = croixAux(haut1,haut2,x,y,takuzu);
        if (!worked1) {

            worked2 = croixAux(bas1, bas2, x, y, takuzu);
            if (!worked2) {

                worked3 = croixAux(gauche1, gauche2, x, y, takuzu);
                if (!worked3) {

                    worked4 = croixAux(droite1, droite2, x, y, takuzu);
                    return worked4;
                }
            }
        }
        return true;
    }


    /**
     * Regarde autour de la case aux coordonnées (x,y) dans les 4 sens avec une distance de 1 case et joue une valeur, sûr à 100%, uniquement si il le peut.
     *
     * @pré-requis la case centrale est toujours vide, mais on ne le vérifie pas ici
     *
     * @return true ssi il a fait une modification, false sinon
     */
    private boolean croix3(int haut, int bas, int gauche, int droite, int x, int y, Takuzu takuzu)
    {
        boolean worked1, worked2;

        worked1 = croixAux(haut,bas,x,y,takuzu);
        if (!worked1) {

            worked2 = croixAux(gauche,droite,x,y,takuzu);
            return worked2;
        }
        return true;
    }

    /**
     * Compare 2 valeur et joue la valeur inverse si elles sont identiques.
     *
     * @param valA une valeur d'une case
     * @param valB une valeur d'une case
     * @param x indice de la case
     * @param y indice de la case
     */
    private boolean croixAux(int valA, int valB, int x, int y, Takuzu takuzu)
    {
        if (valA == valB & valA != -1 & valB != -1) {
            takuzu.playInverse(x,y,valA);
            didSomething = true;
            return true;
        }
        return false;
    }
}
