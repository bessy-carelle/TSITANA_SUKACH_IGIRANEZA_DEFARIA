/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tsitana251
 */
public class Cercle extends Forme{
    public Point centre;
    public int rayon;
    
    public Cercle(String couleur){
        super(couleur);
        
    }
    
    public Cercle(Point centre, int rayon){
        super("ok");
        this.centre = centre;
        this.rayon = rayon;
    }
    
    public Point getCentre() {
        return centre;
    }
    
    public double getRayon(){
        return rayon;
    }
}
