package main.IHM;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.Grille;

@SuppressWarnings("Duplicates")
public class IHM extends Application{
    public static void main(String[] args) { Application.launch(IHM.class, args);}

    Grille uneGrille;

    public static void special(){
        String args = "";
        Application.launch(IHM.class, args);}

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTBLUE);
        primaryStage.setScene(scene);


        /*Circle cercle = new Circle();
        cercle.setCenterX(300);//réglage de la position, de la taille et de la couleur du cercle
        cercle.setCenterY(200);
        cercle.setRadius(100);
        cercle.setFill(Color.YELLOW);
        cercle.setStroke(Color.ORANGE);//réglage de la couleur de la bordure et de son épaisseur
        cercle.setStrokeWidth(5);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(300);
        rectangle.setY(200);
        rectangle.setWidth(300);
        rectangle.setHeight(200);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.DARKGREEN);
        rectangle.setStrokeWidth(5);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);*/

        GrilleIHM g = new GrilleIHM();
        root.getChildren().add(g);

        primaryStage.setScene(scene);

        /*root.getChildren().add(cercle);//on ajoute le cercle au groupe root
        root.getChildren().add(rectangle);//On ajoute le rectangle après le cercle*/
        primaryStage.show();
    }
}
