package main.Solveur.Backtrack;

import main.Solveur.Solveur;
import main.Takuzu;

import java.util.Deque;
import java.util.LinkedList;

public class Backtrack{

    Deque<Takuzu> backupTakuzu;
    int cpt = 0;
    Takuzu gagnant = null;

    public Backtrack() {
        backupTakuzu = new LinkedList<>();
    }

    public Takuzu getGagnant() {
        return gagnant;
    }

    private void ajoutListe(Takuzu tak) {
        int column = tak.getGrille().getHEIGHT();
        int row = tak.getGrille().getWIDTH();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (tak.getValue(i, j) == -1) {
                    Takuzu tak0 = tak.cloneTakuzu();
                    Takuzu tak1 = tak.cloneTakuzu();
                    tak0.play0(i, j);
                    tak1.play1(i, j);
                    backupTakuzu.add(tak0);
                    backupTakuzu.add(tak1);
                    return;
                }
            }
        }
    }

    public boolean resoudre(Takuzu takuzu) {
        backupTakuzu.add(takuzu);

        while (!backupTakuzu.isEmpty()) {
            Takuzu takuzuBis = backupTakuzu.poll();

            if (takuzuBis.estGagnant()) {
                gagnant = takuzuBis;
                /*
                while (!backupTakuzu.isEmpty()) {
                    backupTakuzu.poll();
                    cpt++;
                }


                System.out.println(cpt);
                takuzuBis.affichage();

                */
                return true;
            }

            //Si le Takuzu n'est pas valide, alors on arrete de chercher de ce côté, c-a-d que le while prochain ne sera pas "actif"
            if (!takuzuBis.estValide()) {
                continue;
            }

            ajoutListe(takuzuBis);
        }
        return false;
    }


}
