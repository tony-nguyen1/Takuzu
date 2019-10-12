package test;

import main.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

//TODO make tests for equals2Column(), isColumnFull() and columnFilled().

public class testGrille {
    private Grille g;

    @BeforeEach
    void setup() {
        g = new Grille(2,2);

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                assertEquals(-1, g.getValue(j,i));
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
    public void test_equals2Row()
    {
        //remplissage de 1
        g.fill(1);

        assertTrue(g.equals2Row(0,1));
    }
}
