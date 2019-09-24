package main;



public class Grille {
    private int[,] grille;
    private final int HEIGHT;
    private final int WIDTH;

    public Grille(int width, int height) {
        grille = new int[width, height]{-1};
        HEIGHT = height;
        WIDTH = width;
    }

    @Override
    public String toString() {
        return "Grille{\n" + grille + '}\n';
    }

    public void affichage() {
        //TODO
        throw new RuntimeException("Not implemented yet!");
    }


    public void setValue(int x, int y, int value){
        grille[x,y] = value;
    }

    public int getValue(int x, int y){
        return grille[x,y];
    }

    
    public int getHEIGHT() {
        return this.HEIGHT;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }
}
