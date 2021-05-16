package takuzu.Solveur.BacktrackIntelligent;

import takuzu.Case;
import takuzu.Solveur.Equilibre.Equilibre;
import takuzu.Solveur.PatternCroix.PatternCroix;
import takuzu.Solveur.Solveur;
import takuzu.Takuzu;

import java.util.Deque;
import java.util.LinkedList;

public class BacktrackIntelligent implements Solveur {

    Deque<Takuzu> backupTakuzu;
    Takuzu gagnant = null;

    public BacktrackIntelligent() {
        backupTakuzu = new LinkedList<>();
    }

    public Takuzu getGagnant() {
        return gagnant;
    }


    private void ajoutListe(Takuzu tak) {
        Case caseAJouer;

        caseAJouer = tak.trouverMeilleurCase();

        Takuzu tak0 = tak.cloneTakuzu();
        Takuzu tak1 = tak.cloneTakuzu();
        tak0.play0(caseAJouer.getLigne(), caseAJouer.getColonne());
        tak1.play1(caseAJouer.getLigne(), caseAJouer.getColonne());
        backupTakuzu.add(tak0);
        backupTakuzu.add(tak1);

    }

    @Override
    public boolean resoudre(Takuzu takuzu) {
        Equilibre equilibre = new Equilibre();
        PatternCroix eviteTriplet = new PatternCroix();
        backupTakuzu.add(takuzu.cloneTakuzu());

        while (!backupTakuzu.isEmpty()) {
            Takuzu takuzuBis = backupTakuzu.poll();

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

                System.out.println("Compteur " + backupTakuzu.size());
                takuzu.remplirLaDifference(takuzuBis);

                return true;
            }

            //Si le Takuzu n'est pas valide, alors on arrete de chercher de ce côté, c-a-d que l'ajoute liste prochain ne sera pas "actif"
            if (!takuzuBis.estValide()) {
                continue;
            }

            ajoutListe(takuzuBis);
        }
        return false;
    }
}