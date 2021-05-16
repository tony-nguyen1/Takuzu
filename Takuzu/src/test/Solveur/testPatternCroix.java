package Solveur;

import takuzu.Solveur.PatternCroix.PatternCroix;
import takuzu.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class testPatternCroix {
    @Test
    public void test_if_resoudre_works() {
        System.out.println("Grille facile de 0");
        Takuzu unTakuzu = new Takuzu(6);
        unTakuzu.play0(1,0);
        unTakuzu.play0(1,2);
        unTakuzu.play0(0,1);
        unTakuzu.play0(2,1);

        unTakuzu.play1(3,3);
        unTakuzu.play1(2,4);
        unTakuzu.play1(3, 5);
        unTakuzu.play1(4,4);

        unTakuzu.play1(5,0);
        unTakuzu.play1(5,1);

        unTakuzu.play0(0,5);
        unTakuzu.play0(0,4);
        //unTakuzu.affichage();


        assertFalse(unTakuzu.estGagnant());
        unTakuzu.seResoudre(new PatternCroix());
        assertFalse(unTakuzu.estGagnant());

        unTakuzu.affichage();

        assertEquals(1,unTakuzu.getValue(1,1));
        assertEquals(1,unTakuzu.getValue(0,3));

        assertEquals(0,unTakuzu.getValue(3,4));
        assertEquals(0,unTakuzu.getValue(5,2));
    }
}
