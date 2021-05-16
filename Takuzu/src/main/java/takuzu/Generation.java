package takuzu;

import takuzu.Generateur.Difficile.GenerateurTakuzuDifficile;
import takuzu.Generateur.Facile.GenerateurTakuzuFacile;
import takuzu.Generateur.Generateur;

public class Generation {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir")+"/Takuzu/Ressources/";

        System.out.println(path);
        Takuzu tak;

        for (int i = 4; i <= 20; i+=2) {
            Takuzu takuzu = Takuzu.load(path + "takuzu_taille_" + i + ".txt");
            Generateur gen = new GenerateurTakuzuFacile(takuzu);
            Generateur gen2 = new GenerateurTakuzuDifficile(takuzu);

            for (int nb = 1; nb <= 30; nb++) {
                tak = gen.generer();
                tak.save(path + "BanqueTakuzu/Taille_" +i+"/takuzuFacile_taille_"+i+"_nb_"+nb+".txt");
                tak.affichage();

                tak = gen2.generer();
                tak.save(path + "BanqueTakuzu/Taille_" +i+"/takuzuDifficile_taille_"+i+"_nb_"+nb+".txt");
                tak.affichage();
            }
        }
    }
}
