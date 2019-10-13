package main.IHM;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
        //Group root = new Group();
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 700, 700, Color.LIGHTBLUE);
        primaryStage.setTitle("TakuzuApp");

        Thread thread = new Thread( () ->
        {
            Runnable updater = () -> {
                GrilleIHM g = new GrilleIHM(grille,100,4);
                root.getChildren().add(g); };

            while (true)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }

                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
