package main;

public class Takuzu {
    private Grille grille;

    public Takuzu() {
        this.grille = new Grille(4,4);
    }
    public Takuzu(Grille grille) {
        this.grille = grille;
    }

    public void play(int x, int y, int value)
    {
        grille.setValue(x, y, value);
    }

    public boolean checkColumn(int x){
        throw new RuntimeException("Not implemented yet !");
    }
    public boolean checkRow(int x){
        throw new RuntimeException("Not implemented yet!");
    }

    public void affichage() { 
        System.out.println("Takuzu:\n");
        grille.affichage();
    }
}
