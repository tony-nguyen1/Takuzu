package main.IHM2;


import main.Case;
import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Fenetre extends JFrame{


    private Takuzu takuzu;
    private Takuzu takuzuBackup;
    private GridLayout gridLayout;
    private FenetreRegles fenetreRegles;
    private FenetreTaille fenetreTaille;
    private JPanel pan; //panel pour la grille
    private JPanel panelboutons; //panel pour le niveau
    private JButton bsolution, undo, bregles, bniveau, undoJusquaSolution;
    /*private JComboBox jComboBox;
    private Object[] elements = new Object[]{"Facile", "Difficile"};*/
    private int tailleTakuzu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private int fontSize;
    private Boolean nonResolu = true;

    private Deque<Takuzu> DequeTakuzu;
    private Deque<Case> DequeCaseJoue;


    private String fontName = "Corbel";

    public Fenetre(Takuzu t) {
        DequeTakuzu = new ArrayDeque<>();
        DequeCaseJoue = new ArrayDeque<>();
        this.largeurFenetre = 800;
        this.hauteurFenetre = 800;

        tailleTakuzu = t.getTailleGrille();
        fontSize = determineFontSize();

        gridLayout = new GridLayout(tailleTakuzu, tailleTakuzu); //création d'une grille
        pan = new JPanel(gridLayout); //création d'un panel contenant la grille.
        bsolution = new JButton("Solution"); //création d'un bouton solution.
        undo = new JButton("Undo");
        bniveau = new JButton("Niveau");
        panelboutons = new JPanel();
        bregles = new JButton("Règles");

        takuzu = t;
        takuzuBackup = t.cloneTakuzu();
    }


    public void creerFenetreParametre(){

        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(largeurFenetre, hauteurFenetre); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge

        pan.setPreferredSize(new Dimension(largeurFenetre - 140, hauteurFenetre));//le panel pan ne prend qu'une partie du panel de la fenêtre.
        pan.setBackground(Color.lightGray); // modification de couleur du panel

        panelboutons.setBackground(Color.darkGray);
        panelboutons.setLayout(null);
        panelboutons.add(bsolution);
        panelboutons.add(undo);
        panelboutons.add(bregles);
        panelboutons.add(bniveau);

        bsolution.setBounds(0, hauteurFenetre - 200, 125, 100);
        bregles.setBounds(0, hauteurFenetre - 400, 125, 100);
        undo.setBounds(0, hauteurFenetre - 600, 125, 100);
        bniveau.setBounds(0, 0, 125, 100);
    }

    public void creerBouton(){
        //Bouton solution
        bsolution.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bsolution.setFont(new Font(fontName, Font.BOLD, 20)); //modification de la police du bouton "solution"
        bsolution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    //takuzu.affichage();
                    viderGrille();

                    boolean resolution = takuzu.seResoudre(new Hypotheses());
                    nonResolu = false;

                    if (resolution) { //S'il a réussi à résoudre
                        creerGrille();

                    } else {
                        System.out.println("La résolution à partir de ce qui a été fait est un echec, nous allons donc reprendre le takuzu initial");
                        Takuzu takuzuBackupBis = takuzuBackup.cloneTakuzu();
                        takuzuBackupBis.seResoudre(new Hypotheses());
                        takuzu = takuzuBackupBis;
                        creerGrille();

                    }
                }
            }
        });

        //Bouton undo
        undo.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        undo.setFont(new Font(fontName, Font.BOLD, 20));
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {

                    if (nonResolu){
                        if (!DequeTakuzu.isEmpty()) {
                            takuzu = DequeTakuzu.pollLast();
                            remplirGrilleDifference(takuzu);
                        }
                        colorieGrisCaseValide();
                    }

                }
            }
        });

        //Bouton des règles
        bregles.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bregles.setFont(new Font(fontName, Font.BOLD, 20)); //modification de la police du bouton "solution"
        bregles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fenetreRegles = new FenetreRegles();
                fenetreRegles.creerFenetre();
            }
        });

        //Bouton de la selection de niveau
        bniveau.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bniveau.setFont(new Font(fontName, Font.BOLD, 20)); //modification de la police du bouton "solution"
        bniveau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
                fenetreTaille = new FenetreTaille();
                fenetreTaille.creerFenetre();
            }
        });

        this.getContentPane().add(panelboutons);
        this.getContentPane().add(pan, BorderLayout.WEST); //ajout du panel Pane

    }

    public void creerFenetre() {
        creerFenetreParametre();
        creerBouton();
        this.creerGrille();
        this.setVisible(true); //ligne permettant de rendre la fenêtre visible

    }

    public void creerGrille() {
        int value;
        for (int x = 0; x < tailleTakuzu; x++) { //parcours de la grille
            for (int y = 0; y < tailleTakuzu; y++) {
                value = takuzu.getValue(x, y);
                JButton ptest = new JButton(); //création d'un bouton pour chaque case.
                ptest.setLayout(new FlowLayout());



                if (value == 0 || value == 1) {
                    JLabel label = new JLabel(Integer.toString(value), SwingConstants.CENTER); //création d'un label avec la valeur 1 ou 0.
                    label.setFont(new Font("Verdana", Font.BOLD, fontSize)); //modification de la police
                    ptest.add(label); //ajout des labels avec la valeur 1 ou 0 dans les panels ptest
                    ptest.setBackground(Color.WHITE);
                }

                else{
                    ptest.setContentAreaFilled(false);
                    JLabel label = new JLabel(""); //création d'un label avec la valeur 1 ou 0.
                    label.setFont(new Font(fontName, Font.BOLD, fontSize)); //modification de la police

                    ptest.add(label); //ajout des labels avec la valeur 1 ou 0 dans les panels ptest


                    ptest.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            ptest.setOpaque(true);
                            //On sauvegarde le takuzu afin de l'ajouter dans la pile (pour undo)
                            Takuzu takuzuASauvegarder = takuzu.cloneTakuzu();

                            //Grace a ces formules magiques on trouve la valeur de X et de Y par rapport au Takuzu, on peut donc faire le lien entre le panel et le takuzu désormais
                            int boutonX = ptest.getX() / (pan.getSize().width / tailleTakuzu);
                            int boutonY = ptest.getY() / (pan.getSize().height / tailleTakuzu);

                            //Si on clique une fois on joue un 0, si on reclique on joue un 1
                            if (e.getClickCount() >= 1) {

                                if (nonResolu) {

                                    JLabel labelus = (JLabel)ptest.getComponent(0);
                                    String value = labelus.getText();

                                    if (value.equals("") || value.equals("1")) {
                                        labelus.setText("0");
                                        takuzu.play0(boutonY, boutonX);
                                    } else {
                                        labelus.setText("1");
                                        takuzu.play1(boutonY, boutonX);
                                    }

                                    ptest.add(labelus);
                                    DequeTakuzu.add(takuzuASauvegarder);
                                    DequeCaseJoue.add(new Case(boutonY, boutonX));
                                }
                            }

                            //Colorie en rouge la case non valide
                            if (!takuzu.estCaseValide(boutonY, boutonX)){
                                ptest.setBackground(Color.RED);
                            }

                            //Colorie en gris sinon
                            else {
                                ptest.setBackground(Color.lightGray);
                            }

                            //Faire un stack de case, foutre les cases joué dessus et vérifier chacune d'entre elle à chaque move pour les recolorier et retirer celles qui ont
                            //été supprimé avec un "undo" / ou les supprimer si un bouton solution a été joué

                            colorieGrisCaseValide();

                            //Colorie en orange si le takuzu est gagnant
                            if (takuzu.estGagnant()){
                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    pan.getComponent(z).setBackground(Color.orange);
                                    nonResolu = false;
                                }
                            }

                        }
                    });

                }


                //Ajout du bouton à chaque fin d'itération d'une boucle

                pan.add(ptest);
            }
        }
    }

    /**
     *
     * @param t le takuzu qui va servir d'exemple
     * La fonction va changer l'affichage de la grille de sorte à ce qu'il corresponde à celui du takuzu t
     */
    public void remplirGrilleDifference(Takuzu t) {
        int value;
        int compteurComposant = 0;


        for (int x = 0; x < tailleTakuzu; x++) { //parcours de la grille
            for (int y = 0; y < tailleTakuzu; y++) {



                value = t.getValue(x, y);

                JButton bouton = (JButton)pan.getComponent(compteurComposant);
                JLabel labelus = (JLabel)bouton.getComponent(0);

                if ((value == 0 || value == 1) && takuzuBackup.getValue(x, y) == -1) {
                    labelus.setText(String.valueOf(value));
                }

                if ((value == -1)){
                    labelus.setText("");
                }


                bouton.add(labelus);


                compteurComposant++;
            }
        }

    }


    /**
     * Fonction qui sert à colorier en grille toutes les cases valides
     */
    private void colorieGrisCaseValide() {
        for (int z = 0; z < tailleTakuzu * tailleTakuzu; z++) {
            int ligne, colonne;
            //C'est pour faire le lien entre la grille et le takuzu afin de colorier UNIQUEMENT les cases dont le joueur a joué dessus
            ligne = z / tailleTakuzu;
            colonne = z % tailleTakuzu;
            if (takuzuBackup.getValue(ligne, colonne) == -1) {
                if (takuzu.estCaseValide(ligne, colonne)) {
                    pan.getComponent(z).setBackground(Color.lightGray);
                }
            }
            if (takuzu.getValue(ligne, colonne) == -1){
                pan.getComponent(z).setBackground(Color.lightGray);
            }
        }
    }

    public void viderGrille() {
        pan.removeAll();
        pan.revalidate();
        pan.repaint();
    }

    public JPanel getPan(){
        return this.pan;
    }

    private int determineFontSize(){
        if (tailleTakuzu == 4)
            return 120;
        if (tailleTakuzu == 6)
            return 80;
        else if (tailleTakuzu == 8)
            return 60;
        else if (tailleTakuzu == 10)
            return 45;
        else if (tailleTakuzu == 12)
            return 30;
        else if (tailleTakuzu == 14)
            return 30;
        else if (tailleTakuzu == 16)
            return 20;
        else if (tailleTakuzu == 18)
            return 15;

        else return 15;
    }

    public int getLargeurFenetre(){
        return largeurFenetre;
    }

    public int getHauteurFenetre(){
        return hauteurFenetre;
    }
    public Takuzu getTakuzu(){
        return takuzu;
    }
}
