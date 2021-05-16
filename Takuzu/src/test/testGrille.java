
import takuzu.Takuzu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class testGrille {
    private Takuzu g;

    @BeforeEach
    void setup() {

        g = new Takuzu(2);


        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                assertEquals(-1, g.getValue(j,i));//grille vide avant
            }
        }
    }


    @Test
    public void test_getters_and_setters()
    {
        //grille vide avant

        //grille remplit de 1
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                g.setValue(j,i,1);
            }
        }

        //grille remplit après
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                assertEquals(1, g.getValue(j,i));
            }
        }
    }

    @Test
    public void test_if_isRowFull_works()
    {
        //grille vide avant
        for (int i = 0; i < 2; i++)
        {
            assertFalse(g.isRowFull(i));
        }

        //remplissage 1er ligne de 0
        g.setValue(0,0,0);
        g.setValue(1,0,1);
        //remplissage 2e ligne de 1
        g.setValue(1,1,1);
        g.setValue(0,1,1);

        //grille après
        assertTrue(g.isRowFull(0));
        assertTrue(g.isRowFull(1));
    }

    @Test
    public void test_equals2Column_with_same_colomn() {
        g.fill(0);

        assertTrue(g.equals2Column(0,1));
        assertTrue(g.equals2Column(1,0));
        assertTrue(g.equals2Column(1,1));
    }

    @Test
    public void test_equals2Column_with_different_colomn() {
        g.setValue(0,0,0);
        g.setValue(0, 1, 1);
        g.setValue(1,0,1);
        g.setValue(1, 1, 0);


        assertFalse(g.equals2Column(0,1));
        assertTrue(g.equals2Column(1,1));
        assertTrue(g.equals2Column(0,0));
    }

    @Test
    public void test_equals2Row_with_same_row() {
        g.fill(0);

        assertTrue(g.equals2Row(0,0));
        assertTrue(g.equals2Row(1,1));
        assertTrue(g.equals2Row(0,1));
    }

    @Test
    public void test_equals2Row_with_different_row() {
        g.setValue(0,0,0);
        g.setValue(1,0,1);
        g.setValue(0,1,1);
        g.setValue(1,1,0);

        assertFalse(g.equals2Row(0,1));
        assertTrue(g.equals2Row(0,0));
        assertTrue(g.equals2Row(1,1));
    }

    @Test
    public void test_isColumnFull_when_grille_is_full() {
        g.fill(1);

        assertTrue(g.isRowFull(0));
        assertTrue(g.isRowFull(1));
    }

    @Test
    public void test_isColumnFull_when_grille_is_empty() {
        assertFalse(g.isRowFull(0));
        assertFalse(g.isRowFull(1));
    }
/*
    @Test
    public void test_compteNbCaseParLigne_when_empty() {
        int[] actual = g.compteNbCaseRemplitParLigne();

        for (int i : actual) { assertEquals(0, i); }
    }

    @Test
    public void test_compteNbCaseParLigne_when_not_empty() {
        g.setValue(0,0,0);
        g.setValue(0,1,0);

        g.setValue(1,0,0);

        int[] actual = g.compteNbCaseRemplitParLigne();

        assertEquals(2,actual[0]);
        assertEquals(1,actual[1]);
    }

    @Test
    public void test_compteNbCaseParColonne_when_empty() {
        int[] actual = g.compteNbCaseRemplitParColonne();

        for (int i : actual) { assertEquals(0, i); }
    }

    @Test
    public void test_compteNbCaseParColonne_when_not_empty() {
        g.setValue(0,0,0);
        g.setValue(1,0,0);

        g.setValue(1,1,0);

        int[] actual = g.compteNbCaseRemplitParColonne();

        assertEquals(2,actual[0]);
        assertEquals(1,actual[1]);
        assertEquals(0,actual[2]);
        assertEquals(0,actual[3]);
    }

 */

}
