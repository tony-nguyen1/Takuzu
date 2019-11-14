package main.Solveur.BacktrackIntelligent;

import main.Solveur.Equilibre.Equilibre;
import main.Solveur.EviteTriplet.EviteTriplet;
import main.Takuzu;

import java.util.Deque;
import java.util.LinkedList;

public class BacktrackIntelligent{

    Deque<Takuzu> backupTakuzu;
    int cpt = 0;
    Takuzu gagnant = null;

    public BacktrackIntelligent() {
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
        Equilibre equilibre = new Equilibre();
        EviteTriplet eviteTriplet = new EviteTriplet();
        backupTakuzu.add(takuzu);

        while (!backupTakuzu.isEmpty()) {
            Takuzu takuzuBis = backupTakuzu.poll();
            cpt++;

            while (true){
                boolean bool1, bool2;
                bool1 = eviteTriplet.resoudre(takuzuBis);
                bool2 = equilibre.resoudre(takuzuBis);
                if (!bool1 && !bool2){
                    break;
                }

            }

            if (takuzuBis.estGagnant()) {
                gagnant = takuzuBis;

                System.out.println("Compteur " + cpt);
                //takuzuBis.affichage();


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