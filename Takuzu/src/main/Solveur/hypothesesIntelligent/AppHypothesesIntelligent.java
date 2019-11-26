package main.Solveur.hypothesesIntelligent;


import main.Generateur.Difficile.GenerateurTakuzuDifficile;
import main.Solveur.Hypotheses.Hypotheses;
import main.Takuzu;
@SuppressWarnings("Duplicates")
public class AppHypothesesIntelligent {
    public static void main(String[] args) {
        //Takuzu takuzu = new Takuzu(16);
        //takuzu.preRemplissagePourBenchMarkEntreNous();

        GenerateurTakuzuDifficile g = new GenerateurTakuzuDifficile(14, 5);

        Takuzu takuzu = g.generer();

        Takuzu takuzu2 = takuzu.cloneTakuzu();

        System.out.println("Dans le main");
        takuzu.affichage();

        takuzu.seResoudre(new HypothesesIntelligent());
        takuzu.affichage();

        takuzu2.seResoudre(new Hypotheses());
        takuzu.affichage();

        System.out.println("Fin du programme");
    }
}
