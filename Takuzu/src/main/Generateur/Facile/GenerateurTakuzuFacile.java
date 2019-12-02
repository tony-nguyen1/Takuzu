package main.Generateur.Facile;

import main.Generateur.Generateur;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Random;

public class GenerateurTakuzuFacile implements Generateur {

    private int nbCaseRandomASupprimer;
    private Random rand;
    private Solveur lesSolveursSimples;
    private int hauteur;
    private int largeur;
    private Takuzu takuzuResolut;

    public GenerateurTakuzuFacile(int nbCaseASupprimerAleatoirement, Takuzu takuzuGagnant) {
        this.nbCaseRandomASupprimer = nbCaseASupprimerAleatoirement;
        this.rand = new Random();
        this.lesSolveursSimples = new MaitreSolveur();
        this.takuzuResolut = takuzuGagnant;
        this.hauteur = takuzuGagnant.getTailleGrille();
        this.largeur = takuzuGagnant.getTailleGrille();
    }

    public Takuzu generer() {
        Takuzu unNouveauEtBeauTakuzu, unNouveauEtBeauTakuzuSimplifie;

        long startTime = System.currentTimeMillis();

        unNouveauEtBeauTakuzu = genererTakuzuAvecTrous();
        unNouveauEtBeauTakuzuSimplifie = enleverToutesLesCasesInutiles(unNouveauEtBeauTakuzu);

        long endTime = System.currentTimeMillis();

        System.out.println("Generateur Takuzu Facile generer() execution time: " + (endTime-startTime) + "ms");

        return unNouveauEtBeauTakuzuSimplifie;
    }

    private Takuzu genererTakuzuAvecTrous() {
        //déclaration
        int nbCaseSupprimeeAleatoirement, cpt;
        int[] infoValeurEnlever;

        //initialisation des types primitifs
        nbCaseSupprimeeAleatoirement = 0;
        cpt = 0;

        while((nbCaseSupprimeeAleatoirement < nbCaseRandomASupprimer )) {

            //On enlève une case au hasard
            infoValeurEnlever = enleverUneCaseRandom(takuzuResolut, hauteur, largeur);
            nbCaseSupprimeeAleatoirement++;


            //Puis on essaye de résoudre SANS faire d'hypothèses
            if (!verifierSiPeutResoudreLogiquement(takuzuResolut)) {
                //les solveurs simples n'arrivent pas à résoudre
                //la valeur qu'on a enlever entraine des hypothèses, elle est trop importante
                //donc on doit la remettre

                nbCaseSupprimeeAleatoirement--;
                if (infoValeurEnlever[2] == 0)
                    takuzuResolut.play0(infoValeurEnlever[0], infoValeurEnlever[1]);
                else
                    takuzuResolut.play1(infoValeurEnlever[0], infoValeurEnlever[1]);

            }
            cpt++;
        }
        takuzuResolut.affichage();
        System.out.println("nbCaseSupprimeeAleatoirement = " + nbCaseSupprimeeAleatoirement);
        //System.out.println("Tour de boucle : " + cpt);
        return takuzuResolut;
    }

    private Takuzu enleverToutesLesCasesInutiles(Takuzu unTakuzu) {
        System.out.println("Début enleverToutesLesCasesInutiles");
        boolean pause, sarreter;
        int valeurEnlever, i, j, cpt, nbCaseInutileTotal, nbCaseEnleverCeTour;

        i = 0;
        j = 0;
        nbCaseInutileTotal = 0;
        nbCaseEnleverCeTour = -1;
        pause = false;
        valeurEnlever = -2;
        sarreter = false;

        while(nbCaseEnleverCeTour != 0) { //pour recommencer le parcour total si on peut encore supprimer des cases
            nbCaseEnleverCeTour = 0;
            //System.out.println("Début boucle");
            //System.out.println(sarreter);
            while (!sarreter) { //parcour total ici
                //System.out.println("Début à ord abs " + ord + " " + abs);
                //System.out.println("Début à i j  " + i + " " + j);
                cpt = 0;
                for (; i < hauteur && !pause; i++) {
                    j = cpt == 0 ? j : 0;
                    for (/*j = cpt == 0 ? abs : 0*/; j < largeur && !pause; j++) {

                        //System.out.println(i + " " + j);
                        if (unTakuzu.getValue(i, j) != -1) {
                            //System.out.println("Valeur Trouvée");
                            valeurEnlever = unTakuzu.suppr(i, j);
                            pause = true;
                        }
                    }
                    cpt++;
                }
                pause = false;
                i--; j--; //util pcq les boucles for font un tour de trop à la sortie, donc il faut rectifier


                //Puis on essaye de résoudre SANS faire d'hypothèses
                if (!verifierSiPeutResoudreLogiquement(unTakuzu)) {
                    //les solveurs simples n'arrivent pas à résoudre
                    //la valeur qu'on a enlever entraine des hypothèses, elle est trop importante
                    //donc on doit la remettre

                    //System.out.println("Valeur importante -> " + i + " " + j);
                    if (valeurEnlever == 0)
                        unTakuzu.play0(i, j);
                    else
                        unTakuzu.play1(i, j);
                } else {
                    //System.out.println("Valeur pas importante -> " + i + " " + j);
                    nbCaseInutileTotal++;
                    nbCaseEnleverCeTour++;
                }

                if (i == hauteur - 1 && j == largeur - 1) {
                    sarreter = true;
                    //System.out.println("Arrêt");
                } else {
                    if (j == largeur-1) {
                        //ord = i + 1;
                        i++;
                        //abs = 0;
                        j = 0;
                    } else {
                        //ord = i;
                        //abs = j + 1;
                        j++;
                    }
                    //System.out.println("Fin à ord abs " + ord + " " + abs);
                    //System.out.println("Fin à i j  " + i + " " + j);
                }
            }
            System.out.println("nbCaseEnleverCeTour = " + nbCaseEnleverCeTour);
            if (nbCaseEnleverCeTour > 1)
            {
                sarreter = false;
                i = 0; j = 0;
            }
            else {
                sarreter = true;
            }
        }
        System.out.println("nbCaseInutileTotal = " + nbCaseInutileTotal);
        System.out.println("Fin enleverToutesLesCasesInutiles");
        return unTakuzu;
    }

    /**
     * Vérifie si unTakuzu est facile à résoudre.
     *
     * @param unTakuzu quelconque
     * @return true ssi unTakuzu est facile, faux sinon
     */
    private boolean verifierSiPeutResoudreLogiquement(Takuzu unTakuzu) {
        Takuzu t = unTakuzu.cloneTakuzu();
        t.seResoudre(lesSolveursSimples);

        return t.estGagnant();
    }

    private int[] enleverUneCaseRandom(Takuzu unTakuzu, int hauteur, int largeur) {
        int abscisse, ordonnee, valeurEnlever;
        do {
            abscisse = rand.nextInt(largeur);
            ordonnee = rand.nextInt(hauteur);
            valeurEnlever = unTakuzu.suppr(ordonnee, abscisse);
        } while (valeurEnlever == -1);//mais on supprime une case remplit, pas une case vide

        return new int[]{ordonnee, abscisse, valeurEnlever};
    }
}
