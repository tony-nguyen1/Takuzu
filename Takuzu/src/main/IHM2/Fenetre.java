package main.IHM2;

import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre extends JFrame{


    private Takuzu takuzu = new Takuzu(6); //création d'un takuzu de taille 6 par 6
    private GridLayout gridLayout = new GridLayout(takuzu.getHeightGrille(), takuzu.getWidthGrille(), 1, 1); //création d'une grille
    private JPanel pan = new JPanel(gridLayout); //création d'un panel contenant la grille.
    private JButton bsolution = new JButton("Solution"); //création d'un bouton solution.

    public Fenetre() {

        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(725, 600); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge
        //this.getContentPane().setBackground(Color.cyan);
        pan.setPreferredSize(new Dimension(600, 600));//le panel pan  ne prend qu'une partie du panel de la fenêtre, ici 600 par 600.
        pan.setBackground(Color.lightGray); // modification de couleur du panel
        takuzu.preRemplissage();
        //pan.setBorder(blackline);
        this.remplirGrille(); //utilisation de la méthode remplirgrille()
        bsolution.setBackground(Color.orange); //modification de couleur du bouton "solution"
        bsolution.setFont(new Font("Serif", Font.BOLD, 18)); //modification de la police du bouton "solution"
        bsolution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    viderGrille();
                    takuzu.seResoudre(new MaitreSolveur());
                    for (int x = 0; x <= takuzu.getHeightGrille(); x++) { //parcours de la grille
                        for (int y = 0; y <= takuzu.getWidthGrille(); y++) {
                            setContentPane(pan);
                            if (takuzu.getValue(x, y) == 1) {
                                JPanel ptest = new JPanel(); //création d'un panel pour chaque case.
                                JLabel label = new JLabel("1", SwingConstants.CENTER); //création d'un label avec la valeur 1.
                                label.setFont(new Font("Serif", Font.BOLD, 75)); //modification de la police
                                ptest.add(label); //ajout des labels avec la valeur 1 dans les panels ptest
                                //ptest.setBackground(Color.yellow);
                                pan.add(ptest);
                            }
                            if (takuzu.getValue(x, y) == 0) {
                                JPanel ptest = new JPanel(); //création d'un panel pour chaque case.
                                JLabel label = new JLabel("0", SwingConstants.CENTER); //création d'un label avec la valeur 1.
                                label.setFont(new Font("Serif", Font.BOLD, 75)); //modification de la police
                                ptest.add(label); //ajout des labels avec la valeur 1 dans les panels ptest
                                //ptest.setBackground(Color.yellow);
                                pan.add(ptest);
                            }
                        }
                    }
                }
            }
        });
        //bsolution.addActionListener(this);
        //Border blackline = BorderFactory.createLineBorder(Color.lightGray,1);
        this.getContentPane().add(bsolution); //ajout du bouton bsolution au Pane de la fenêtre
        this.getContentPane().add(pan, BorderLayout.WEST); //ajout du panel Pane
        this.setVisible(true); //ligne permettant de rendre la fenêtre visible
    }

    public void remplirGrille() {
        for (int x = 0; x < takuzu.getHeightGrille(); x++) { //parcours de la grille
            for (int y = 0; y < takuzu.getWidthGrille(); y++) {
                if (takuzu.getValue(x, y) == 1) {
                    JPanel ptest = new JPanel(); //création d'un panel pour chaque case.
                    JLabel label = new JLabel("1", SwingConstants.CENTER); //création d'un label avec la valeur 1.
                    label.setFont(new Font("Serif", Font.BOLD, 75)); //modification de la police
                    ptest.add(label); //ajout des labels avec la valeur 1 dans les panels ptest
                    //ptest.setBackground(Color.yellow);
                    pan.add(ptest); // ajout de tous les panels avec un 1 dans pan.
                }

                if (takuzu.getValue(x, y) == 0) {
                    JPanel ptest = new JPanel();
                    JLabel label = new JLabel("0"); //création d'un label avec la valeur 0.
                    label.setFont(new Font("Serif", Font.BOLD, 75));
                    ptest.add(label);//ajout des labels avec la valeur 1 dans les panels ptest
                    //ptest.setBackground(Color.red);
                    //ptest.setSize(this.getWidth()-(this.getWidth()-this.getHeight())/takuzu.getWidthGrille(), this.getHeight()/takuzu.getHeightGrille());
                    pan.add(ptest);// ajout de tous les panels avec un 0 dans pan.
                }

                if (takuzu.getValue(x, y) == -1) {
                    JButton bouton = new JButton();//création d'un bouton si pas de valeur
                    pan.add(bouton); //ajout du bouton dans le pan
                    bouton.setContentAreaFilled(false);
                    bouton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JLabel label = new JLabel();
                            bouton.setText(label.getText());
                            if (e.getClickCount() == 1) {
                                label.setText("0");
                                label.setFont(new Font("Serif", Font.BOLD, 75));
                                bouton.setText(label.getText());
                                bouton.setFont(label.getFont());
                            }
                            if (e.getClickCount() == 2) {
                                label.setText("1");
                                label.setFont(new Font("Serif", Font.BOLD, 75));
                                bouton.setText(label.getText());
                                bouton.setFont(label.getFont());

                            }

                        }


                    });

                }

            }
        }
    }

    public void viderGrille() {
        for (int x = 0; x < takuzu.getHeightGrille(); x++) { //parcours de la grille
            for (int y = 0; y < takuzu.getWidthGrille(); y++) {
                remplirGrille();
                if (takuzu.getValue(x, y) == 1) {
                    getContentPane();
                    pan.removeAll();
                }

                if (takuzu.getValue(x, y) == 0) {
                    getContentPane();
                    pan.removeAll();
                }

                if (takuzu.getValue(x, y) == -1) {
                    getContentPane();
                    pan.removeAll();

                }

            }
        }
    }

    public JPanel getPan(){
        return this.pan;
    }

}













