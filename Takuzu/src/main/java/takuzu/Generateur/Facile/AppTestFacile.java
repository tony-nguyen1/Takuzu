package takuzu.Generateur.Facile;

import takuzu.Generateur.Generateur;
import takuzu.Solveur.MaitreSolveur.MaitreSolveur;
import takuzu.Takuzu;

public class AppTestFacile {
    public static void main(String[] args) {

        String path = System.getProperty("user.dir");
        Takuzu tak = Takuzu.load(path + "/Takuzu/Ressources/takuzu_taille_4.txt");

        Generateur g = new GenerateurTakuzuFacile(tak);
        Takuzu takuzuFacile = g.generer();

        System.out.println("Takuzu généré :");
        takuzuFacile.affichage();

        takuzuFacile.seResoudre(new MaitreSolveur());
        takuzuFacile.affichage();
    }
}
