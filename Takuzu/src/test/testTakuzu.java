import main.Takuzu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testTakuzu {
    Takuzu unTakuzuNormale;

    @BeforeEach
    void setup() {
        unTakuzuNormale = new Takuzu(6);
    }

    //test des règles :
    @Test
    public void test_if_can_put3times_0_back_to_back()
    {
        Takuzu badTakuzu = unTakuzuNormale;

        badTakuzu.play0(0,0);
        badTakuzu.play0(0,1);
        badTakuzu.play0(0,2);

        assertFalse(badTakuzu.estValide());
    }

    @Test
    public void test_if_can_make_more_than_half_of_the_line_in_0() {
        Takuzu badTakuzu = unTakuzuNormale;

        badTakuzu.play0(0,0);
        badTakuzu.play0(0,1);
        badTakuzu.play0(0,3);
        badTakuzu.play0(0,4);

        assertFalse(badTakuzu.estValide());
    }

    @Test
    public void test_if_can_put3times_1_back_to_back()
    {
        Takuzu badTakuzu = unTakuzuNormale;

        badTakuzu.play1(0,0);
        badTakuzu.play1(0,1);
        badTakuzu.play1(0,2);

        assertFalse(badTakuzu.estValide());
    }

    @Test
    public void test_if_can_make_more_than_half_of_the_line_in_1() {
        Takuzu badTakuzu = unTakuzuNormale;

        badTakuzu.play1(0,0);
        badTakuzu.play1(0,1);
        badTakuzu.play1(0,3);
        badTakuzu.play1(0,4);

        assertFalse(badTakuzu.estValide());
    }

    @Test
    public void test_if_can_make_the_same_line_2times() {
        Takuzu badTakuzu = unTakuzuNormale;

        badTakuzu.play0(0,0);
        badTakuzu.play1(0,1);
        badTakuzu.play0(0,2);
        badTakuzu.play1(0,3);
        badTakuzu.play0(0,4);
        badTakuzu.play1(0,5);

        badTakuzu.play0(1,0);
        badTakuzu.play1(1,1);
        badTakuzu.play0(1,2);
        badTakuzu.play1(1,3);
        badTakuzu.play0(1,4);
        badTakuzu.play1(1,5);

        assertFalse(badTakuzu.estValide());
    }

    @Test
    public void test_if_takuzu_vide_is_valide() {
        assertTrue(unTakuzuNormale.estValide());
    }
}
