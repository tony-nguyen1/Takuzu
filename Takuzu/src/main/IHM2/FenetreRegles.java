package main.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreRegles extends JFrame{
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";
    private JPanel pan;
    private JLabel label1;

    public FenetreRegles() {
        this.largeurFenetre = 350;
        this.hauteurFenetre = 700;
        pan = new JPanel(); //création d'un panel.
        label1 = new JLabel();
    }

    public void creerFenetre(){
        this.setTitle("Regles");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pan.setBackground(Color.lightGray);
        label1.setText("EXPLICATIONS");
        setContentPane(pan);
        pan.add(label1);
        this.setVisible(true);
    }


}
