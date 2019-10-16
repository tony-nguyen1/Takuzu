import main.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



public class testGrille {
    private Grille g;

    @BeforeEach
    void setup() {

        g = new Grille(2, 2);

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
    public void test_if_rowFilled_works()
    {
        //remplissage 1er ligne de 0
        g.setValue(0,0,0);
        g.setValue(1,0,1);
        //remplissage 2e ligne de 1
        g.setValue(1,1,1);
        g.setValue(0,1,1);

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(1);

        assertEquals(answer, g.rowFilled());
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
    public void test_columnFilled_when_grille_is_full() {
        g.fill(0);

        ArrayList<Integer> answer = new ArrayList<>(2);
        answer.add(0);
        answer.add(1);

        assertEquals(answer,g.columnFilled());
    }

    @Test
    public void test_columnFilled_when_grille_is_empty() {
        ArrayList<Integer> answer = new ArrayList<>(2);

        assertEquals(answer,g.columnFilled());
    }

    @Test
    public void test_columnFilled_when_grille_is_half_empty() {
        g.setValue(0,0,1);
        g.setValue(1, 0, 0);

        ArrayList<Integer> answer = new ArrayList<>(2);
        answer.add(0);


        assertEquals(answer,g.columnFilled());
    }
}
