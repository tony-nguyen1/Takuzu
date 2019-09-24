package main;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("\n");

        Grille g = new Grille(4, 4);
        g.affichage();

        g.setValue(0, 2, 1);

        g.affichage();


        Takuzu t = new Takuzu(g);
        t.play(0, 3, 0);
        t.affichage();
    }
}
