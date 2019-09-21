package main;

import java.util.Collection;

public class ReglesTakuzu implements Regles{

    @Override
    public boolean checkTableau1D(Collection<Integer> tableau1D) {
        return false;
    }

    @Override
    public boolean compareTableau1D(Collection<Integer> t1, Collection<Integer> t2) {
        return false;
    }

    @Override
    public boolean estGagnant() {
        return false;
    }
}
