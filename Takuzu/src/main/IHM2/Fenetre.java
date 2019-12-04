package main.IHM2;

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
    private JPanel pan; //panel pour la grille
    private JPanel panelboutons; //panel pour le niveau
    private JButton bsolution, undo, bregles, undoJusquaSolution;
    /*private JComboBox jComboBox;
    private Object[] elements = new Object[]{"Facile", "Difficile"};*/
    private int tailleTakuzu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private int fontSize;

    private Deque<Takuzu> DequeTakuzu;

    private String fontName = "Verdana";

    public Fenetre(int tailleTakuzu, int largeurFenetre, int hauteurFenetre) {
        DequeTakuzu = new ArrayDeque<>();

        this.tailleTakuzu = tailleTakuzu;
        this.largeurFenetre = 800;
        this.hauteurFenetre = 800;
        this.fontSize = determineFontSize();

        takuzu = new Takuzu(tailleTakuzu); //création d'un takuzu
        gridLayout = new GridLayout(takuzu.getTailleGrille(), takuzu.getTailleGrille(), 1, 1); //création d'une grille
        pan = new JPanel(gridLayout); //création d'un panel contenant la grille.
        bsolution = new JButton("Solution"); //création d'un bouton solution.
        undo = new JButton("Undo");
        //jComboBox = new JComboBox(elements);
        panelboutons = new JPanel();
        bregles = new JButton("Règles");
    }

    public void creerFenetre(){

        pan.setPreferredSize(new Dimension(largeurFenetre - 125, hauteurFenetre));//le panel pan ne prend qu'une partie du panel de la fenêtre.
        pan.setBackground(Color.lightGray); // modification de couleur du panel
        takuzu.preRemplissage();
        panelboutons.setBackground(Color.darkGray);
        panelboutons.setLayout(null);
        panelboutons.add(bsolution);
        //panelboutons.add(jComboBox);
        panelboutons.add(undo);
        panelboutons.add(bregles);
        bsolution.setBounds(0, hauteurFenetre - 300, 125, 100);
        //jComboBox.setBounds(0, hauteurFenetre - 500, 125, 80);
        undo.setBounds(0, hauteurFenetre - 700, 125, 100);
        bregles.setBounds(0, hauteurFenetre - 500, 125, 100);


        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(largeurFenetre, hauteurFenetre); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge
        //this.getContentPane().setBackground(Color.cyan);
        //pan.setBorder(blackline);
        this.remplirGrille(takuzu); //utilisation de la méthode remplirgrille()
        takuzuBackup = takuzu.cloneTakuzu();
        /*jComboBox.setBackground(Color.lightGray);
        jComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==jComboBox){
                    System.out.println("Ta mère");
                }
                if(e.getSource()=="Difficile"){
                    viderGrille();
                }
            }
        });*/
        bsolution.setBackground(new Color(148,159,230)); //modification de couleur du bouton "solution"
        bsolution.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bsolution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    //takuzu.affichage();
                    viderGrille();
                    //Si ca crash, test backtrackIntelligent
                    boolean resolution = takuzu.seResoudre(new Hypotheses());
                    if (resolution) { //S'il a réussi à résoudre
                        takuzu.affichage();
                        remplirGrille(takuzu);
                    }
                    else {
                        System.out.println("La résolution à partir de ce qui a été fait est un echec, nous allons donc reprendre le takuzu initial");
                        takuzuBackup.seResoudre(new Hypotheses());
                        takuzuBackup.affichage();
                        remplirGrille(takuzuBackup);
                    }
                }
            }
        });
        undo.setBackground(new Color(148,159,230)); //modification de couleur du bouton "solution"
        undo.setFont(new Font(fontName, Font.BOLD, 18));
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() > 0){

                if (!DequeTakuzu.isEmpty()){
                    System.out.println("Undoing");
                    takuzu = DequeTakuzu.poll();
                    viderGrille();
                    remplirGrille(takuzu);
                }
            }
            }});

        bregles.setBackground(new Color(148,159,230)); //modification de couleur du bouton "solution"
        bregles.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bregles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fenetreRegles = new FenetreRegles();
                fenetreRegles.creerFenetre();
            }
        });
        //bsolution.addActionListener(this);
        //Border blackline = BorderFactory.createLineBorder(Color.lightGray,1);
        //panbsol.add(bsolution);

       // this.getContentPane().add(panbsol,BorderLayout.EAST);
        this.getContentPane().add(panelboutons);
        this.getContentPane().add(pan, BorderLayout.WEST); //ajout du panel Pane
        //this.getContentPane().add(bsolution); //ajout du bouton bsolution au Pane de la fenêtre
        this.setVisible(true); //ligne permettant de rendre la fenêtre visible
    }

    public void remplirGrille(Takuzu takuzu) {
        System.out.println(takuzu);
        int value;
        for (int x = 0; x < tailleTakuzu; x++) { //parcours de la grille
            for (int y = 0; y < tailleTakuzu; y++) {
                value = takuzu.getValue(x, y);
                if (value == 0 || value == 1) {
                    JPanel ptest = new JPanel(); //création d'un panel pour chaque case.
                    JLabel label = new JLabel(Integer.toString(value), SwingConstants.CENTER); //création d'un label avec la valeur 1 ou 0.
                    label.setFont(new Font(fontName, Font.BOLD, fontSize)); //modification de la police
                    ptest.add(label); //ajout des labels avec la valeur 1 ou 0 dans les panels ptest
                    ptest.setBackground(Color.WHITE);
                    pan.add(ptest); // ajout de tous les panels avec un 1 ou 0 dans pan.
                }

                else {
                    JButton bouton = new JButton();//création d'un bouton si pas de valeur
                    pan.add(bouton); //ajout du bouton dans le pan
                    bouton.setContentAreaFilled(false);
                    bouton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JLabel label = new JLabel();
                            bouton.setOpaque(true);

                            //Grace a ces formules magiques on trouve la valeur de X et de Y par rapport au Takuzu, on peut donc faire le lien entre le panel et le takuzu désormais
                            int boutonX = bouton.getX()/(pan.getSize().width/tailleTakuzu);
                            int boutonY =  bouton.getY()/(pan.getSize().height/tailleTakuzu);
                            int i = 0;

                            if (e.getClickCount()%2 == 1) {
                                label.setText("0");
                                label.setFont(new Font(fontName, Font.BOLD, fontSize));
                                takuzu.play0(boutonY, boutonX);

                            }
                            else {
                                label.setText("1");
                                label.setFont(new Font(fontName, Font.BOLD, fontSize));
                                takuzu.play1(boutonY, boutonX);
                            }
                            bouton.setText(label.getText());
                            bouton.setFont(label.getFont());


                            if (!takuzu.estValide()){
                                bouton.setBackground(Color.red);
                            }

                            else {
                                //C'est juste pour déterminer les bonnes cases et les recolorier
                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    int ligne, colonne;
                                    //C'est pour faire le lien entre la grille et le takuzu afin de colorier UNIQUEMENT les cases dont le joueur a joué dessus
                                    ligne = z/tailleTakuzu;
                                    colonne = z%tailleTakuzu;
                                    if (takuzuBackup.getValue(ligne, colonne) == -1){
                                        pan.getComponent(z).setBackground(Color.lightGray);
                                    }
                                }

                                DequeTakuzu.add(takuzu.cloneTakuzu());
                            }


                            if (takuzu.estGagnant()){
                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    pan.getComponent(z).setBackground(Color.orange);
                                }
                            }

                        }
                    });

                }

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
        if (tailleTakuzu == 6)
            return 75;
        else if (tailleTakuzu == 8)
            return 60;
        else if (tailleTakuzu == 10)
            return 45;
        else if (tailleTakuzu == 12)
            return 35;
        else if (tailleTakuzu == 14)
            return 30;
        else if (tailleTakuzu == 16)
            return 25;
        else return 10;
    }

    public int getLargeurFenetre(){
        return largeurFenetre;
    }

    public int getHauteurFenetre(){
        return hauteurFenetre;
    }

}
