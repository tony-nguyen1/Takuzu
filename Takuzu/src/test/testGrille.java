import main.Grille;
import main.Takuzu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;



public class testGrille {
    private Grille g;

    @BeforeEach
    void setup() {

        g = new Grille(2, 4);

        /*
        try {
            g = new Grille(2,2);
        }
        catch (OddDimensionsGrilleException e)
        {
            System.out.println("Grille creation has failed.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
         */

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

    @Test
    public void test_rechercheCasesVideAux_when_grille_is_empty(){
        LinkedList<int[]> abc = g.rechercheCasesVideAux(0);

        assertEquals(4,abc.size());//4 cases sur ma ligne

        int cpt = 0;
        for (int[] i : abc) {
            System.out.println(i[0] + " " + i[1]);


            assertEquals(0,i[0]);
            assertEquals(cpt, i[1]);

            cpt++;
        }
    }

    @Test
    public void test_rechercheCasesVideSurToutesLigne_when_grille_is_empty(){
        LinkedList<LinkedList<int[]>> abc = g.rechercheCasesVide();

        assertEquals(2,abc.size()); //2 lignes sur ma grille

        int i, j;
        i = 0;
        j = 0;
        for (LinkedList<int[]> a : abc) {
            assertEquals(4,a.size());//4 cases sur ma ligne

            j = 0;
            for (int[] coord : a) {
                System.out.println(coord[0] + " " + coord[1]);
                assertEquals(i, coord[0]);
                assertEquals(j, coord[1]);

                j++;
            }
            i++;
        }
    }

    @Test
    public void test_rechercheCasesVideSurToutesLigne_when_grille_is_empty_and_bigger(){
        Takuzu t = new Takuzu(6);
        t.preRemplissage3();
        g = t.getGrille();
        t.affichage();

        LinkedList<LinkedList<int[]>> abc = g.rechercheCasesVide();

        assertEquals(6,abc.size());

        int i, j;
        i = 0;
        j = 0;
        for (LinkedList<int[]> a : abc) {

            j = 0;
            for (int[] coord : a) {
                System.out.println(coord[0] + " " + coord[1]);
                /*assertEquals(i, coord[0]);
                assertEquals(j, coord[1]);*/

                j++;
            }
            i++;
        }
    }

    /*@Test
    public void test_rechercheCasesVide2Aux() {
        g.setValue(0,0,0);

        LinkedList<int[]> actual = g.rechercheCasesVide2Aux(0);

        for (int[] i : actual) {
            System.out.println(i[0] + " " + i[1]);
        }

        assertEquals(1, actual.size());

        assertEquals(1, actual.get(0)[0]);
        assertEquals(0, actual.get(0)[1]);
    }*/
}
