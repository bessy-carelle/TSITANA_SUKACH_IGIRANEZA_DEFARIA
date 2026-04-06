/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import com.mycompany.projetpatron.Controller.VueControlleurState;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author tsitana251
 */
public class FenetreGlobale extends JFrame implements ActionListener{
    public static final int DIM = 600;
    public VueControlleurJeu controlleurJeu;
    private JButton bouttonCercle;
    private JButton bouttonRectangle;
    private JLabel drawArea;
    
    public VueControlleurState etatCreationCercle;
    public VueControlleurState etatCreationRectangle;
    public VueControlleurState etatSuppressionForme;
    public VueControlleurState etatDeplacementForme;
    
    public FenetreGlobale(VueControlleurJeu controlleurJeu, VueControlleurState etatCreationCercle, VueControlleurState etatCreationRectangle){
        this.controlleurJeu = controlleurJeu;
        this.etatCreationCercle = etatCreationCercle;
        this.etatCreationRectangle = etatCreationRectangle;
        JFrame frame = new JFrame("panel");
        JPanel ButtonContainer = new JPanel(); 
        
        JPanel drawArea = new JPanel();
        drawArea.setPreferredSize(new Dimension(DIM, DIM-100)); // Donne une taille au JPanel
        drawArea.setBackground(Color.WHITE); // Fond rouge pour drawArea
        drawArea.setBorder(BorderFactory.createLineBorder(Color.black));
        drawArea.setLayout(new BorderLayout());
        drawArea.setOpaque(true);
        drawArea.add(new VueJeu2D((EtatCreationCercle) etatCreationCercle,
                (EtatCreationRectangle) etatCreationRectangle        
        ), BorderLayout.CENTER);
        frame.add(drawArea, BorderLayout.SOUTH);
        
        drawArea.addMouseListener(controlleurJeu);
        drawArea.addMouseMotionListener(controlleurJeu); 
        bouttonCercle = new JButton("Cercle");
        bouttonRectangle = new JButton("Rectangle");    
        ButtonContainer.add(bouttonCercle);
        ButtonContainer.add(bouttonRectangle);
        bouttonRectangle.addActionListener(this);
        bouttonCercle.addActionListener(this);    
        
        frame.add(ButtonContainer, BorderLayout.NORTH);        // frame.add(mouseContainer, BorderLayout.SOUTH);
        
        
        
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
    }
}
