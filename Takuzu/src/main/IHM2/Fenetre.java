package main.IHM2;

import main.Grille;
import main.Takuzu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;

public class Fenetre extends JFrame {

        JPanel pan = new JPanel();

        public Fenetre() {

            this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
            this.setSize(600, 600); // dimension fenêtre
            this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge
            //this.setContentPane(pan); //le content pane est un objet Panneau
            GridLayout gridLayout = new GridLayout (6,6, 1,1);
            JPanel pan = new JPanel(gridLayout);
            pan.setBackground(Color.black);
            Border blackline = BorderFactory.createLineBorder(Color.black,1);
            Takuzu takuzu = new Takuzu(6);
            takuzu.preRemplissage();
            for(int x=0; x<takuzu.getHeightGrille(); x++){
                for(int y=0; y<takuzu.getWidthGrille(); y++){
                    if(takuzu.getValue(x,y)==1){
                         JPanel ptest = new JPanel();
                         ptest.setBackground(Color.yellow);
                         pan.add(ptest);
                    }
                    if(takuzu.getValue(x,y)==0){
                         JPanel ptest = new JPanel();
                         ptest.setBackground(Color.red);
                         pan.add(ptest);
                    }
                    if(takuzu.getValue(x,y)==-1){
                        JButton bouton = new JButton();
                        pan.add(bouton);
                        bouton.setContentAreaFilled(false);
                    }

                }
            }
            pan.setBorder(blackline);
            this.add(pan);
            this.setVisible(true);
        }
    }


