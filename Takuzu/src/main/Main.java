package main;

//TODO
// -tests
// -Résolveur

public class Main {

    public static void main(String[] args) {
       Takuzu takuzu = new Takuzu(6);
       takuzu.preRemplissage();

        Takuzu tak = takuzu.cloneTakuzu();
        tak.play0(0, 1);
        tak.affichage();
        System.out.println(tak.estValide());
        tak.affichageGraphique();


       System.out.println("\nRéponse");
       Takuzu rep = Takuzu.getPreRemplissageAnswer();

        rep.affichage();

        System.out.println("Takuzu gagnant ?");
        System.out.println(rep.estGagnant());



    }
}
