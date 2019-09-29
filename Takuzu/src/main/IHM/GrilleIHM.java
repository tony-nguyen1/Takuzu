package main.IHM;

import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class GrilleIHM extends Parent {

    private Case[] cases;

    public GrilleIHM() {
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(420);
        fond_clavier.setHeight(420);
        fond_clavier.setArcWidth(0);
        fond_clavier.setArcHeight(0);
        fond_clavier.setFill( //on remplie notre rectangle avec un dégradé
                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                        new Stop[] {
                                new Stop(0, Color.web("#333333")),
                                new Stop(1, Color.web("#000000"))
                        }
                )
        );
        Reflection r = new Reflection();//on applique un effet de réflection
        r.setFraction(0.25);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.5);
        fond_clavier.setEffect(r);

        cases = new Case[]{
                //1er ligne
                new Case(04,4),
                new Case(108,4),
                new Case(212,4, Color.ORANGE),
                new Case(316,4, Color.AQUAMARINE),

                //2e ligne
                new Case(04,108),
                new Case(108,108),
                new Case(212,108),
                new Case(316,108),

                //3e ligne
                new Case(04,212),
                new Case(108,212),
                new Case(212,212),
                new Case(316,212),

                //4e ligne
                new Case(04,316),
                new Case(108,316),
                new Case(212,316),
                new Case(316,316),
        };

        this.setTranslateX(50);
        this.setTranslateY(00);
        this.getChildren().add(fond_clavier);
        for (Case c: cases){
            this.getChildren().add(c);
        }
    }

}
