/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
/**
 *
 * @author tsitana251
 */
public class VueControlleurJeu  extends JPanel implements MouseListener, MouseMotionListener{
    
    public VueControlleurState etatCourant;
    
    public VueControlleurState etatCreationCercle = new EtatCreationCercle();
    public VueControlleurState etatCreationRectangle  = new EtatCreationRectangle();
    public VueControlleurState etatSuppressionForme;
    public VueControlleurState etatDeplacementForme;
    
    public void setEtatCourant(VueControlleurState nouvelEtat){
       this.etatCourant = nouvelEtat;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(etatCourant != null){
            etatCourant.mouseClicked(me);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        if(etatCourant != null){
            etatCourant.mouseMoved(me);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("M");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println("M");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("M");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("M");
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        System.out.println("M");
    }

}
