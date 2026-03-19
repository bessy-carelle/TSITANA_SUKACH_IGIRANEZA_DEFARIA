/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

/**
 *
 * @author tsitana251
 */
public class FenetreGlobale extends JFrame{
    public static final int DIM = 600;
    
    public FenetreGlobale(){
        JFrame frame = new JFrame("panel");
        JPanel ButtonContainer = new JPanel();
        MouseEventDemo mouseContainer = new MouseEventDemo();
        
        JButton bouttonCercle = new JButton("Cercle");
        JButton bouttonRectangle = new JButton("Rectangle");    
        ButtonContainer.add(bouttonCercle);
        ButtonContainer.add(bouttonRectangle);
        
        frame.add(ButtonContainer, BorderLayout.NORTH);
        frame.add(mouseContainer, BorderLayout.SOUTH);
        
        
        
        frame.setSize(DIM, DIM);
        frame.show();
    }
}
