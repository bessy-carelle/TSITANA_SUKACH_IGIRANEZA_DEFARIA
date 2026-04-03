/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.EtatCreationCercle;
import Model.EcouteurModele;
import Model.Point;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author tsitana251
 * 
 */
public class VueJeu2D extends JPanel implements EcouteurModele{
    EtatCreationCercle circle;
    final static int sizeX = 5;
    // rouge ou bleu
    
     public VueJeu2D(EtatCreationCercle circle) {
        this.circle = circle;
        setOpaque(false);  // Pas de fond opaque pour VueJeu2D, donc on verra le fond rouge derrière
        circle.ajoutEcouteurModele(this); 
    }
     
    @Override
    public void paintComponent(Graphics g){
        //g.drawOval(centre.x, centre.y, rayon, rayon);
        if (circle.getCentre() != null){
            Point centre = circle.getCentre();
            int x  = (int)centre.x;
            int y  = (int) centre.y;
            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX); // First diagonal line
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);
            
            if(circle.getRayon() > 0.0){
               int rayon = (int) circle.getRayon(); 
               g.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
            }
        }
        // demande à l'etat courant de se peindre en utilisant ecouteur model
    }

    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
}
