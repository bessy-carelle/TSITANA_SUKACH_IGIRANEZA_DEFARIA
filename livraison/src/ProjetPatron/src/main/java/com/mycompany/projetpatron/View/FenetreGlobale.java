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
    
    public VueControlleurState etatCreationCercle;
    public VueControlleurState etatCreationRectangle;
    public VueControlleurState etatSuppressionForme;
    public VueControlleurState etatDeplacementForme;
    
    public FenetreGlobale(VueControlleurJeu controlleurJeu, VueControlleurState etatCreationCercle, VueControlleurState etatCreationRectangle,JeuFormes jeu,int largeur, int hauteur){
        this.controlleurJeu = controlleurJeu;
        this.etatCreationCercle = etatCreationCercle;
        this.etatCreationRectangle = etatCreationRectangle;
        this.jeu = jeu;
        this.borneMaxX= largeur; 
        this.borneMaxY = hauteur;
        JFrame frame = new JFrame("panel");
        JPanel ButtonContainer = new JPanel(); 
        labelScore = new JLabel("Espace libre rempli : 0%");
        labelScore.setForeground(Color.BLUE);
        ButtonContainer.add(labelScore);

        JPanel drawArea = new JPanel();
        drawArea.setPreferredSize(new Dimension(DIM, DIM-100)); // Donne une taille au JPanel
        drawArea.setBackground(Color.WHITE); // Fond rouge pour drawArea
        drawArea.setBorder(BorderFactory.createLineBorder(Color.black));
        drawArea.setLayout(new BorderLayout());
        drawArea.setOpaque(true);
        drawArea.add(new VueJeu2D((EtatCreationCercle) etatCreationCercle,
                (EtatCreationRectangle) etatCreationRectangle,jeu      
        ), BorderLayout.CENTER);
        //frame.add(drawArea, BorderLayout.SOUTH);
        frame.add(drawArea, BorderLayout.CENTER);
        
        drawArea.addMouseListener(controlleurJeu);
        drawArea.addMouseMotionListener(controlleurJeu); 
        bouttonCercle = new JButton("Cercle");
        bouttonRectangle = new JButton("Rectangle");    
        ButtonContainer.add(bouttonCercle);
        ButtonContainer.add(bouttonRectangle);
        bouttonRectangle.addActionListener(this);
        bouttonCercle.addActionListener(this);    
        
        frame.add(ButtonContainer, BorderLayout.NORTH);        // frame.add(mouseContainer, BorderLayout.SOUTH);
        
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
        
        
        frame.setSize(DIM, DIM);
        frame.show();
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
        if (ae.getSource() == bouttonValider) {
            JOptionPane.showMessageDialog(null, "Score validé !");
            rafraichirScore();
        }
        if (ae.getSource() == bouttonQuitter) {
            System.exit(0);
        }
    }

   private void rafraichirScore() {
    int score = jeu.calculerPourcentage(borneMaxX, borneMaxY);
    labelScore.setText("Espace libre rempli : " + score + "%");
    JOptionPane.showMessageDialog(this,
        "Yess!!! Vous avez rempli " + score + "% de l'espace disponible.");
    }
}