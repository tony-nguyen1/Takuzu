package test.Solveur;

import main.CustomsExceptions.OddDimensionsGrilleException;
import main.Grille;
import main.Solveur.PatternCroix3.PatternCroix3;
import main.Solveur.Solveur;
import main.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class testPatternCroix3 {
//    @Test
//    public void test_if_takuzu_is_tested()
//    {
//        fail("Implémentez-moi");
//    }

    @Test
    public void test_if_it_works() {
        Takuzu unTakuzu;

        unTakuzu = new Takuzu(4);
        Solveur petiteCroix = new PatternCroix3(unTakuzu);

        unTakuzu.play0(1,0);
        unTakuzu.play0(3,0);

        petiteCroix.resoudre();

        assertEquals(1,unTakuzu.getValue(2,0));
    }
}
