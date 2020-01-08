package main.IHM2;

import main.Generateur.Facile.GenerateurTakuzuFacile;
import main.Generateur.Generateur;
import main.Takuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreTaille extends JFrame{

    private FenetreNiveau fenniv;
    private JPanel pan;
    private JButton taille1;
    private JButton taille2;
    private JButton taille3;
    private JButton taille4;
    private JButton taille5;
    private JButton taille6;
    private JButton taille7;
    private JButton taille8;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";

    public FenetreTaille() {
        this.largeurFenetre = 400;
        this.hauteurFenetre = 450;
        pan = new JPanel();
        taille1 = new JButton("4x4"); //création d'un bouton 4 par 4.
        taille2 = new JButton("6x6"); //création d'un bouton 6 par 6.
        taille3 = new JButton("8x8"); //création d'un bouton 8 par 8
        taille4 = new JButton("10x10"); //création d'un bouton 10 par 10
        taille5 = new JButton("12x12"); //création d'un bouton 12 par 12
        taille6 = new JButton("14x14"); //création d'un bouton 14 par 14
        taille7 = new JButton("16x16"); //création d'un bouton 16 par 16
        taille8 = new JButton("18x18"); //création d'un bouton 18 par 18
    }

    public void creerFenetre(){

        pan.setLayout(null);
        this.setTitle("Taille");
        this.setSize(largeurFenetre,hauteurFenetre);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pan.setBackground(Color.lightGray);
        taille1.setBackground(Color.white);
        //niveau1.setBounds(120,hauteurFenetre - 300, 150, 150);
        taille1.setFont(new Font(fontName, Font.BOLD, 40));
        taille1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille1.setBounds(0, 0, 200, 100);
        taille1.setFocusPainted(false);
        taille1.setBorderPainted(false);
        taille1.setContentAreaFilled(false);
        taille1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(4);
                f.creerFenetre();

            }
        });

        taille2.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille2.setFont(new Font(fontName, Font.BOLD, 40));
        taille2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille2.setBounds(0,100, 200, 100);
        taille2.setFocusPainted(false);
        taille2.setBorderPainted(false);
        taille2.setContentAreaFilled(false);
        taille2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(6);
                f.creerFenetre();
            }
        });
        taille3.setBackground(Color.white);
        //niveau3.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille3.setFont(new Font(fontName, Font.BOLD, 40));
        taille3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille3.setBounds(0, 200, 200, 100);
        taille3.setFocusPainted(false);
        taille3.setBorderPainted(false);
        taille3.setContentAreaFilled(false);
        taille3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(8);
                f.creerFenetre();
            }


        });
        taille4.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille4.setFont(new Font(fontName, Font.BOLD, 40));
        taille4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille4.setBounds(0,300, 200, 100);
        taille4.setFocusPainted(false);
        taille4.setBorderPainted(false);
        taille4.setContentAreaFilled(false);
        taille4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(10);
                f.creerFenetre();
            }
        });

        taille5.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille5.setFont(new Font(fontName, Font.BOLD, 40));
        taille5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille5.setBounds(200,0, 200, 100);
        taille5.setFocusPainted(false);
        taille5.setBorderPainted(false);
        taille5.setContentAreaFilled(false);
        taille5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(12);
                f.creerFenetre();
            }
        });

        taille6.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille6.setFont(new Font(fontName, Font.BOLD, 40));
        taille6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille6.setBounds(200,100, 200, 100);
        taille6.setFocusPainted(false);
        taille6.setBorderPainted(false);
        taille6.setContentAreaFilled(false);
        taille6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(14);
                f.creerFenetre();
            }
        });
        taille7.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille7.setFont(new Font(fontName, Font.BOLD, 40));
        taille7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille7.setBounds(200,200, 200, 100);
        taille7.setFocusPainted(false);
        taille7.setBorderPainted(false);
        taille7.setContentAreaFilled(false);
        taille7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(16);
                f.creerFenetre();
            }
        });
        taille8.setBackground(Color.white);
        //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
        taille8.setFont(new Font(fontName, Font.BOLD, 40));
        taille8.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taille8.setBounds(200,300, 200, 100);
        taille8.setFocusPainted(false);
        taille8.setBorderPainted(false);
        taille8.setContentAreaFilled(false);
        taille8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                FenetreNiveau f = new FenetreNiveau(18);
                f.creerFenetre();
            }
        });
        setContentPane(pan);
        pan.add(taille1);
        pan.add(taille2);
        pan.add(taille3);
        pan.add(taille3);
        pan.add(taille4);
        pan.add(taille5);
        pan.add(taille6);
        pan.add(taille7);
        pan.add(taille8);
        this.setVisible(true);
    }
}
