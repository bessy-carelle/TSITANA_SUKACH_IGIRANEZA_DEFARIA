/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Controller;

import com.mycompany.projetpatron.Model.AbstractModeleEcoutable;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;
import com.mycompany.projetpatron.Controller.Command.Command;
import com.mycompany.projetpatron.Controller.Command.CommandAjoutForme;
import com.mycompany.projetpatron.Controller.Command.GestionnaireCommandes;
import java.awt.event.MouseEvent;

/**
 *
 * @author tsitana251
 */
public class EtatCreationRectangle extends AbstractModeleEcoutable implements VueControlleurState{
    public Point coinRectangle, diagPoint;
    public int largeur;
    public int hauteur;
    private int rectangleDone = 0;
    private double diagonale;
    public GroupeForme formes = new GroupeForme();

    public Point getCoinRectangle() {
        return coinRectangle;
    }

    public void setCoinRectangle(Point coinRectangle) {
        this.coinRectangle = coinRectangle;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked sur rectangle");
        if(coinRectangle == null){
            System.out.println("eto");
            coinRectangle = new Point(me.getX(),me.getY());
            System.out.println("Coin : " + coinRectangle);
            fireChangement();
        }else{
            rectangleDone = 1;
            diagPoint = new Point(me.getX(),me.getY());
            diagonale = diagPoint.getDistance(coinRectangle); 
 
            // Calcul de la largeur et de la hauteur
            largeur = (int) Math.abs(diagPoint.x - coinRectangle.x);
            hauteur = (int) Math.abs(diagPoint.y - coinRectangle.y);  
             System.out.println("diag mis en place");
            fireChangement();
            
        }
        
        if(rectangleDone == 1){
          System.out.println("creation nouveau rect");
             Rectangle ancienRectangle = new Rectangle(coinRectangle, largeur, hauteur);
             Command cmd = new CommandAjoutForme(formes, ancienRectangle);
             GestionnaireCommandes.getInstance().executerCommande(cmd);
             coinRectangle = null;
             largeur = 0;
             hauteur=0;
             rectangleDone = 0;
             fireChangement();
         }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(coinRectangle != null & rectangleDone==0){
            diagPoint = new Point(me.getX(),me.getY());
            diagonale = diagPoint.getDistance(coinRectangle); 
            largeur = (int) Math.abs(diagPoint.x - coinRectangle.x);
            hauteur = (int) Math.abs(diagPoint.y - coinRectangle.y);
            fireChangement();
        }
    }
    
}
