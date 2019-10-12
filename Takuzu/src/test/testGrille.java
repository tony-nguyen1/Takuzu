package test;

import main.Grille;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class testGrille {
    //TODO faire un before each pour enlever un peu de duplication du code

    @Test
    public void test_getters_and_setters()
    {
        Grille g = new Grille(2,2);

        //grille vide avant
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                assertEquals(-1, g.getValue(j,i));
            }
        }

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
    public void test_checkUneLigneRemplit()
    {
        Grille g = new Grille(2,2);

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
        Grille g = new Grille(2,2);

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
    public void test_equals2Row()
    {
        Grille g = new Grille(2,2);

        //remplissage de 1
        g.fill(1);

        assertTrue(g.equals2Row(0,1));
    }

    @Test
    public void test_isValide(){
        Grille g = new Grille(2,2);

        //remplissage de 1
        g.fill(1);

        assertFalse(g.isValide());
    }
}
