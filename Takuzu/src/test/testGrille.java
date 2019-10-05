package test;
import main.Grille;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class testGrille {

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


//     @Test// à modifier pour enlever le getGrille()
//     public void test_if_constructor_method_create_2D_array_with_right_size() {
//         Grille g = new Grille(6);
//         assertEquals(6,g.getGrille().size());
//         for (int i = 0; i < 6; i++) {
//             assertEquals(6,g.getLigne(i).size());
//         }
//     }

//     @Test
//     public void test_getLigne()
//     {
//         Grille g = new Grille(6);
//         for (int i = 0; i < 6; i++) {
//             g.ecrireValeur(i,i,i);//diagonale de 0 à 6-1
//         }

//         ArrayList<Integer> answer, remplis;
//         remplis = new ArrayList<>();

//         for (int i = 0; i < 6; i++) { remplis.add(-1); }
//         for (int j = 0; j < 6; j++) {
//             //Création d'une liste remplis de -1
//             answer = new ArrayList<>();
//             answer.addAll(remplis);
//             answer.set(j,j);  // j-ème élément égale à j
//             /////////////////////////////////////

//             assertArrayEquals(answer.toArray(),g.getLigne(j).toArray());
//         }
//     }

//     //@Disabled
//     @Test
//     public void test_getCollonne()
//     {
//         Grille g = new Grille(6);
//         for (int i = 0; i < 6; i++) {
//             g.ecrireValeur(i,0,i);//
//         }

//         ArrayList<Integer> answer, remplis;
//         remplis = new ArrayList<>();

//         for (int i = 0; i < 6; i++) { remplis.add(-1); }
//         for (int j = 0; j < 6; j++) {
//             //Création d'une liste remplis de -1
//             answer = new ArrayList<>();
//             answer.addAll(remplis);
//             answer.set(0,j);  // 1er élément égale à j
//             /////////////////////////////////////

//             assertArrayEquals(answer.toArray(),g.getCollonne(j).toArray());
//         }


//     }
}
