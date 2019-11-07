package Solveur;

import main.Solveur.PatternCroix.PatternCroix;
import main.Solveur.Solveur;
import main.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPatternCroix {
    @Test
    public void test_if_resoudre_works() {
        Takuzu unTakuzu;

        unTakuzu = new Takuzu(6);
        Solveur croix = new PatternCroix();

        unTakuzu.play0(0,0);
        unTakuzu.play0(1,0);

        unTakuzu.play0(3,0);
        unTakuzu.play0(4,0);

        unTakuzu.seResoudre(croix);

        assertEquals(1,unTakuzu.getValue(2,0));
    }
}
