/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AbstractModeleEcoutable;
import Model.Cercle;
import Model.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author tsitana251
 */
public class EtatCreationCercle extends AbstractModeleEcoutable implements VueControlleurState{
    private Point centre;
    private Point rayonPoint;
    private double rayon;
    private int cercleDone = 0;

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked sur cercleeeee");
        if(centre == null){
            System.out.println("Centre mis en place");
            centre = new Point(me.getX(),me.getY());
            fireChangement();
        }else {
            cercleDone = 1;
            rayonPoint = new Point(me.getX(),me.getY());
            rayon = rayonPoint.getDistance(centre);

            System.out.println("rayon mis en place");
            fireChangement();
        }
        
         //if(cercleDone == 1){
          //   System.out.println("creation nouveau cercle");
          //   Cercle ancienCercle = new Cercle(centre, (int) rayon);
           //  centre = null;
           //  cercleDone=0;
            //VueGroupeFormesArea.add(this);
            // nouveau ou reinitialiser
            //fireChangement();
         //}
    }

     //public int getCercleDone() {
     //    return cercleDone;
     //}
    
    @Override
    public void mouseMoved(MouseEvent me){
        if(centre != null & cercleDone==0){
            rayonPoint = new Point(me.getX(),me.getY());
            rayon = rayonPoint.getDistance(centre);
            fireChangement();
        }
    }

    public Point getCentre() {
        return centre;
    }

    public Point getRayonPoint() {
        return rayonPoint;
    }
    
    
    
    public double getRayon(){
        return rayon;
    }
    
}
