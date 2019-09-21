package main;

public class Takuzu {
    private Grille grille;
    private Regles r;

    public Takuzu() {
        this.grille = new Grille(6);
        this.r = new ReglesTakuzu();
    }

    public void mettreZero(int x, int y)
    {
        try {
            grille.ecrireValeur(x, y, 0);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("Erreurs d'index - " + e.getMessage());
            //e.printStackTrace();
        }
        finally {
            System.out.println("x = " + x + "  y = " + y);
        }
    }

    public void mettreUn(int x, int y)
    {
        try {
            grille.ecrireValeur(x, y, 1);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("Erreurs d'index - " + e.getMessage());
            //e.printStackTrace();
        }
        finally {
            System.out.println("x = " + x + "  y = " + y);
        }
    }

    public void affichage() { grille.affichage();}
}
