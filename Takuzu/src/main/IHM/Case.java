package main.IHM;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Case extends Parent {

    public String lettre;
    private int positionX = 0;//abscisse
    private int positionY = 0;//ordonnée de la touche

    Rectangle uneCase;
    Text lettre_touche;

    public Case(String l, int posX, int posY, int dimensions){
        lettre =  new String(l);
        positionX = posX;
        positionY = posY;

        uneCase = new Rectangle(dimensions,dimensions,Color.WHITE);

        this.getChildren().add(uneCase);//ajout du rectangle de fond de la touche

        lettre_touche = new Text(lettre);
        lettre_touche.setFont(new Font(80));
        lettre_touche.setFill(Color.BLACK);
        lettre_touche.setX(28);
        lettre_touche.setY(80);
        this.getChildren().add(lettre_touche);//ajout de la lettre de la touche

        this.setTranslateX(positionX);//positionnement de la touche sur le clavier
        this.setTranslateY(positionY);
    }

    public Case(int posX, int posY, Color color, int dimensions){
        positionX = posX;
        positionY = posY;

        uneCase = new Rectangle(dimensions,dimensions,color);

        this.getChildren().add(uneCase);//ajout du rectangle de fond de la touche

        this.setTranslateX(positionX);//positionnement de la touche sur le clavier
        this.setTranslateY(positionY);
    }
}
