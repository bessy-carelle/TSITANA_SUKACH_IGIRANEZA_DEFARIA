/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author tsitana251
 */
public class EtatCreationRectangle implements VueControlleurState{
    public Point coinRectangle;
    public int Largeur;
    public int hauteur;
    
    @Override
    public void mouseClicked(MouseEvent m) {
        System.out.println("Mouse clicked sur rectangle");
    }

    @Override
    public void mouseMoved(MouseEvent m) {
        System.out.println("Mouse moved sur rectangle");
    }
    
}
