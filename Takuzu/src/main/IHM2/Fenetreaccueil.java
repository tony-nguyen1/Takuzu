package main.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Fenetreaccueil extends JFrame{
    private Fenetre fen;
    private JButton boutonjouer;
    private JButton boutonquitter;
    private JPanel pan;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";

    public Fenetreaccueil(int largeurFenetre, int hauteurFenetre){
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        pan = new JPanel(); //création d'un panel.
        boutonjouer = new JButton("Jouer"); //création d'un bouton jouer.
        boutonquitter = new JButton("Quitter"); //création d'un bouton quitter.

    }

    public void creerFenetre(){
        pan.setLayout(null);
        this.setTitle("Bienvenue sur le jeu du Takuzu");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pan.setBackground(Color.lightGray);
        boutonjouer.setBackground(Color.yellow);
        boutonjouer.setBounds(200,150, 300, 200);
        boutonjouer.setFont(new Font(fontName, Font.BOLD, 18));
        boutonjouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fen = new Fenetre(8,largeurFenetre,hauteurFenetre);
                fen.creerFenetre();
            }
        });
        boutonquitter.setBackground(Color.yellow);
        boutonquitter.setBounds(400,300, 300, 200);
        boutonquitter.setFont(new Font(fontName, Font.BOLD, 18));
        boutonquitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        pan.add(boutonjouer);
        pan.add(boutonquitter);


        setContentPane(pan);
        this.setVisible(true);
    }
}
