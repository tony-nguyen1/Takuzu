package main;

import java.util.Collection;

public interface Regles {
    public boolean checkTableau1D(Collection<Integer> tableau1D);
    public boolean compareTableau1D(Collection<Integer> t1, Collection<Integer> t2);
    public boolean estGagnant();
}
