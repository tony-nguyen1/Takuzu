package main.IHM;

import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class GrilleIHM extends Parent {

    //private Case[] cases;
    private LinkedList<Case> cases;

    public GrilleIHM() {
        cases = new LinkedList<>();
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(420);
        fond_clavier.setHeight(420);

        this.setTranslateX(50);
        this.setTranslateY(00);
        this.getChildren().add(fond_clavier);
        for (Case c: cases){
            this.getChildren().add(c);
        }
    }

    public GrilleIHM(int[][] grille, int dimensionsCase, int pas)
    {
        cases = new LinkedList<>();
        int tailleDeLaGrille = dimensionsCase * grille.length + (grille.length+1) * pas;

        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(tailleDeLaGrille);
        fond_clavier.setHeight(tailleDeLaGrille);

        this.getChildren().add(fond_clavier);

        //////////////////////////////
        createCase(grille, 420,4,dimensionsCase);
    }

    public void createCase(int[][] grille, int taille, int pas, int dimensionsCase)
    {
        for (int i = 0; i < grille.length; i++)
        {
            for (int j = 0; j < grille[i].length; j++)
            {
                if (grille[i][j] == 0) //la case a une valeur
                {
                    cases.add(new Case(pas*(j+1)+j*dimensionsCase,pas*(i+1)+i*dimensionsCase,Color.YELLOW,dimensionsCase));
                }
                if (grille[i][j] == 1) //la case a une valeur
                {
                    cases.add(new Case(pas*(j+1)+j*dimensionsCase,pas*(i+1)+i*dimensionsCase,Color.RED,dimensionsCase));
                }
            }
        }

        for (Case c: cases){
            this.getChildren().add(c);
        }
    }

    public void createCaseLettres(int[][] grille, int taille, int pas, int dimensionsCase)
    {
        for (int i = 0; i < grille.length; i++)
        {
            for (int j = 0; j < grille[i].length; j++)
            {
                if (grille[i][j] == 0) //la case a une valeur
                {
                    cases.add(new Case("0",pas*(j+1)+j*dimensionsCase,pas*(i+1)+i*dimensionsCase,100));
                }
                if (grille[i][j] == 1) //la case a une valeur
                {
                    cases.add(new Case("1",pas*(j+1)+j*dimensionsCase,pas*(i+1)+i*dimensionsCase,100));
                }
            }
        }

        for (Case c: cases){
            this.getChildren().add(c);
        }
    }

}
