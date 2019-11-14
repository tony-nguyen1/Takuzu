package Solveur;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testMaitreSolveur {
    @Test
    public void test_if_resoudre_works() {
        Takuzu unTakuzu = new Takuzu(6);
        unTakuzu.preRemplissage2();

        assertFalse(unTakuzu.estGagnant());

        unTakuzu.seResoudre(new MaitreSolveur());
        unTakuzu.affichage();

        assertTrue(unTakuzu.estGagnant());
    }
}
