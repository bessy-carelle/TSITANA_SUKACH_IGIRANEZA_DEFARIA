/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mycompany.projetpatron.Controller.Command.GestionnaireCommandes;
import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import com.mycompany.projetpatron.Controller.VueControlleurState;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Strategy.FormeJoueurStrategy;
import com.mycompany.projetpatron.Model.Strategy.ScoreParSurface;


/**
 *
 * @author tsitana251
 */
public class FenetreGlobale extends JFrame implements ActionListener{
    public static final int DIM = 600;
    public VueControlleurJeu controlleurJeu;
    private JButton bouttonCercle;
    private JButton bouttonRectangle;
    private JButton bouttonUndo;      
    private JButton bouttonRedo;      
    private JButton bouttonValider;   
    private JButton bouttonQuitter;   
    private JLabel drawArea;
    private JLabel labelScore; 
    private JeuFormes jeu;
    private int borneMaxX;
    private int borneMaxY;
    private JButton bouttonMode2J;
    private FormeJoueurStrategy strategieJoueur1 = new FormeJoueurStrategy();
    private boolean modeDeuxJoueursActif = false;

    public VueJeu2D vueJeu2D;
    public VueControlleurState etatCreationCercle;
    public VueControlleurState etatCreationRectangle;
    public VueControlleurState etatSuppressionForme;
    public VueControlleurState etatDeplacementForme;
    
    
    public FenetreGlobale(VueControlleurJeu controlleurJeu, VueControlleurState etatCreationCercle, VueControlleurState etatCreationRectangle,JeuFormes jeu){
        this.controlleurJeu = controlleurJeu;
        this.etatCreationCercle = etatCreationCercle;
        this.etatCreationRectangle = etatCreationRectangle;
        this.jeu = jeu;
        JFrame frame = new JFrame("panel");
        JPanel ButtonContainer = new JPanel(); 
        labelScore = new JLabel("Espace libre rempli : 0%");
        labelScore.setForeground(Color.BLUE);
        ButtonContainer.add(labelScore);

        this.vueJeu2D = new VueJeu2D((EtatCreationCercle) etatCreationCercle,(EtatCreationRectangle) etatCreationRectangle,jeu);

        JPanel drawArea = new JPanel();
        drawArea.setPreferredSize(new Dimension(DIM, DIM-100)); 
        drawArea.setBackground(Color.WHITE); 
        drawArea.setBorder(BorderFactory.createLineBorder(Color.black));
        drawArea.setLayout(new BorderLayout());
        drawArea.setOpaque(true);
        drawArea.add(this.vueJeu2D, BorderLayout.CENTER); 
        //frame.add(drawArea, BorderLayout.SOUTH);
        frame.add(drawArea, BorderLayout.CENTER);
        
        GestionnaireCommandes.getInstance().setJeu(jeu);
        
        drawArea.addMouseListener(controlleurJeu);
        drawArea.addMouseMotionListener(controlleurJeu); 
        bouttonCercle = new JButton("Cercle");
        bouttonRectangle = new JButton("Rectangle");    
        ButtonContainer.add(bouttonCercle);
        ButtonContainer.add(bouttonRectangle);
        bouttonRectangle.addActionListener(this);
        bouttonCercle.addActionListener(this);    
        
        frame.add(ButtonContainer, BorderLayout.NORTH);       
        
        bouttonUndo = new JButton("Undo");
        bouttonRedo = new JButton("Redo");
        bouttonValider = new JButton("Valider");

        bouttonQuitter = new JButton("Quitter");

        ButtonContainer.add(bouttonUndo);
        ButtonContainer.add(bouttonRedo);
        ButtonContainer.add(bouttonValider);
        ButtonContainer.add(bouttonQuitter);

        bouttonUndo.addActionListener(this);
        bouttonRedo.addActionListener(this);
        bouttonValider.addActionListener(this);
        bouttonQuitter.addActionListener(this);
        
        bouttonMode2J = new JButton("Mode 2J");
        ButtonContainer.add(bouttonMode2J);
        bouttonMode2J.addActionListener(this);
    
        frame.setSize(DIM, DIM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //ce bloc nous permet d avoir la taille de la fenetre pour le bon positionnement des formes rouges pour bien les dispatcher sur tout le plateau
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            int largeur = vueJeu2D.getWidth();
            int hauteur = vueJeu2D.getHeight();
            System.out.println("Taille réelle VueJeu2D : " + largeur + "x" + hauteur);
            this.borneMaxX = largeur;
            this.borneMaxY = hauteur;
            ((ScoreParSurface) jeu.getCalculScore()).setSurfaceTotale(largeur * hauteur);
            jeu.demarrerPartie(largeur, hauteur);
        });
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == bouttonCercle){
            controlleurJeu.setEtatCourant(etatCreationCercle);    
        }
        if(ae.getSource() == bouttonRectangle){
            controlleurJeu.setEtatCourant(etatCreationRectangle);
        }
        if (ae.getSource() == bouttonUndo) {
            GestionnaireCommandes.getInstance().undo();
        }
        if (ae.getSource() == bouttonRedo) {
            GestionnaireCommandes.getInstance().redo();

        }

        if (ae.getSource() == bouttonQuitter) {
            System.exit(0);
        }

        if (ae.getSource() == bouttonValider) {
            if (modeDeuxJoueursActif 
                    && jeu.getPhase() == JeuFormes.Phase.JOUEUR1_PLACE_ROUGE) {
                strategieJoueur1.valider();
                jeu.joueur1Valide(vueJeu2D.getWidth(), vueJeu2D.getHeight());
                JOptionPane.showMessageDialog(this,"Joueur 2 : place tes formes BLEUES pour marquer des points !");
            } else {
                int scorePartie = (int) jeu.calculerScore();
                boolean fin = jeu.validerPartie(borneMaxX, borneMaxY);

                if (fin) {
                    StringBuilder recap = new StringBuilder();
                    recap.append("=== PARTIE TERMINEE ===\n\n");
                    List<Integer> scores = jeu.getScores();
                    List<Double> pourcentages = jeu.getPourcentages();
                    for (int i = 0; i < scores.size(); i++) {
                        recap.append("Partie ").append(i + 1)
                            .append(" : ").append(scores.get(i)).append("/4")
                            .append(" - ").append(String.format("%.1f", pourcentages.get(i))).append("%\n");
                        if (scores.get(i) == 4) recap.append("Top! Toutes les formes bien placees!!\n");
                        else if (scores.get(i) == 0) recap.append("Rate!!\n");
                    }
                    double moyenne = pourcentages.stream().mapToDouble(d -> d).average().orElse(0);
                    recap.append("\n─────────────────────\n");
                    recap.append("Score global : ").append(String.format("%.1f", moyenne)).append("%");
                    JOptionPane.showMessageDialog(this, recap.toString(), 
                        "Resultats finaux", JOptionPane.INFORMATION_MESSAGE);
                    labelScore.setText("Score global : " + String.format("%.1f", moyenne) + "%");

                } else {
                    String msg = "Partie " + jeu.getNumeroPartie() + "/" + jeu.getTotalParties() + "\n" +
                                "Score : " + scorePartie + "/4 formes valides\n\n" + jeu.calculerResultat() + "\n\n" +
                                "Prochaine partie...";
                    JOptionPane.showMessageDialog(this, msg,
                        "Score partie " + jeu.getNumeroPartie(), JOptionPane.INFORMATION_MESSAGE);

                    labelScore.setText("Partie " + (jeu.getNumeroPartie() + 1) + 
                                    "/" + jeu.getTotalParties() +
                                    "  |  Dernier score : " + scorePartie + "/4");

                    jeu.demarrerPartie(vueJeu2D.getWidth(), vueJeu2D.getHeight());
                }
            }
        }
        if (ae.getSource() == bouttonMode2J) {
            modeDeuxJoueursActif = true;
            jeu.activerModeDeuxJoueurs(strategieJoueur1);
            ((EtatCreationCercle) etatCreationCercle).setJeu(jeu);
            ((EtatCreationCercle) etatCreationCercle).setStrategie(strategieJoueur1);
            ((EtatCreationRectangle) etatCreationRectangle).setJeu(jeu);
            ((EtatCreationRectangle) etatCreationRectangle).setStrategie(strategieJoueur1);
            JOptionPane.showMessageDialog(this,"Mode 2 joueurs !\nJoueur 1 : dessine les zones ROUGES\nClique Valider quand tu as fini.");
        }
    }
}