/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author tsitana251
 */
public class FenetreGlobale extends JFrame implements ActionListener{
    public static final int DIM = 600;
    public VueControlleurJeu controlleurJeu;
    
    public FenetreGlobale(VueControlleurJeu controlleurJeu){
        this.controlleurJeu = controlleurJeu;
        JFrame frame = new JFrame("panel");
        JPanel ButtonContainer = new JPanel();
        MouseEventDemo mouseContainer = new MouseEventDemo();
        
        JButton bouttonCercle = new JButton("Cercle");
        JButton bouttonRectangle = new JButton("Rectangle");    
        ButtonContainer.add(bouttonCercle);
        ButtonContainer.add(bouttonRectangle);
        bouttonRectangle.addActionListener(this);
        bouttonCercle.addActionListener(this);    
        
        frame.add(ButtonContainer, BorderLayout.NORTH);
        frame.add(mouseContainer, BorderLayout.SOUTH);
        
        
        
        frame.setSize(DIM, DIM);
        frame.show();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        controlleurJeu.setEtatCourant(new EtatCreationCercle());
        System.out.println("cercleeeee");
    }
}
