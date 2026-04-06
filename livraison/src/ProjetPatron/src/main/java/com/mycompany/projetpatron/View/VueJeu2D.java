/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.EcouteurModele;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author tsitana251
 * 
 */
public class VueJeu2D extends JPanel implements EcouteurModele{
    EtatCreationCercle circle;
    EtatCreationRectangle rectangle;
    final static int sizeX = 5;
    // rouge ou bleu
    
     public VueJeu2D(EtatCreationCercle circle, EtatCreationRectangle rectangle) {
        this.circle = circle;
        this.rectangle = rectangle;
        setOpaque(false);  // Pas de fond opaque pour VueJeu2D, donc on verra le fond rouge derrière
        circle.ajoutEcouteurModele(this); 
        rectangle.ajoutEcouteurModele(this); 
    }

    @Override
    public void paintComponent(Graphics g){
        //g.drawOval(centre.x, centre.y, rayon, rayon);
        // cercle
        System.out.println(" here 0000");
        if (circle.getCentre() != null){
            Point centre = circle.getCentre();
            int x  = (int) centre.x;
            int y  = (int) centre.y;
            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX); // First diagonal line
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);
            
            if(circle.getRayon() > 0.0){
               int rayon = (int) circle.getRayon(); 
               g.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
            }
        }
        ArrayList formes = circle.formes.getFormes();
        for (int i = 0; i < formes.size(); i++) {
            Cercle monCercle = (Cercle) formes.get(i);
            int x  = (int) monCercle.getCentre().x;
            int y  = (int) monCercle.getCentre().y;
            int rayon = (int) monCercle.getRayon();
            g.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
     
        }
        
        // rectangle
        System.out.println(" here 0");
        System.out.println("Coin vue: " + rectangle.getCoinRectangle());
        if (rectangle.getCoinRectangle() != null){
            Point coinRectangle = rectangle.getCoinRectangle();
            int x  = (int) coinRectangle.x;
            int y  = (int) coinRectangle.y;
            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX); // First diagonal line
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);
            
            System.out.println(" here 1");
            if(rectangle.getLargeur() > 0.0){
                System.out.println("Draw here ");
            
               int width = rectangle.getLargeur();
               int height = rectangle.getHauteur();
               g.drawRect(x, y, width, height);
               System.out.println("AFTER Draw ");
            }
        }
        
        ArrayList formes_rectangles = rectangle.formes.getFormes();
        for (int i = 0; i < formes_rectangles.size(); i++) {
            Rectangle monRectangle = (Rectangle) formes_rectangles.get(i);
            int x  = (int) monRectangle.getCoinRectangle().x;
            int y  = (int) monRectangle.getCoinRectangle().y;
            int width = (int) monRectangle.getWidth();
            int height = (int) monRectangle.getHeight();
            g.drawRect(x, y, width, height);
     
        }
    }

    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }

}
