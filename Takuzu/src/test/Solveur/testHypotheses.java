package Solveur;

import main.Solveur.Hypotheses.Hypotheses;
import main.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testHypotheses {
    @Test
    public void test_if_resoudre_works() {

        Takuzu unTakuzu = new Takuzu(14);
        unTakuzu.preRemplissagePourBenchMarkEntreNous();

        long startTime = System.currentTimeMillis();
        unTakuzu.seResoudre(new Hypotheses());
        long endTime = System.currentTimeMillis();

        unTakuzu.affichage();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        assertTrue(unTakuzu.estGagnant());
    }
}
