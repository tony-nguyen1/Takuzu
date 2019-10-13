package test.Solveur;

import main.Grille;
import main.Solveur.PatternCroix5.PatternCroix5;
import main.Solveur.Solveur;
import main.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPatternCroix5 {
    @Test
    public void test_if_resoudre_works() {
        Takuzu unTakuzu;

        unTakuzu = new Takuzu(6);
        Solveur petiteCroix = new PatternCroix5(unTakuzu);

        unTakuzu.play0(0,0);
        unTakuzu.play0(1,0);

        unTakuzu.play0(3,0);
        unTakuzu.play0(4,0);

        petiteCroix.resoudre();

        assertEquals(1,unTakuzu.getValue(2,0));
    }
}
