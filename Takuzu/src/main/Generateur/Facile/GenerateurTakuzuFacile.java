package main.Generateur.Facile;

import main.Generateur.Generateur;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class GenerateurTakuzuFacile implements Generateur {

    private int abs;
    private int ord;
    private int nbCaseRandomASupprimer;
    private Random rand;
    private Solveur lesSolveursSimples;
    private int hauteur;
    private int largeur;
    private Takuzu takuzuResolut;

    public GenerateurTakuzuFacile(int nbCaseASupprimerAleatoirement, Takuzu takuzuGagnant) {
        this.abs = 0;
        this.ord = 0;
        this.nbCaseRandomASupprimer = nbCaseASupprimerAleatoirement;
        this.rand = new Random();
        this.lesSolveursSimples = new MaitreSolveur();
        this.takuzuResolut = takuzuGagnant;
        this.hauteur = takuzuGagnant.getHeightGrille();
        this.largeur = takuzuGagnant.getWidthGrille();
    }

    @Override
    public Takuzu generer() {
        //déclaration
        int valeurEnlever, nbCaseSupprimeeAleatoirement, cpt;
        boolean assezEnleverDeCaseAleatoirement;
        Takuzu /*takuzuResolut,*/ foo;

        //initialisation des objets
        //takuzuResolut = Takuzu.getPreRemplissageAnswer2();

        //initialisation des types primitifs
        this.abs = 0; this.ord = 0;
        valeurEnlever = 99999999;
        nbCaseSupprimeeAleatoirement = 0;
        cpt = 0;

        while((nbCaseSupprimeeAleatoirement < nbCaseRandomASupprimer )) {

            //On enlève une case au hasard
            valeurEnlever = enleverUneCaseRandom(takuzuResolut, hauteur, largeur);
            nbCaseSupprimeeAleatoirement++;


            //Puis on essaye de résoudre SANS faire d'hypothèses
            foo = takuzuResolut.cloneTakuzu();
            lesSolveursSimples.resoudre(foo);

            if (!foo.estGagnant()) {
                //les solveurs simples n'arrivent pas à résoudre
                //la valeur qu'on a enlever entraine des hypothèses, elle est trop importante
                //donc on doit la remettre

                nbCaseSupprimeeAleatoirement--;
                if (valeurEnlever == 0)
                    takuzuResolut.play0(ord, abs);
                else
                    takuzuResolut.play1(ord, abs);

            }
            cpt++;
        }
        System.out.println("nbCaseSupprimeeAleatoirement = " + nbCaseSupprimeeAleatoirement);
        System.out.println("Tour de boucle : " + cpt);
        takuzuResolut.affichage();
        return enleverToutesLesCasesInutiles(takuzuResolut);
    }

    private Takuzu enleverToutesLesCasesInutiles(Takuzu unTakuzu) {
        System.out.println("Entrer");
        boolean pause, sarreter;
        int valeurEnlever, i, j, cpt, nbCaseInutileTotal, nbCaseEnleverCeTour;
        Takuzu foo;

        this.abs = 0;
        this.ord = 0;
        i = 0;
        j = 0;
        nbCaseInutileTotal = 0;
        nbCaseEnleverCeTour = -1;
        pause = false;
        valeurEnlever = 99999999;
        sarreter = false;

        while(nbCaseEnleverCeTour != 0) {
            nbCaseEnleverCeTour = 0;
            System.out.println("Début");
            System.out.println(sarreter);
            while (!sarreter) {
                System.out.println("Début à ord abs " + ord + " " + abs);
                System.out.println("Début à i j  " + ord + " " + abs);
                cpt = 0;
                for (i = ord; i < hauteur && !pause; i++) {
                    for (j = cpt == 0 ? abs : 0; j < largeur && !pause; j++) {

                        System.out.println(i + " " + j);
                        if (unTakuzu.getValue(i, j) != -1) {
                            System.out.println("Valeur Trouvée");
                            valeurEnlever = unTakuzu.suppr(i, j);
                            pause = true;
                        }
                    }
                    cpt++;
                }
                pause = false;
                i--;
                j--;


                //Puis on essaye de résoudre SANS faire d'hypothèses

                if (!verifierSiPeutResoudreLogiquement(unTakuzu)) {
                    //les solveurs simples n'arrivent pas à résoudre
                    //la valeur qu'on a enlever entraine des hypothèses, elle est trop importante
                    //donc on doit la remettre

                    System.out.println("Valeur importante -> " + i + " " + j);
                    if (valeurEnlever == 0)
                        unTakuzu.play0(i, j);
                    else
                        unTakuzu.play1(i, j);
                } else {
                    System.out.println("Valeur pas importante -> " + i + " " + j);
                    nbCaseInutileTotal++;
                    nbCaseEnleverCeTour++;
                }

                if (i == hauteur - 1 && j == largeur - 1) {
                    sarreter = true;
                    System.out.println("Arrêt");
                } else {
                    if (j == unTakuzu.getWidthGrille()-1) {
                        ord = i + 1;
                        abs = 0;
                    } else {
                        ord = i;
                        abs = j + 1;
                    }

                    System.out.println("Fin à ord abs " + ord + " " + abs);
                    System.out.println("Fin à i j  " + ord + " " + abs);
                }
            }
            System.out.println("nbCaseEnleverCeTour = " + nbCaseEnleverCeTour);
            if (nbCaseEnleverCeTour > 1)
            {
                sarreter = false;
                i = 0; j = 0;
                abs = 0; ord = 0;
            }
            else {
                sarreter = true;
            }
        }
        System.out.println("nbCaseInutileTotal = " + nbCaseInutileTotal);
        return unTakuzu;
    }

    private boolean verifierSiPeutResoudreLogiquement(Takuzu unTakuzu) {
        Takuzu foo = unTakuzu.cloneTakuzu();
        foo.seResoudre(lesSolveursSimples);

        return foo.estGagnant();
    }

    private int enleverUneCaseRandom(Takuzu unTakuzu, int hauteur, int largeur) {
        int valeurEnlever;
        do {
            abs = rand.nextInt(largeur);
            ord = rand.nextInt(hauteur);
            valeurEnlever = unTakuzu.suppr(ord, abs);
        } while (valeurEnlever == -1);//mais on supprime une case remplit, pas une case vide

        return valeurEnlever;
    }
}
