package main;

public class Takuzu {
    private Grille grille;

    public Takuzu() {
        this.grille = new Grille(4,4);
    }
    public Takuzu(Grille grille) {
        this.grille = grille;
    }
    public Takuzu(int i) { grille = new Grille(i,i); }

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

    public void affichageGraphique()
    {
        grille.affichageGraphique();
    }

    public void play0(int x, int y) { grille.setValue(x, y, 0); }
    public void play1(int x, int y) { grille.setValue(x, y, 1); }

    public void preRemplissage() {
        //1er ligne
        play0(0,0);
        play0(1,0);
        play1(4,0);
        play1(5,0);

        //2e ligne
        play1(2,1);
        play1(5,1);

        //3e ligne
        play1(0,2);
        play0(3,2);

        //4e ligne
        play1(0,3);
        play0(3,3);
        play0(5,3);

        //5e ligne
        play1(1,4);

        //6e ligne
        play1(0,5);
        play1(1,5);
        play0(4,5);
        play0(5,5);
    }
}
