package Generateur;

import takuzu.Generateur.Facile.GenerateurTakuzuFacile;
import takuzu.Solveur.MaitreSolveur.MaitreSolveur;
import takuzu.Takuzu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class testGenerateurTakuzuFacile {
    @Test
    public void test_if_generateur_is_tested()
    {
        Takuzu takuzuFacile = Takuzu.genererUnTakuzu(new GenerateurTakuzuFacile(Takuzu.getPreRemplissageAnswer2()));
        takuzuFacile.affichage();
        assertTrue(takuzuFacile.estValide());

        takuzuFacile.seResoudre(new MaitreSolveur());
        takuzuFacile.affichage();

        assertTrue(takuzuFacile.estGagnant());

    }
}

