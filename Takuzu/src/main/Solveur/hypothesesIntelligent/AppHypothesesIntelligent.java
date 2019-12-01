package main.Solveur.hypothesesIntelligent;


import main.Solveur.Hypotheses.Hypotheses;
import main.Takuzu;
@SuppressWarnings("Duplicates")
public class AppHypothesesIntelligent {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(14);
        takuzu.preRemplissagePourBenchMarkEntreNous();

        Takuzu takuzu2 = takuzu.cloneTakuzu();

        System.out.println("Dans le main");

        HypothesesIntelligent hypotheses = new HypothesesIntelligent();
        takuzu.seResoudre(new HypothesesIntelligent());
        takuzu.affichage();


        Hypotheses hypothese = new Hypotheses();
        takuzu2.seResoudre(hypothese);


        System.out.println("Fin du programme");
    }
}
