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
    public VueControlleurState myVueController;
    public VueControlleurState etatCourant;
    public VueControlleurState etatCreationCercle;
    public VueControlleurState etatCreationRectangle;
    public VueControlleurState etatSuppressionForme;
    public VueControlleurState etatDeplacementForme;
    
    public void setEtatCourant(VueControlleurState nouvelEtat){
        this.etatCourant = nouvelEtat;
    }
    
    public VueControlleurJeu(VueControlleurState myVueController){
        this.myVueController = myVueController;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        etatCourant.mouseClicked(me);
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        etatCourant.mouseMoved(me);
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
