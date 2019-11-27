package main.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Fenetreaccueil extends JFrame{
    private Fenetre fen;
    private JButton bouton1;
    private JPanel pan;
    private int largeurFenetre;
    private int hauteurFenetre;

    public Fenetreaccueil(int largeurFenetre, int hauteurFenetre){
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        pan = new JPanel(); //création d'un panel contenant la grille.
        bouton1 = new JButton("Jouer"); //création d'un bouton solution.

    }

    public void creerFenetre(){
        pan.setLayout(null);
        this.setTitle("Bienvenue sur le jeu du Takuzu");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        bouton1.setBackground(Color.lightGray);
        bouton1.setBounds(200,150, 300, 200);
        bouton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fen = new Fenetre(8,largeurFenetre,hauteurFenetre);
                fen.creerFenetre();
            }
        });
        pan.add(bouton1);


        setContentPane(pan);
        this.setVisible(true);
    }
}
