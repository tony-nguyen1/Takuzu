package takuzu.Solveur.Equilibre;

import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

public class AppEquilibreTest {
    public static void main(String[] args) {
        Takuzu takuzu = new Takuzu(6);
        takuzu.play0(0, 0);
        takuzu.play0(0,1);
        takuzu.play0(0,3);
        takuzu.play0(1,0);
        takuzu.play1(4, 0);




        takuzu.affichage();

        Solveur sol = new Equilibre();

        sol.resoudre(takuzu);


        takuzu.affichage();
    }
}
