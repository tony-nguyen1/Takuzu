package main.IHM;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class IHM extends Application{
    public static void main(String[] args) { Application.launch(IHM.class, args);}

    static int[][] grille;

    public static void special(int[][] g){
        grille = g;
        String args = "";
        Application.launch(IHM.class, args);}

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700, Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TakuzuApp");

        GrilleIHM g = new GrilleIHM(grille,100,4);
        root.getChildren().add(g);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
