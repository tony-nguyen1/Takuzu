package main.IHM;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Case extends Parent {

    private int positionX = 0;//abscisse
    private int positionY = 0;//ordonnée de la touche

    Rectangle uneCase;
    Text lettre_touche;

    public Case(int posX, int posY){
        positionX = posX;
        positionY = posY;

        uneCase = new Rectangle(100,100,Color.WHITE);

        this.getChildren().add(uneCase);//ajout du rectangle de fond de la touche

        this.setTranslateX(positionX);//positionnement de la touche sur le clavier
        this.setTranslateY(positionY);
    }

    public Case(int posX, int posY, Color color){
        positionX = posX;
        positionY = posY;

        uneCase = new Rectangle(100,100,color);

        this.getChildren().add(uneCase);//ajout du rectangle de fond de la touche

        this.setTranslateX(positionX);//positionnement de la touche sur le clavier
        this.setTranslateY(positionY);
    }
}
