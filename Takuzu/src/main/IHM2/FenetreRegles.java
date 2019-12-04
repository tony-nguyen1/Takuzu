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
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;

    public FenetreRegles() {
        this.largeurFenetre = 400;
        this.hauteurFenetre = 520;
        pan = new JPanel(); //création d'un panel.
        label1 = new JLabel("EXPLICATIONS: ");
        label2 = new JLabel("<html>Les règles du jeu pour résoudre un takuzu sont les suivantes:</html>");
        label3 = new JLabel("1. Une case possède un 0 ou un 1");
        label4 = new JLabel("2. Pas plus de 2 numéros identiques à la suite");
        label5 = new JLabel("3. Chaque ligne ou colonne contient autant de 0 que de 1");
        label6 = new JLabel("4. Chaque ligne ou colonne possède un série de chiffre unique");
        label7 = new JLabel("5. Un nombre égal de 0 et de 1 une fois la grille remplie");
        label8 = new JLabel("UTILISATION:");
        label9 = new JLabel("Cliquez 1 fois sur la case pour afficher un 0");
        label10 = new JLabel("Cliquez 2 fois sur la case pour afficher un 1");
        label11 = new JLabel("Cliquez sur le bouton UNDO pour revenir au coup précédent");
    }

    public void creerFenetre(){
        this.setTitle("Règles");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        label1.setFont(new Font(fontName, Font.BOLD, 25));
        label8.setFont(new Font(fontName, Font.BOLD, 25));
        label2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        label3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label5.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label6.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label7.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label8.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        label9.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label10.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        setContentPane(pan);
        pan.setBackground(new Color(245,230,77));
        pan.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan.add(label1);
        pan.add(label2);
        pan.add(label3);
        pan.add(label4);
        pan.add(label5);
        pan.add(label6);
        pan.add(label7);
        pan.add(label8);
        pan.add(label9);
        pan.add(label10);
        pan.add(label11);
        this.setVisible(true);
    }


}
