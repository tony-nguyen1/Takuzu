package takuzu.Solveur.BacktrackIntelligent;

import takuzu.Takuzu;

public class AppBacktrackIntelligent {
    public static void main(String[] args) {

        Takuzu takuzu = new Takuzu(14);
        takuzu.preRemplissagePourBenchMarkEntreNous();
        takuzu.affichage();


        long startTime = System.currentTimeMillis();
        takuzu.seResoudre(new BacktrackIntelligent());
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        takuzu.affichage();
    }
}