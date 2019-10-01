package main;



import main.IHM.IHM;

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();
       takuzu.affichage();
       takuzu.affichageGraphique();
    }
}
