/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.EcouteurModele;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Point;
import static com.mycompany.projetpatron.View.VueJeu2D.sizeX;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author tsitana251@CAMPUS
 */
public class VueGroupeFormesArea extends JPanel implements EcouteurModele{
    public ArrayList<Forme> formes = new ArrayList<>();
    GroupeForme groupeForme = new GroupeForme();
    EtatCreationCercle circle;
    final static int sizeX = 5;
    
    public VueGroupeFormesArea(EtatCreationCercle circle) {
            setOpaque(false); 
            this.circle = circle;
            circle.ajoutEcouteurModele(this);         
        }

    public ArrayList<Forme> getFormes() {
        return formes;
    }

    public void setFormes(ArrayList<Forme> formes) {
        this.formes = formes;
    }
    
    @Override
    public void paintComponent(Graphics g){
        //g.drawOval(centre.x, centre.y, rayon, rayon);
        for(Forme e : groupeForme.getFormes()){
            //this.add(e);
            this.repaint();
            Cercle forme = (Cercle) e;
            Point centre = forme.getCentre();
            int x  = (int)centre.x;
            int y  = (int) centre.y;
            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX); // First diagonal line
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);
            
            if(circle.getRayon() > 0.0){
               int rayon = (int) circle.getRayon(); 
               g.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
            }
        }
    }
    
    @Override
    public void modeleMisAJour(Object source) {
        
         //if(circle.getCercleDone()==1){
          //   groupeForme.ajoutForme(new Cercle(circle.getCentre(), (int) circle.getRayon()));
        //}
        for(Forme e : groupeForme.getFormes()){
            //this.add(e);
            this.repaint();
        }
        
    }
}
