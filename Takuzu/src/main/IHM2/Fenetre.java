package main.IHM2;

import javafx.event.Event;
import javafx.scene.text.Text;
import main.Grille;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Fenetre extends JFrame{


    private Takuzu takuzu = new Takuzu(6);
    private GridLayout gridLayout = new GridLayout(6, 6, 1, 1);
    private JPanel pan = new JPanel(gridLayout);
    private JButton bsolution = new JButton("Solution");

    public Fenetre() {

        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(725, 600); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge
        //this.getContentPane().setBackground(Color.cyan);
        pan.setPreferredSize(new Dimension(600, 600));
        pan.setBackground(Color.lightGray);
        bsolution.setBackground(Color.orange);
        bsolution.setFont(new Font("Serif", Font.BOLD, 18));
        //bsolution.addActionListener(this);
        //Border blackline = BorderFactory.createLineBorder(Color.lightGray,1);
        takuzu.preRemplissage();
        //pan.setBorder(blackline);
        this.remplirGrille();
        this.getContentPane().add(bsolution);
        this.getContentPane().add(pan, BorderLayout.WEST);
        this.setVisible(true);
    }

    public void remplirGrille() {
        for (int x = 0; x < takuzu.getHeightGrille(); x++) {
            for (int y = 0; y < takuzu.getWidthGrille(); y++) {
                if (takuzu.getValue(x, y) == 1) {
                    JPanel ptest = new JPanel();
                    JLabel label = new JLabel("1", SwingConstants.CENTER);
                    label.setFont(new Font("Serif", Font.BOLD, 75));
                    ptest.add(label);
                    //ptest.setBackground(Color.yellow);
                    pan.add(ptest);
                }

                if (takuzu.getValue(x, y) == 0) {
                    JPanel ptest = new JPanel();
                    JLabel label = new JLabel("0");
                    label.setFont(new Font("Serif", Font.BOLD, 75));
                    ptest.add(label);
                    //ptest.setBackground(Color.red);
                    //ptest.setSize(this.getWidth()-(this.getWidth()-this.getHeight())/takuzu.getWidthGrille(), this.getHeight()/takuzu.getHeightGrille());
                    pan.add(ptest);
                }

                if (takuzu.getValue(x, y) == -1) {
                    JButton bouton = new JButton();
                    pan.add(bouton);
                    bouton.setContentAreaFilled(false);

                }
            }
        }
    }


}












