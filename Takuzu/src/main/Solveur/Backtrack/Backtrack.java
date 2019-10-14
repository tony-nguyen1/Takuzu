package main.Solveur.Backtrack;

import main.Grille;
import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Deque;
import java.util.LinkedList;

public class Backtrack implements Solveur {

    Takuzu takuzu;

    public Backtrack(Takuzu takuzu) {
        this.takuzu = takuzu;
    }

    @Override
    public boolean resoudre() {

        int column = takuzu.getGrille().getHEIGHT();
        int row = takuzu.getGrille().getWIDTH();

        int i = 0;
        int j = 0;

        Deque<Takuzu> backupTakuzu = new LinkedList<Takuzu>();
        backupTakuzu.add(takuzu);

        while (!backupTakuzu.isEmpty()) {
            Takuzu takuzu = backupTakuzu.poll();

            if (takuzu.estGagnant()) {
                return true;
            }

            //Si le Takuzu n'est pas valide, alors on arrete de chercher de ce côté, c-a-d que le while prochain ne sera pas "actif"
            if (!takuzu.checkAllRowAll() && !takuzu.checkAllColumnAll()) {
                continue;
            }

            //Y'a des manières plus propres que ca, mais flemme de debugger derrière pour voir si ca marche parfaitement
            Grille grille = takuzu.getGrille();
            Boolean trouve = false;
            while (i < row) {
                j = 0;
                while (j < column) {
                    //Si l'on trouve une case vide on place le 0 et le 1
                    if (grille.getValue(i, j) == -1) {
                        Takuzu takuzu0 = takuzu.cloneTakuzu();
                        takuzu0.play0(i, j);
                        backupTakuzu.add(takuzu0);
                        Takuzu takuzu1 = takuzu.cloneTakuzu();
                        takuzu1.play1(i, j);
                        backupTakuzu.add(takuzu1);
                        trouve = true;
                        break;
                    }
                    j++;
                }
                if (trouve) {
                    break;
                }
                i++;
            }
            i = 0;
            resoudre();
        }

        return true;
    }
}

